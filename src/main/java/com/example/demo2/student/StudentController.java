package com.example.demo2.student;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * API Layer: CRUD operations
 */

@RestController
@RequestMapping(path = "/api/v1/student")
public class StudentController {

	private final StudentService studentService;

	public StudentController(StudentService studentService) {
		this.studentService = studentService;
	}

	@GetMapping
	public List<Student> getStudent() {
		return studentService.getStudent();
	}

	@PostMapping
	public boolean addStudent(@RequestBody Student student) {
		return studentService.addStudent(student);
	}

	@DeleteMapping(path = "{studentEmail}")
	public boolean deleteStudent(@PathVariable("studentEmail") String email) {
		return studentService.deleteStudent(email);
	}

	@PutMapping(path = "{studentId}")
	public void updateStudent(@PathVariable("studentId") Long id, @RequestParam(required = false) String email,
			@RequestParam(required = false) String name) {
		studentService.updateStudent(id, email, name);
	}
}
