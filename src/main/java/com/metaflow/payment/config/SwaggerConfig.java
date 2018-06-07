package com.metaflow.payment.config;

import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
//@EnableWebMvc // WebMvc 사용 설정 annotation 
//@ComponentScan({ "com.metaflow.payment.resource" }) // REST API 를 찾을 Package 지정 annotation 
public class SwaggerConfig  extends SpringBootServletInitializer{

	@Bean
    public Docket api() { 
        return new Docket(DocumentationType.SWAGGER_2)  
          .select()                                  
          .apis(RequestHandlerSelectors.basePackage("com.metaflow.payment.resource"))              
          .paths(PathSelectors.any())                          
          .build()
          .enable(true)
          .apiInfo(apiInfo());                                           
    }

//	@Bean 
//	public Docket api() { 
//		return new Docket(DocumentationType.SWAGGER_2)
//					.select()
//					.apis(RequestHandlerSelectors.any()) 
//					.paths(springfox.documentation.builders.PathSelectors.regex("/.*"))
//					.build(); 
//			} 
			

	
//    public ApiInfo apiInfo() {
//        StringVendorExtension vendorExtension = new StringVendorExtension("", "");
//        Collection<VendorExtension> vendorExtensions = new ArrayList<>();
//        vendorExtensions.add(vendorExtension);
//
//        Contact contactInfo = new Contact("test", "www.metaflow.com",
//               "test@gmail.com");
//
//        return new ApiInfo(
//            "SpringBoot-Swagger2-JaxRS",
//            "Example project showing how to integrate spring boot " +
//                  "web app using jaxrs instead of springmvc with swagger and springfox.",
//            "1.0",
//            "For Demo only",
//            contactInfo,
//            "Apache 2.0",
//            "www.apache.org",
//            vendorExtensions);
//      }	  
//    
    private ApiInfo apiInfo() {
		return new ApiInfoBuilder().title("JavaInUse API")
				.description("JavaInUse API reference for developers")
				.termsOfServiceUrl("http://www.metaflow.com")
				.contact("test@gmail.com").license("metaflow License")
				.licenseUrl("http://www.metaflow.ca").version("1.0").build();
	}  
	
}