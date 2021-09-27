package com.cognizant;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

/**
 * @author Vamsi Krishna
 *
 */
@EnableDiscoveryClient
@SpringBootApplication
public class ProceedToBuyApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProceedToBuyApplication.class, args);
	}
	
	/** Creates an RestTemplate Object
	 * @return RestTemplate
	 */
	@Bean
	public RestTemplate restTemplate() {
	    return new RestTemplate();
	}

}