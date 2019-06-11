package com.example.batchfiles.config;

import com.example.batchfiles.model.target.UniqueLayout;
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
    protected JpaItemWriter<UniqueLayout> writerJpa() {
        return new JpaItemWriterBuilder<UniqueLayout>().entityManagerFactory(this.emf).build();
    }

}
