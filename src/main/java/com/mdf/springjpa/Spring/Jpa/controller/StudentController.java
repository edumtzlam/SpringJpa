package com.mdf.springjpa.Spring.Jpa.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mdf.springjpa.Spring.Jpa.models.Student;
import com.mdf.springjpa.Spring.Jpa.service.IStudentService;

@RestController
@RequestMapping("/api/student")
public class StudentController {
	@Autowired
	private IStudentService _studentService;

	@PostMapping
	public ResponseEntity<Student> createNewStudent(@Valid @RequestBody Student student) {
		Student objStudent = _studentService.AddStudent(student);
		return new ResponseEntity<>(objStudent, HttpStatus.CREATED);
	}

	@GetMapping("/byEmail")
	ResponseEntity<Student> getStudentByEmail(@RequestParam(name = "email", required = true) String email) {
		Student objStudentByEmail = this._studentService.FindStudentByEmail(email);
		return new ResponseEntity<>(objStudentByEmail, HttpStatus.OK);
	}

	@GetMapping("/All")
	ResponseEntity<List<Student>> getAllStudents() {
		List<Student> arrStudents = this._studentService.retreiveAllStudent();
		return new ResponseEntity<List<Student>>(arrStudents, HttpStatus.OK);
	}

	@PutMapping
	public ResponseEntity<Boolean> updateStudent(@Valid @RequestBody Student student) {
		Boolean objSucces = this._studentService.updateStudent(student);
		return new ResponseEntity<>(objSucces, HttpStatus.OK);
	}
}
