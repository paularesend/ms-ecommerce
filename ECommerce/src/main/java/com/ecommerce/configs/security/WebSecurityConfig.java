package com.ecommerce.configs.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class WebSecurityConfig {
	
	 @Bean
	 public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
	        http
	        .httpBasic()
	        .and()
	        .authorizeHttpRequests()
	        .antMatchers("/h2-console/**").permitAll()  //libera acesso ao H2 console
	        .antMatchers(HttpMethod.GET, "/products/**").permitAll()
	        .antMatchers(HttpMethod.POST, "/products").hasRole("USER")
	        .antMatchers(HttpMethod.DELETE, "/products/**").hasRole("ADMIN")
	        .anyRequest().authenticated()
	        .and()
	        .csrf().disable()
	        .headers().frameOptions().disable();  //permite exibição do H2 console em frames
	        return http.build();
	    }

	@Bean
	protected PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
}
