package com.alexlzn.coches;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestTemplateConfig {

	@Bean("restTemplate") //nombre para ser usado en Spring
	public RestTemplate getRestTemplate() {
		return new RestTemplate();
	}
}
