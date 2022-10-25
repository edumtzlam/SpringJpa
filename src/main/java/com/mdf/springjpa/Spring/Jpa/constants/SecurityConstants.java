package com.mdf.springjpa.Spring.Jpa.constants;

import org.springframework.beans.factory.annotation.Value;

public class SecurityConstants {

	@Value(value = "${App.jwtSecret}")
	public static String JWT_KEY = "jxgEQeXHuPq8VdbyYFNkANdudQ53YUn4";
	public static final String JWT_HEADER = "Autorization";
}
