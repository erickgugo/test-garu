package com.example.demo.service;

import com.example.demo.entity.Student;
import com.example.demo.exception.DemoException;

import java.util.List;

public interface StudentService {

    Student create(Student student) throws DemoException;

    Student update(Student student) throws DemoException;

    void delete(String studentId) throws DemoException;

    Student findStudent(String studentId);

    List<Student> findAll() throws DemoException;

    List<Student> findCourseCode(String code) throws DemoException;
}
