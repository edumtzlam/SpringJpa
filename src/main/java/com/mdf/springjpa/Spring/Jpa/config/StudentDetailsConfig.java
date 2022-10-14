package com.mdf.springjpa.Spring.Jpa.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.mdf.springjpa.Spring.Jpa.models.Student;
import com.mdf.springjpa.Spring.Jpa.repository.IStudentRepository;

@Service
public class StudentDetailsConfig implements UserDetailsService {

	@Autowired
	private IStudentRepository _studentRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		String userName, password = null;
		List<GrantedAuthority> authorities = null;
		Student students = _studentRepository.findByEmailId(username);
		System.out.println(students);
		if (students == null)
			throw new UsernameNotFoundException("User details not found.");
		else {
			userName = students.getEmailId();
			password = students.getPassword();
			authorities = new ArrayList<>();
			authorities.add(new SimpleGrantedAuthority(students.getRole()));
		}
		return new User(userName, password, authorities);
	}

}
