package com.mdf.springjpa.Spring.Jpa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;

import com.mdf.springjpa.Spring.Jpa.models.Student;

public interface IStudentRepository extends JpaRepository<Student, Long> {
//	Para hacer un query sin hacer un select
	public Student findByEmailId(String emailId);

	public List<Student> findByGuardianNameNotNull();

	@Procedure
	public List<Student> getAllStudents();
}
