package com.looging.springbootmicroservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.actuate.autoconfigure.metrics.MeterRegistryCustomizer;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.context.annotation.Bean;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
@SpringBootApplication
public class SpringbootMicroserviceApplication {
	
	private static final Logger logger = LoggerFactory.getLogger(SpringbootMicroserviceApplication.class);

	public static void main(String[] args) {
		logger.info("Starting the Logging Demo Application");
		SpringApplication.run(SpringbootMicroserviceApplication.class, args);
	}
	
	 @Bean
	    public MeterRegistryCustomizer<MeterRegistry> metricsCommonTags() {
	        return registry -> registry.config().commonTags("application", "SpringbootMicroservice");
	    }
	}
	 


