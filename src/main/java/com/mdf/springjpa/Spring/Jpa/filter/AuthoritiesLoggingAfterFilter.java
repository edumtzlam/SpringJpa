package com.mdf.springjpa.Spring.Jpa.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.jboss.logging.Logger;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class AuthoritiesLoggingAfterFilter implements Filter {

	private final Logger LOG = Logger.getLogger(AuthoritiesLoggingAfterFilter.class.getName());

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication != null) {
			LOG.info("User" + authentication.getName());
		}
		chain.doFilter(request, response);
	}
}
