package com.mdf.springjpa.Spring.Jpa.config;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.transaction.Transactional;

//import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.mdf.springjpa.Spring.Jpa.models.Authority;
import com.mdf.springjpa.Spring.Jpa.models.Student;
import com.mdf.springjpa.Spring.Jpa.repository.IStudentRepository;

@Service
@Transactional
public class StudentDetailsConfig implements AuthenticationProvider {

	@Autowired
	private IStudentRepository _studentRepository;

	@Autowired
	private PasswordEncoder _passwordEncoder;

//	@Override
//	public Authentication loadUserByUsername(String username) throws UsernameNotFoundException {
//		String userName, password = null;
//		List<GrantedAuthority> authorities = null;
//		Student students = _studentRepository.findByEmailId(username);
//		if (students == null)
//			throw new UsernameNotFoundException("User details not found.");
//		else {
////			userName = students.getEmailId();
////			password = students.getPassword();
////			authorities = new ArrayList<>();
////			authorities.add(new SimpleGrantedAuthority(students.getRole()));
//		}
//		return null;
//	}

	@Override
	public org.springframework.security.core.Authentication authenticate(
			org.springframework.security.core.Authentication authentication) throws AuthenticationException {
		String userName = authentication.getName();
		String password = authentication.getCredentials().toString();
		Student students = this._studentRepository.findByEmailId(userName);
		if (students != null) {
			if (_passwordEncoder.matches(password, students.getPassword())) {
				return new UsernamePasswordAuthenticationToken(userName, password,
						getGrantedAuthorities(students.getAuthorities()));
			} else {
				throw new BadCredentialsException("No user registered with those credentials");
			}
		}
		return null;
	}

	private List<GrantedAuthority> getGrantedAuthorities(Set<Authority> authorities) {
		List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
		for (Authority authority : authorities) {
			grantedAuthorities.add(new SimpleGrantedAuthority(authority.getName()));
		}
		return grantedAuthorities;
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return (UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication));
	}

}
