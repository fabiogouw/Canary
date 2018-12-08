package com.fabiogouw.canaryapi;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
@EnableDiscoveryClient
public class CanaryApiApplication {

	private static Logger _log = LoggerFactory.getLogger(CanaryApiApplication.class);

	@Value("${spring.application.name}")
	private String _appName;

	public static void main(String[] args) {
		SpringApplication.run(CanaryApiApplication.class, args);
	}

	@RequestMapping(value = "/new", method = RequestMethod.POST)
	public String newApi(@RequestBody BusinessData data) {
		_log.info("newApi");
	  return "The newest API! " + data.toString();
	}
  
	@RequestMapping(value = "/legacy", method = RequestMethod.POST)
	public String legacyApi(@RequestBody BusinessData data) {
		_log.info("legacy");
	  return "The good old-fashioned API... " + data.toString();
	}	
}
