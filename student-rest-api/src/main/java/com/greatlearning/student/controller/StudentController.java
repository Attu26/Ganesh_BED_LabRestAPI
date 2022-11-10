package com.greatlearning.student.controller;

import java.security.Principal;
import java.util.List;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.greatlearning.student.model.Student;
import com.greatlearning.student.service.StudentService;


@Controller
@RequestMapping("/api/students")
public class StudentController {

	@Autowired
	private StudentService studentService;

	//add mapping for "/list"
	@RequestMapping("/list")
	public String listStudent(Model theModel) {
		
		List<Student> theStudents = studentService.findAll();

		theModel.addAttribute("Students", theStudents);
		return "list-Students";

	}

	@RequestMapping("/showFormForAdd")
	public String showFormForAdd(Model theModel) {
		Student theStudent = new Student();
		theModel.addAttribute("Student", theStudent);
		return "Student-form";	
	}

	@RequestMapping("/showFormForUpdate")
	public String showFormForUpdate(@RequestParam("studentId") int theId,
			Model theModel) {
		Student theStudent = studentService.findById(theId);
		theModel.addAttribute("Student", theStudent);
		return "Student-form";

	}
	@PostMapping("/save")
	public String saveStudent(@RequestParam("id") int id,
			@RequestParam("firstName") String firstName,@RequestParam("lastName") String lastName,@RequestParam("course") String course,@RequestParam("country") String country) {

		System.out.println(id);
		Student theStudent;
		if(id!=0)
		{
			theStudent=studentService.findById(id);
			theStudent.setFirstName(firstName);
			theStudent.setLastName(lastName);
			theStudent.setCourse(course);
			theStudent.setCountry(country);
		}
		else
			theStudent=new Student(firstName, lastName, course,country);
		// save the Student Record
		studentService.save(theStudent);
		return "redirect:/student/list";
	}

	@RequestMapping("/delete")
	public String delete(@RequestParam("studentId") int theId) {
		studentService.deleteById(theId);
		return "redirect:/student/list";

	}
	@RequestMapping(value = "/403")
	public ModelAndView accesssDenied(Principal user) {

		ModelAndView model = new ModelAndView();

		if (user != null) {
			model.addObject("msg", "Hi " + user.getName() 
			+ ", you do not have permission to access this page!");
		} else {
			model.addObject("msg", 
					"You do not have permission to access this page!");
		}

		model.setViewName("403");
		return model;

	}

}

