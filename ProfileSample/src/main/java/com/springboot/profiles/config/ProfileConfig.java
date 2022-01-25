package com.springboot.profiles.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Slf4j
public class ProfileConfig {

	// @Profile annotation is used to conditionally activate/register
	// Used to develop an "if-then-else" conditional checking to configure
	// Allows to register beans by condition 
	@Profile(value = "dev")
	@Bean
	public void devConfig() {
		log.info("Succesfully loaded the development environment.");
	}
	
	@Profile(value = "qa")
	@Bean
	public void qaConfig() {
		log.info("Succesfully loaded the testing environment.");
	}

	@Profile(value = "prod")
	@Bean
	public void prodConfig() {
		log.info("Succesfully loaded the production environment.");
	}


}
