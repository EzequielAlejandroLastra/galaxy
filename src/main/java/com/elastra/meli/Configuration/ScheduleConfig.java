package com.elastra.meli.Configuration;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.launch.support.SimpleJobLauncher;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.repository.support.AbstractJobRepositoryFactoryBean;
import org.springframework.batch.core.repository.support.MapJobRepositoryFactoryBean;
import org.springframework.batch.support.transaction.ResourcelessTransactionManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
public class ScheduleConfig {

    private static final Logger logger = LoggerFactory.getLogger(ScheduleConfig.class);


    @Bean
    public PlatformTransactionManager transactionManager() {
        return new ResourcelessTransactionManager();
    }

    @Bean
    public AbstractJobRepositoryFactoryBean mapJobRepositoryFactory(PlatformTransactionManager transactionManager) throws Exception {
        MapJobRepositoryFactoryBean factory = new MapJobRepositoryFactoryBean(transactionManager);
        factory.afterPropertiesSet();
        return factory;
    }


    @Bean
    public JobRepository jobRepository(AbstractJobRepositoryFactoryBean mapJobRepositoryFactory) throws Exception {
        return (JobRepository) mapJobRepositoryFactory.getObject();
    }

    @Bean("jobLauncher")
    public JobLauncher jobLauncher(JobRepository jobRepository) {
        SimpleJobLauncher launcher = new SimpleJobLauncher();
        launcher.setJobRepository(jobRepository);
        return launcher;
    }

}
