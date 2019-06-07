package com.example.batchfiles.listener;

import com.example.batchfiles.repository.LeiauteUnicoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.listener.JobExecutionListenerSupport;
import org.springframework.stereotype.Component;

@Slf4j
@RequiredArgsConstructor
@Component
public class JobCompletionNotificationListener extends JobExecutionListenerSupport {

    private final LeiauteUnicoRepository leiauteUnicoRepository;

    @Override
    public void afterJob(JobExecution jobExecution) {
        if(jobExecution.getStatus() == BatchStatus.COMPLETED) {
            log.info("!!! JOB FINISHED! Time to verify the results");

            this.leiauteUnicoRepository.findAll()
                    .forEach(leiaute -> log.info("Found <" + leiaute + "> in the database."));
        }
    }
}
