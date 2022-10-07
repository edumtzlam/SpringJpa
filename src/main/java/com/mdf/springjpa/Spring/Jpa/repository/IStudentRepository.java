package com.mdf.springjpa.Spring.Jpa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mdf.springjpa.Spring.Jpa.models.Student;

public interface IStudentRepository extends JpaRepository<Student, Long> {
//	Para hacer un query sin hacer un select
	Student findByeMailId(String emailId);
	List<Student> findByGuardianNameNotNull(); 
}
