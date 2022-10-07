package com.mdf.springjpa.Spring.Jpa.service.impl;

import java.security.Guard;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mdf.springjpa.Spring.Jpa.models.Guardian;
import com.mdf.springjpa.Spring.Jpa.models.Student;
import com.mdf.springjpa.Spring.Jpa.repository.IStudentRepository;
import com.mdf.springjpa.Spring.Jpa.service.IStudentService;

@Service
public class StudentServiceImpl implements IStudentService {

	@Autowired
	IStudentRepository _studentRepository;

	@Override
	public Student AddStudent(Student student) {
		Guardian objGuardian = Guardian.builder().name(student.getGuardian().getName())
				.email(student.getGuardian().getEmail()).mobile(student.getGuardian().getMobile()).build();

		Student objStudent = Student.builder().firstName(student.getFirstName()).lastName(student.getLastName())
				.eMailId(student.getEMailId()).guardian(objGuardian).build();
		this._studentRepository.save(objStudent);
		return objStudent;
	}

	@Override
	public Student FindStudentByEmail(String email) {
		return this._studentRepository.findByeMailId(email);
	}

	@Override
	public Boolean updateStudent(Student student) {
		try {
			Guardian objGuardian = Guardian.builder().name(student.getGuardian().getName())
					.email(student.getGuardian().getEmail()).mobile(student.getGuardian().getMobile()).build();
			Student objStudentUpdate = Student.builder().studentId(student.getStudentId())
					.firstName(student.getFirstName()).lastName(student.getLastName()).eMailId(student.getEMailId())
					.guardian(objGuardian).build();
			this._studentRepository.save(objStudentUpdate);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public Boolean removeStudent(Student student) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Student> retreiveAllStudent() {
		return this._studentRepository.findAll();
	}

}
