package com.example.batchfiles.config;

import com.example.batchfiles.listener.JobCompletionNotificationListener;
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
import org.springframework.batch.item.database.JpaItemWriter;
import org.springframework.batch.item.database.builder.JpaItemWriterBuilder;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import javax.persistence.EntityManagerFactory;

@Configuration
@RequiredArgsConstructor
public class MasterBatchConfiguration {

    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;
    private final EntityManagerFactory emf;

    @Bean
    public BeanIOFlatFileItemReader<LeiauteBase> readerMaster() {
        Resource fileLeiaute = new ClassPathResource("leiaute_master_01012019.let");

        StreamFactory factory = StreamFactory.newInstance();
        StreamBuilder builder = new StreamBuilder("leiaute_master_01012019")
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
        beanIOFlatFileItemReader.setStreamName("leiaute_master_01012019");
        return beanIOFlatFileItemReader;
    }

    @Bean
    public LeiauteA1ItemProcessorMaster processorMaster() {
        return new LeiauteA1ItemProcessorMaster();
    }

    @Bean
    public JpaItemWriter<LeiauteUnico> writerJpaMaster() {
        return new JpaItemWriterBuilder<LeiauteUnico>()
                .entityManagerFactory(this.emf)
                .build();
    }

    @Bean
    public Job masterJob(JobCompletionNotificationListener listener, @Qualifier("masterStep") Step step1) {
        return jobBuilderFactory.get("masterJob")
                .incrementer(new RunIdIncrementer())
                .listener(listener)
                .flow(step1)
                .end()
                .build();
    }

    @Bean
    public Step masterStep(JpaItemWriter<LeiauteUnico> writer) {
        return stepBuilderFactory.get("masterStep")
                .<LeiauteBase, LeiauteUnico> chunk(100)
                .reader(readerMaster())
                .processor(processorMaster())
                .writer(writer)
                .build();
    }

}
