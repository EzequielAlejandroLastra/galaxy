package com.elastra.meli.Configuration;

import com.elastra.meli.Task.GeneratePredictionTasklet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.*;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.Date;

@Configuration
public class BatchConfiguration {

    private static final Logger logger = LoggerFactory.getLogger(BatchConfiguration.class);

    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    @Autowired
    @Qualifier("jobLauncher")
    private JobLauncher jobLauncher;

    @Scheduled(cron = "0 0 0 * * *")
    public void sendSmsForExpiringBookmark() throws Exception {
        logger.info(" Job Started at :"+ new Date());
        JobParameters param = new JobParametersBuilder().addString("JobID",
                String.valueOf(System.currentTimeMillis())).toJobParameters();
        JobExecution execution = jobLauncher.run(weatherPredictionJob(), param);
        logger.info("Job finished with status :" + execution.getExitStatus());
    }

    @Bean
    public Job weatherPredictionJob() {
        return jobBuilderFactory.get("job")
                .incrementer(new RunIdIncrementer())
                .flow(stepGeneratePrediction())
                .end()
                .build();
    }

    public Step stepGeneratePrediction() {
        return stepBuilderFactory.get("generatePredictionTasklet")
                .tasklet(generatePredictionTasklet())
                .build();
    }

    public Tasklet generatePredictionTasklet() {
        return new GeneratePredictionTasklet();
    }




}
