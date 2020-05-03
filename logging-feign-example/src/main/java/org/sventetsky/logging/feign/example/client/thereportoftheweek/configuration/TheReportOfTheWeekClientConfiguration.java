package org.sventetsky.logging.feign.example.client.thereportoftheweek.configuration;

import feign.Logger;
import feign.Request;
import org.springframework.cloud.openfeign.FeignLoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.sventetsky.logging.feign.PrettyFeignLoggerFactory;

import static java.util.concurrent.TimeUnit.SECONDS;

@Configuration
public class TheReportOfTheWeekClientConfiguration {

    @Bean
    public FeignLoggerFactory theReportOfTheWeekFeignLoggerFactory() {
        return new PrettyFeignLoggerFactory(new TheReportOfTheWeekLoggingConfig());
    }

    @Bean
    public Logger.Level theReportOfTheWeekFeignLoggerLevel() {
        return Logger.Level.HEADERS;
    }

    @Bean
    public Request.Options theReportOfTheWeekFeignRequestOptions() {
        return new Request.Options(
                4, SECONDS,
                4, SECONDS,
                true
        );
    }


}
