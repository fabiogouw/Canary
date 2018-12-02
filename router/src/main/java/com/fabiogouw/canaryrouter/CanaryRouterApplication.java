package com.fabiogouw.canaryrouter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;

import io.micrometer.core.instrument.simple.SimpleMeterRegistry;

@EnableZuulProxy
@SpringBootApplication
public class CanaryRouterApplication {

	private static Logger _log = LoggerFactory.getLogger(CanaryRouterApplication.class);
	public static void main(String[] args) {
		SpringApplication.run(CanaryRouterApplication.class, args);
	}

    @Value("${routerStrategy}")
    private String _routerStrategy;

	@Bean
	public CanaryFilter simpleFilter() {
		_log.info("Router Strategy: " + _routerStrategy);
		CanaryInfoGetter infoGetter = _routerStrategy == "header" ? new HeaderInfoGetter() : new BodyInfoGetter();
	  	return new CanaryFilter(infoGetter);
	}	
}
