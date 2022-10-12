package com.mdf.springjpa.Spring.Jpa.service;

import java.util.List;

import com.mdf.springjpa.Spring.Jpa.models.Student;

public interface IStudentService {
	Student AddStudent(Student student);

	Student FindStudentByEmail(String email);

	Boolean updateStudent(Student student);

	Boolean removeStudent(Student student);

	List<Student> retreiveAllStudent();
}
