package com.elastra.meli.Configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@Configuration
public class ThreadPoolConfig {

	
	@Bean
	public ThreadPoolTaskExecutor executor() {
		ThreadPoolTaskExecutor pool = new ThreadPoolTaskExecutor();
		pool.setCorePoolSize(10);
		pool.setWaitForTasksToCompleteOnShutdown(true);
		pool.setAwaitTerminationSeconds(Integer.MAX_VALUE);
		return pool;
	}

}
