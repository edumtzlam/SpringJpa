package com.mdf.springjpa.Spring.Jpa.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.jboss.logging.Logger;

public class AuthoritiesLoggingAtFilter implements Filter {

	private final Logger LOG = Logger.getLogger(AuthoritiesLoggingAtFilter.class.getName());

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		LOG.info("Authentication validation is in progress");
		
		chain.doFilter(request, response);
		
	}

}
