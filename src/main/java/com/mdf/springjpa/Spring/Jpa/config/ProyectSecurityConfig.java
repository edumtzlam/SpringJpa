package com.mdf.springjpa.Spring.Jpa.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
//import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import com.mdf.springjpa.Spring.Jpa.filter.AuthoritiesLoggingAfterFilter;
import com.mdf.springjpa.Spring.Jpa.filter.AuthoritiesLoggingAtFilter;
import com.mdf.springjpa.Spring.Jpa.filter.RequestValidationBeforeFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true, jsr250Enabled = true, prePostEnabled = true)
public class ProyectSecurityConfig extends WebSecurityConfigurerAdapter {

//	@Value("${URLS.Authenticated}")
//	private String authenticatedURL;

	@Value("${URLS.Authenticated.WithoutRole}")
	private String authenticatedURLWithoutRole;

	@Value("${URLS.Authenticated.WithBalanceRole}")
	private String authenticatedURLWithBalanceRole;

	@Value("${URLS.Permitall}")
	private String permitallURL;

//	@Bean
//	SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
	@Override
	protected void configure(HttpSecurity http) throws Exception {
//		Configuration to deny all the requests
//		http.authorizeRequests().anyRequest().denyAll().and().formLogin().and().httpBasic();

//		Permit all request
//		http.authorizeRequests().anyRequest().permitAll().and().formLogin().and().httpBasic();

//		String[] listUrlAuthenticated = this.authenticatedURL.split(",");
		String[] listUrlAuthenticated = this.authenticatedURLWithoutRole.split(",");
		String[] listUrlAuthenticatedWithBalanceRole = this.authenticatedURLWithBalanceRole.split(",");
		String[] listUrlPermitall = this.permitallURL.split(",");
//		Permit just what you want on matches and csrf permit post for all user
		http.csrf().disable()
//		.addFilterBefore(new RequestValidationBeforeFilter(), BasicAuthenticationFilter.class)
//				.addFilterAfter(new AuthoritiesLoggingAfterFilter(), BasicAuthenticationFilter.class)
//				.addFilterAt(new AuthoritiesLoggingAtFilter(), BasicAuthenticationFilter.class)
				.authorizeRequests().antMatchers(listUrlAuthenticated).authenticated()
				.antMatchers(listUrlAuthenticatedWithBalanceRole).hasAnyAuthority("VIEWBALANCE")
				.antMatchers(listUrlPermitall).permitAll().and().formLogin().and().httpBasic();
//		http.authorizeRequests().antMatchers("/api/**").permitAll().and().formLogin().and().httpBasic().and().csrf().disable();
//		return http.build();
	}

//	Es el mas basico para codificar una contraseña pero no es la mas segura.
//	@Bean
//	public PasswordEncoder passwordEncoder() {
//		return NoOpPasswordEncoder.getInstance();		
//	}

	// Es el mas basico para codificar una contraseña pero no es la mas segura.
	// El parametro de BCryptPasswordEncoder solo acepta valores mayores a 4
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder(5);
	}

	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return new DummyAuthenticationManager();
	}

}

class DummyAuthenticationManager implements AuthenticationManager {

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		// TODO Auto-generated method stub
		return authentication;
	}

}
