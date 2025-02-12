package com.ecommerce.configs.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
	final UserDetailsServiceImpl userDetailsService;
	
	public WebSecurityConfig(UserDetailsServiceImpl userDetailsService) {
		this.userDetailsService = userDetailsService;
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
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
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth
			.userDetailsService(userDetailsService)
			.passwordEncoder(passwordEncoder());
	}

	@Bean
	protected PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
}
