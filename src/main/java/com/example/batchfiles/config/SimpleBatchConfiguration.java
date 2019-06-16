package com.example.batchfiles.config;

import com.example.batchfiles.listener.JobNotificationListener;
import com.example.batchfiles.model.source.BaseLayout;
import com.example.batchfiles.model.source.simple.SimpleLayout;
import com.example.batchfiles.model.source.simple.SimpleLayoutDetail;
import com.example.batchfiles.model.source.simple.SimpleLayoutHeader;
import com.example.batchfiles.model.source.simple.SimpleLayoutTrailer;
import com.example.batchfiles.model.target.UniqueLayout;
import com.example.batchfiles.processor.SimpleLayoutItemProcessor;
import lombok.RequiredArgsConstructor;
import org.beanio.StreamFactory;
import org.beanio.builder.FixedLengthParserBuilder;
import org.beanio.builder.StreamBuilder;
import org.beanio.spring.BeanIOFlatFileItemReader;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

@Profile({"simple"})
@Configuration
@EnableBatchProcessing
@RequiredArgsConstructor
class SimpleBatchConfiguration extends BatchConfigurationForInheritance {

    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;

    @Bean
    protected BeanIOFlatFileItemReader<BaseLayout> simpleReader() {

        StreamFactory factory = StreamFactory.newInstance();
        StreamBuilder builder = new StreamBuilder("simple_layout")
                .format("fixedlength")
                .parser(new FixedLengthParserBuilder())
                .addRecord(SimpleLayout.class)
                .addRecord(SimpleLayoutHeader.class)
                .addRecord(SimpleLayoutDetail.class)
                .addRecord(SimpleLayoutTrailer.class);
        factory.define(builder);

        Resource resource = new ClassPathResource("simple_layout.any");
        BeanIOFlatFileItemReader beanIOFlatFileItemReader = new BeanIOFlatFileItemReader<>();
        beanIOFlatFileItemReader.setResource(resource);
        beanIOFlatFileItemReader.setStreamFactory(factory);
        beanIOFlatFileItemReader.setErrorHandler(new LoggingBeanReaderErrorHandler());
        beanIOFlatFileItemReader.setStreamName("simple_layout");
        return beanIOFlatFileItemReader;
    }

    @Bean
    protected SimpleLayoutItemProcessor simppleProcessor() {
        return new SimpleLayoutItemProcessor();
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
                .<BaseLayout, UniqueLayout> chunk(100)
                .reader(simpleReader())
                .processor(simppleProcessor())
                .writer(super.writerJpa())
                .build();
    }

}
