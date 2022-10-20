package com.mdf.springjpa.Spring.Jpa.filter;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.util.StringUtils;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

import java.util.Base64;

public class RequestValidationBeforeFilter implements Filter {

	public static final String AUTHENTICATION_SCHEME_BASIC = "Basic";
	private Charset credentialsCharset = StandardCharsets.UTF_8;

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		String header = req.getHeader(AUTHORIZATION);
		if (header != null) {
			header = header.trim();
			if (StringUtils.startsWithIgnoreCase(header, AUTHENTICATION_SCHEME_BASIC)) {
				byte[] base64Token = header.substring(6).getBytes(credentialsCharset);
				byte[] decoded;
				try {
					decoded = Base64.getDecoder().decode(base64Token);
					String token = new String(decoded, credentialsCharset);
					System.out.println(token);
					int delim = token.indexOf(';');
					System.out.println(delim);
					System.out.println(decoded);
					if (delim == -1)
						throw new BadCredentialsException("Invalid Basic Authentication");
				} catch (IllegalArgumentException e) {
					System.out.println(e);
				}
			}
		}
		chain.doFilter(request, response);
	}

}
