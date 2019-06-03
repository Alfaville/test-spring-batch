package com.example.batchfiles.config;

import com.example.batchfiles.JobCompletionNotificationListener;
import com.example.batchfiles.model.source.LeiauteA1;
import com.example.batchfiles.model.target.LeiauteUnico;
import com.example.batchfiles.processor.LeiauteA1ItemProcessor;
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
import org.springframework.batch.item.database.JpaItemWriter;
import org.springframework.batch.item.database.builder.JpaItemWriterBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

@Configuration
@EnableBatchProcessing
@RequiredArgsConstructor
public class BatchConfiguration {

    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;
    private final EntityManagerFactory emf;

    @Bean
    public BeanIOFlatFileItemReader<LeiauteA1> reader() {
        Resource fileLeiaute = new ClassPathResource("leiauteX020520191443.let");

        StreamFactory factory = StreamFactory.newInstance();
        StreamBuilder builder = new StreamBuilder("leiauteX020520191443")
                .format("fixedlength")
                .parser(new FixedLengthParserBuilder())
                .addRecord(LeiauteA1.class);
        factory.define(builder);

        BeanIOFlatFileItemReader beanIOFlatFileItemReader = new BeanIOFlatFileItemReader<>();
        beanIOFlatFileItemReader.setResource(fileLeiaute);
        beanIOFlatFileItemReader.setStreamFactory(factory);
        beanIOFlatFileItemReader.setErrorHandler(new LoggingBeanReaderErrorHandler());
        beanIOFlatFileItemReader.setStreamName("leiauteX020520191443");
        return beanIOFlatFileItemReader;
    }

    @Bean
    public LeiauteA1ItemProcessor processor() {
        return new LeiauteA1ItemProcessor();
    }

    @Bean
    public JpaItemWriter<LeiauteUnico> writer(DataSource dataSource) {
        return new JpaItemWriterBuilder<LeiauteUnico>()
                .entityManagerFactory(this.emf)
                .build();
    }

    @Bean
    public Job importUserJob(JobCompletionNotificationListener listener, Step step1) {
        return jobBuilderFactory.get("importUserJob")
                .incrementer(new RunIdIncrementer())
                .listener(listener)
                .flow(step1)
                .end()
                .build();
    }

    @Bean
    public Step step1(JpaItemWriter<LeiauteUnico> writer) {
        return stepBuilderFactory.get("step1")
                .<LeiauteA1, LeiauteUnico> chunk(100)
                .reader(reader())
                .processor(processor())
                .writer(writer)
                .build();
    }

}
