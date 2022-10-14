package com.mdf.springjpa.Spring.Jpa.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class ProyectSecurityConfig {
	@Bean
	SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
//		Configuration to deny all the requests
//		http.authorizeRequests().anyRequest().denyAll().and().formLogin().and().httpBasic();

//		Permit all request
//		http.authorizeRequests().anyRequest().permitAll().and().formLogin().and().httpBasic();

//		Permit just what you want on matches and csrf permit post for all user
		http.authorizeRequests().antMatchers("/api/**").authenticated().and().formLogin().and().httpBasic().and().csrf().disable();
		return http.build();
	}

//	Es el mas basico para codificar una contraseña pero no es la mas segura.
//	@Bean
//	public PasswordEncoder passwordEncoder() {
//		return NoOpPasswordEncoder.getInstance();		
//	}

	//	Es el mas basico para codificar una contraseña pero no es la mas segura.
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder(2);		
	}
}
