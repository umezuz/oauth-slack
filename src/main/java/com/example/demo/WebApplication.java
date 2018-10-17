package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

@SpringBootApplication
public class WebApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(WebApplication.class, args);
	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(WebApplication.class);
	}
	
	@Configuration
	@EnableOAuth2Sso
	@Order
	static class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	    @Override
	    protected void configure(HttpSecurity http) throws Exception {
	        http
	        		.csrf().disable() // CSRF対策を無効化
	                .authorizeRequests()
	                .antMatchers("/", "/healthcheck").permitAll() // /api以下のパスを認証なしで利用できるようにする
	                .anyRequest().authenticated()
	                .and().logout().logoutSuccessUrl("/").permitAll(); // ログアウト機能の設定
	    }
	}
}
