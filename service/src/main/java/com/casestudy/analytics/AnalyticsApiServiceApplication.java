package com.casestudy.analytics;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@EnableAspectJAutoProxy
public class AnalyticsApiServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(AnalyticsApiServiceApplication.class, args);
	}

}
