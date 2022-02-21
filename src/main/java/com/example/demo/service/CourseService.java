package com.example.demo.service;

import com.example.demo.entity.Course;
import com.example.demo.entity.Registration;
import com.example.demo.entity.Student;
import com.example.demo.exception.DemoException;

import java.util.List;

public interface CourseService {

    Course create(Course course) throws DemoException;

    Course update(Course course) throws DemoException;

    void delete(String code) throws DemoException;

    List<Course> findAll() throws DemoException;

    List<Course> findStudentId(String studentId) throws DemoException;

    Registration registeredStudent(String code, String studentId) throws DemoException;

    void unRegisteredStudent(String code, String studentId) throws DemoException;
}
