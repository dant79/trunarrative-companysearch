package com.trunarrative.companysearch.client;

import feign.Logger;
import org.springframework.cloud.openfeign.support.ResponseEntityDecoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FeignClientConfiguration {

    @Bean
    Logger.Level feignLoggerLevel() {
        return Logger.Level.FULL;
    }

}
