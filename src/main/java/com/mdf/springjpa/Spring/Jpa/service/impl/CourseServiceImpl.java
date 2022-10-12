package com.mdf.springjpa.Spring.Jpa.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mdf.springjpa.Spring.Jpa.models.Course;
import com.mdf.springjpa.Spring.Jpa.models.Student;
import com.mdf.springjpa.Spring.Jpa.repository.ICourseRepository;
import com.mdf.springjpa.Spring.Jpa.repository.IStudentRepository;
import com.mdf.springjpa.Spring.Jpa.service.ICourseService;

@Service
public class CourseServiceImpl implements ICourseService {

	@Autowired
	private IStudentRepository _studentRepository;
	@Autowired
	private ICourseRepository _courseRepository;

	@Override
	public boolean addCourse(Course course) {
		try {
			Student _student = _studentRepository.findById(course.getStudent().getStudentId())
					.orElseThrow(() -> new RuntimeException("student_id doesn't exist."));
//			System.out.println(_student);
			Course _newCourse = Course.builder().title(course.getTitle()).credits(course.getCredits()).student(_student)
					.build();
			this._courseRepository.save(_newCourse);
			return true;
		} catch (Exception e) {
			System.out.println(e);
			return false;
		}
	}

}
