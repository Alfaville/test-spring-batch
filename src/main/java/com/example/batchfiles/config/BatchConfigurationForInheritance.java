package com.example.batchfiles.config;

import com.example.batchfiles.model.target.LeiauteUnico;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.item.database.JpaItemWriter;
import org.springframework.batch.item.database.builder.JpaItemWriterBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManagerFactory;

@Configuration
@RequiredArgsConstructor
public class Config {

    private final EntityManagerFactory emf;

    @Bean
    public JpaItemWriter<LeiauteUnico> writerJpa() {
        return new JpaItemWriterBuilder<LeiauteUnico>().entityManagerFactory(this.emf).build();
    }

}
