package org.sventetsky.logging.feign.example.client.jsonplaceholder.configuration;

import feign.Logger;
import feign.Request;
import org.springframework.cloud.openfeign.FeignLoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.sventetsky.logging.feign.PrettyFeignLoggerFactory;

import static java.util.concurrent.TimeUnit.SECONDS;

@Configuration
public class JsonPlaceHolderClientConfiguration {

    @Bean
    public FeignLoggerFactory jsonPlaceHolderFeignLoggerFactory() {
        return new PrettyFeignLoggerFactory(new JsonPlaceHolderLoggingConfig());
    }

    @Bean
    public Logger.Level jsonPlaceHolderFeignLoggerLevel() {
        return Logger.Level.FULL;
    }

    @Bean
    public Request.Options jsonPlaceHolderFeignRequestOptions() {
        return new Request.Options(
                10, SECONDS,
                10, SECONDS,
                true
        );
    }
}
