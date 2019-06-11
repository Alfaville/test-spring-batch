package com.example.batchfiles.config;

import com.example.batchfiles.listener.JobNotificationListener;
import com.example.batchfiles.model.source.LeiauteBase;
import com.example.batchfiles.model.source.visa.LeiauteA1;
import com.example.batchfiles.model.source.visa.LeiauteDetail;
import com.example.batchfiles.model.source.visa.LeiauteHeader;
import com.example.batchfiles.model.source.visa.LeiauteTrailer;
import com.example.batchfiles.model.target.LeiauteUnico;
import com.example.batchfiles.processor.LeiauteA1ItemProcessorVisa;
import lombok.RequiredArgsConstructor;
import org.beanio.StreamFactory;
import org.beanio.builder.FixedLengthParserBuilder;
import org.beanio.builder.StreamBuilder;
import org.beanio.spring.BeanIOFlatFileItemReader;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

@Configuration
@RequiredArgsConstructor
class SimpleBatchConfiguration extends BatchConfigurationForInheritance {

    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;

    @Bean
    protected BeanIOFlatFileItemReader<LeiauteBase> reader() {
        Resource fileLeiaute = new ClassPathResource("simple_layout.any");

        StreamFactory factory = StreamFactory.newInstance();
        StreamBuilder builder = new StreamBuilder("simple_layout")
                .format("fixedlength")
                .parser(new FixedLengthParserBuilder())
                .addRecord(LeiauteA1.class)
                .addRecord(LeiauteHeader.class)
                .addRecord(LeiauteDetail.class)
                .addRecord(LeiauteTrailer.class);
        factory.define(builder);

        BeanIOFlatFileItemReader beanIOFlatFileItemReader = new BeanIOFlatFileItemReader<>();
        beanIOFlatFileItemReader.setResource(fileLeiaute);
        beanIOFlatFileItemReader.setStreamFactory(factory);
        beanIOFlatFileItemReader.setErrorHandler(new LoggingBeanReaderErrorHandler());
        beanIOFlatFileItemReader.setStreamName("simple_layout");
        return beanIOFlatFileItemReader;
    }

    @Bean
    protected LeiauteA1ItemProcessorVisa simppleProcessor() {
        return new LeiauteA1ItemProcessorVisa();
    }

    @Bean
    protected Job simpleJob(JobNotificationListener listener, @Qualifier("simpleStep") Step step1) {
        return jobBuilderFactory.get("simpleJob")
                .incrementer(new RunIdIncrementer())
                .listener(listener)
                .flow(step1)
                .end()
                .build();
    }

    @Bean
    protected Step simpleStep() {
        return stepBuilderFactory.get("simpleStep")
                .<LeiauteBase, LeiauteUnico> chunk(100)
                .reader(reader())
                .processor(simppleProcessor())
                .writer(super.writerJpa())
                .build();
    }

}
