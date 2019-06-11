package com.example.batchfiles.config;

import com.example.batchfiles.model.target.LeiauteUnico;
import org.springframework.batch.item.database.JpaItemWriter;
import org.springframework.batch.item.database.builder.JpaItemWriterBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManagerFactory;

@Configuration
public abstract class BatchConfigurationForInheritance {

    @Autowired
    private EntityManagerFactory emf;

    @Bean
    protected JpaItemWriter<LeiauteUnico> writerJpa() {
        return new JpaItemWriterBuilder<LeiauteUnico>().entityManagerFactory(this.emf).build();
    }

}
