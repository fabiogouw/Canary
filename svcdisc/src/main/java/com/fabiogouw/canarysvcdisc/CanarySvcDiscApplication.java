package com.fabiogouw.canarysvcdisc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class CanarySvcDiscApplication {

	private static Logger _log = LoggerFactory.getLogger(CanarySvcDiscApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(CanarySvcDiscApplication.class, args);
	}
}
