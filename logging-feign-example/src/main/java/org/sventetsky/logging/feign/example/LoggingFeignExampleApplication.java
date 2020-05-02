package org.sventetsky.logging.feign.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class LoggingFeignExampleApplication {

	public static void main(String[] args) {
		SpringApplication.run(LoggingFeignExampleApplication.class, args);
	}

}
