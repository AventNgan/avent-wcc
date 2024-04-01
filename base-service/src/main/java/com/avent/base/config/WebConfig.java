package com.avent.base.config;

import com.avent.base.handler.ExceptionHandlerConfig;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@ComponentScan
@Import(ExceptionHandlerConfig.class)
public class WebConfig {
}
