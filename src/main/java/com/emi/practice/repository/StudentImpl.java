package com.emi.practice.repository;


import com.emi.practice.entity.Student;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class StudentImpl implements StudentRepository{

    private EntityManager entityManager;

    @Autowired
    public StudentImpl(EntityManager entityManager){
        this.entityManager = entityManager;
    }


    @Override
    @Transactional
    public void addStudent(Student student) {
        entityManager.persist(student);
    }
}
