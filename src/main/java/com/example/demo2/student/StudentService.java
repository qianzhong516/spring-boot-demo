package com.example.demo2.student;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;

/**
 * Service Layer
 */

@Service
public class StudentService {
	private final StudentRepository studentRepository;

	public StudentService(StudentRepository studentRepository) {
		this.studentRepository = studentRepository;
	}

	public List<Student> getStudent() {
		return studentRepository.findAll();
	}

	public boolean addStudent(Student student) {
		Optional<Student> maybeStudent = studentRepository.findStudentByEmail(student.getEmail());

		if (maybeStudent.isPresent()) {

			throw new IllegalStateException("Email taken");
		} else {
			studentRepository.save(student);

			return true;
		}
	}

	@Transactional
	public boolean deleteStudent(String email) {
		Optional<Student> maybeStudent = studentRepository.findStudentByEmail(email);

		if (maybeStudent.isPresent()) {
			studentRepository.deleteByEmail(email);
			return true;
		} else {
			throw new IllegalStateException("Email does not exist");
		}
	}

	// @Transactional is to use setters from the entity to perform queries
	@Transactional
	public void updateStudent(Long id, String email, String name) {
		Student student = studentRepository.findById(id)
				.orElseThrow(() -> new IllegalStateException("Student does not exist"));

		if (name != null && !name.isEmpty() && name != student.getName()) {
			student.setName(name);
		}

		if (email != null && !email.isEmpty() && email != student.getEmail()) {
			Optional<Student> maybeStudent = studentRepository.findStudentByEmail(email);

			if (maybeStudent.isPresent()) {
				throw new IllegalStateException("Email taken");
			}

			student.setEmail(email);
		}
	}

}
