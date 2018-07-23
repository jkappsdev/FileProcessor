package com.rabobank.customer.statement.processor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@SpringBootApplication
public class RaboBankUtilityApplication {

    @EnableWebSecurity
    public class WebSecurityConfig extends
    WebSecurityConfigurerAdapter {

       @Override
       protected void configure(HttpSecurity http) throws Exception {
          http.csrf().disable();
       }
    }
    
	public static void main(String[] args) {
		SpringApplication.run(RaboBankUtilityApplication.class, args);
	}
}
