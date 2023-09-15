package com.example.flight_routes.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

 

@Configuration
public class AirportConfiguration {
	@Bean
	public RestTemplate getRestTemplate() {
		RestTemplate restClient = new RestTemplate();
		return restClient;
	}
}
