package com.emi.practice.repository;

import com.emi.practice.entity.Student;

public interface StudentRepository {
    void addStudent(Student student);

    Student findStudentById(Integer id);

}
