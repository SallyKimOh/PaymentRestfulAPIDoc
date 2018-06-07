package com.metaflow.payment;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
public class PaymentRestfulApiDocApplication {

//	@Override
//    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
//        return application.sources(PaymentRestfulApiDocApplication.class);
//    }	
	
	public static void main(String[] args) {
		SpringApplication.run(PaymentRestfulApiDocApplication.class, args);
	}
	
}
