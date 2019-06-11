package com.example.batchfiles.config;

import com.example.batchfiles.listener.JobNotificationListener;
import com.example.batchfiles.model.source.LeiauteBase;
import com.example.batchfiles.model.source.master.LeiauteA1;
import com.example.batchfiles.model.source.master.LeiauteDetail;
import com.example.batchfiles.model.source.master.LeiauteHeader;
import com.example.batchfiles.model.source.master.LeiauteTrailer;
import com.example.batchfiles.model.target.LeiauteUnico;
import com.example.batchfiles.processor.LeiauteA1ItemProcessorMaster;
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
public class ComplexBatchConfiguration extends BatchConfigurationForInheritance {

    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;

    @Bean
    public BeanIOFlatFileItemReader<LeiauteBase> readerMaster() {
        Resource fileLeiaute = new ClassPathResource("complex_layout.any");

        StreamFactory factory = StreamFactory.newInstance();
        StreamBuilder builder = new StreamBuilder("complex_layout")
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
        beanIOFlatFileItemReader.setStreamName("complex_layout");
        return beanIOFlatFileItemReader;
    }

    @Bean
    public LeiauteA1ItemProcessorMaster complexProcessor() {
        return new LeiauteA1ItemProcessorMaster();
    }

    @Bean
    public Job complexJob(JobNotificationListener listener, @Qualifier("complexStep") Step step1) {
        return jobBuilderFactory.get("complexJob")
                .incrementer(new RunIdIncrementer())
                .listener(listener)
                .flow(step1)
                .end()
                .build();
    }

    @Bean
    public Step complexStep() {
        return stepBuilderFactory.get("complexStep")
                .<LeiauteBase, LeiauteUnico> chunk(100)
                .reader(readerMaster())
                .processor(complexProcessor())
                .writer(super.writerJpa())
                .faultTolerant()
                .retry(Exception.class)
                .retryLimit(3)
                .build();
    }

}
