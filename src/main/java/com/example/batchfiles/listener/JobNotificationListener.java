package com.example.batchfiles.listener;

import com.example.batchfiles.repository.UniqueLayoutRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.listener.JobExecutionListenerSupport;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.concurrent.TimeUnit;

@Slf4j
@RequiredArgsConstructor
@Component
public class JobNotificationListener extends JobExecutionListenerSupport {

    private final UniqueLayoutRepository uniqueLayoutRepository;

    @Override
    public void beforeJob(JobExecution jobExecution) {
        if(jobExecution.getStatus() == BatchStatus.STARTED)
            log.info("!!! JOB STARTED!");
    }

    @Override
    public void afterJob(JobExecution jobExecution) {
        Date start = jobExecution.getCreateTime();
        if(jobExecution.getStatus() == BatchStatus.COMPLETED) {
            Date end = jobExecution.getEndTime();
            long diff = end.getTime() - start.getTime();
            log.info("Time in Milliseconds: {}", TimeUnit.MILLISECONDS.convert(diff, TimeUnit.MILLISECONDS));

            log.info("!!! JOB FINISHED! Time to verify the results");

            this.uniqueLayoutRepository.findAll()
                    .forEach(leiaute -> log.info("Found <" + leiaute + "> in the database."));
        } else if(jobExecution.getStatus() == BatchStatus.FAILED)
            log.info("!!! JOB FAILED! ");
    }

}

