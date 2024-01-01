package com.emi.practice.repository;

import com.emi.practice.entity.Student;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

@Repository
public class StudentImpl implements StudentRepository {

    private EntityManager entityManager;

    @Autowired
    public StudentImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    @Transactional
    public void addStudent(Student student) {
        entityManager.persist(student);
    }

    @Override
    public Student findStudentById(Integer id) {
        return entityManager.find(Student.class, id);
    }

    @Override
    public List<Student> findAll() {
        TypedQuery<Student> query = entityManager.createQuery("FROM Student", Student.class);
        return query.getResultList();
    }

    @Override
    public List<Student> findByIds(Integer... id) {
        TypedQuery<Student> query = entityManager.createQuery("FROM Student s WHERE s.id IN :id", Student.class);
        query.setParameter("id", Arrays.asList(id));
        return query.getResultList();
    }

    @Override
    public List<Student> findByName(String name) {
        TypedQuery<Student> query = entityManager.createQuery("FROM Student WHERE firstName = :name", Student.class);
        query.setParameter("name", name);
        return query.getResultList();
    }

    @Override
    public List<Student> findByNames(String... names) {
        TypedQuery<Student> query = entityManager.createQuery("FROM Student WHERE firstName IN :names", Student.class);
        query.setParameter("names", Arrays.asList(names));
        return query.getResultList();

    }

    @Override
    @Transactional
    public void removeStudent(int id) {
        try {
            Student student = entityManager.find(Student.class, id);
            if (student != null) {
                entityManager.remove(student);
                System.out.println("Estudiante con id " + id + " eliminado con exito!");
            } else {
                System.out.println("Estudiante con ID " + id + " no encontrado.");
            }
        } catch (Exception e) {
            throw new RuntimeException("Error al eliminar al estudiante con ID " + id, e);
        }
    }

    @Override
    @Transactional
    public void removeAllStudent() {
        try {
            Query nativeQuery = entityManager.createNativeQuery("TRUNCATE TABLE student");
            nativeQuery.executeUpdate();
            System.out.println("Success");
        }catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    @Transactional
    public void updateStudentName(Integer id, String name) {
        Student theStudent = findStudentById(id);
        theStudent.setFirstName(name);
        entityManager.merge(theStudent);
        System.out.println("The new name is: " + theStudent.getFirstName());
    }

    @Override
    @Transactional
    public void removeMultipleStudents(Integer... studentsIDs) {
        for (Integer id : studentsIDs) {
            Student theStudent = entityManager.find(Student.class, id);
            entityManager.remove(theStudent);
            System.out.println("Success, the student is removed, ids: " + theStudent.getId());
        }
    }

    @Override
    public List<Student> getAllStudents() {
        try {
            TypedQuery<Student> query = entityManager.createQuery("FROM Student", Student.class);
            List<Student> students = query.getResultList();
            System.out.println(students.toString());

            return query.getResultList();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

}

