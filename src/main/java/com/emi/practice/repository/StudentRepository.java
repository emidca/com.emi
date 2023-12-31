package com.emi.practice.repository;

import com.emi.practice.entity.Student;

import java.util.List;

public interface StudentRepository {
    void addStudent(Student student);

    Student findStudentById(Integer id);

    List<Student> findAll();

}
