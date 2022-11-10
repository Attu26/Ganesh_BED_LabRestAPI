package com.greatlearning.student.service;

import java.util.List;

import javax.transaction.Transactional;
import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.greatlearning.student.model.Student;
import com.greatlearning.student.repository.StudentRepository;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

@Repository

public class StudentServiceImpl implements StudentService {
	
	@Autowired
	StudentRepository studentRepository;
	
	@Transactional
	public List<Student> findAll(){
		List<Student> students = studentRepository.findAll();
		return students;
	
	}
	@Transactional
	public Student findById(int id) {
		Student student = new Student();
		//find record with Id from DB table
		student = studentRepository.findById(id).get();
		return student;
		
	}
	@Transactional
	public void save(Student theStudent) {
		studentRepository.save(theStudent);
	}
	@Transactional
	public void deleteById(int id) {
		studentRepository.deleteById(id);
	}

	
}
