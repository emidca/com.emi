package com.emi.practice;

import com.emi.practice.entity.Student;
import com.emi.practice.repository.StudentImpl;
import com.emi.practice.repository.StudentRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Arrays;
import java.util.List;

@SpringBootApplication
public class PracticeApplication {

	public static void main(String[] args) {
		SpringApplication.run(PracticeApplication.class, args);
		System.out.println("Running...");

	}

	@Bean
	public CommandLineRunner commandLineRunner(StudentRepository studentRepository){
		return run -> {
			//createStudent(studentRepository);
			//findStudent(studentRepository);
			//getAll(studentRepository);
			//findByManyIds(studentRepository);
			//findByName(studentRepository);
			findNames(studentRepository);


		};
	}

	private void findNames(StudentRepository studentRepository) {
		try {

			List<Student> findNames = studentRepository.findByNames("ago");

			System.out.println(findNames);

		}
		catch (Exception e)
		{System.out.println(e.getMessage());}

	}

	private void findByName(StudentRepository studentRepository) {
		try {

			List<Student> name = studentRepository.findByName("ago");
			System.out.println(name);

		}catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}

	private void findByManyIds(StudentRepository studentRepository) {
		try {

			List<Student> studentsFromRepository = studentRepository.findByIds(3);

			System.out.println(studentsFromRepository);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}



	private void getAll(StudentRepository studentRepository) {
		List<Student> students = studentRepository.findAll();
		for(Student student : students){
			System.out.println(student.toString());
		}
	}


	private void findStudent(StudentRepository studentRepository) {
		int id = 2;
		Student theStudentId = studentRepository.findStudentById(id);
		System.out.println(theStudentId.toString());
	}


	private void createStudent(StudentRepository studentRepository) {
		Student student = new Student("Emi","Di", "emi@gmail.com");

		System.out.println("Creating student...");
		studentRepository.addStudent(student);
		System.out.println("Finished... with id: " + student.getId() + "and the name " + student.getFirstName());
	};




}
