package com.biquan.helloworld;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.core.annotation.Order;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.biquan.helloworld.config.InputStreamWrapperFilter;

@SpringBootApplication
public class HelloWorldApplication {

	public static void main(String[] args) {
		SpringApplication.run(HelloWorldApplication.class, args);
	}
	
	@Bean
	@Order(1)
	public FilterRegistrationBean inputStreamWrapperFilterRegistration() {
	    FilterRegistrationBean registrationBean = new FilterRegistrationBean();
	    registrationBean.setFilter(new InputStreamWrapperFilter());
	    registrationBean.setName("inputStreamWrapperFilter");
	    registrationBean.addUrlPatterns("/*");

	    return registrationBean;
	}
   
}
