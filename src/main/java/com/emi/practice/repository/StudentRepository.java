package com.emi.practice.repository;

import com.emi.practice.entity.Student;

import java.util.List;

public interface StudentRepository {
    void addStudent(Student student);
    Student findStudentById(Integer id);
    List<Student> findAll();
    List<Student> findByIds(Integer... id);

    List<Student> findByName(String name);

    List<Student> findByNames(String... names);

    void removeStudent(int id);

    void removeAllStudent();

    void updateStudentName(Integer id, String name);


    

}
