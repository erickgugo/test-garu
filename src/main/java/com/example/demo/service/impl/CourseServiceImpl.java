package com.example.demo.service.impl;

import com.example.demo.entity.Course;
import com.example.demo.entity.Registration;
import com.example.demo.exception.DemoException;
import com.example.demo.repository.CourseRepository;
import com.example.demo.repository.RegistrationRepository;
import com.example.demo.service.CourseService;
import com.example.demo.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
public class CourseServiceImpl implements CourseService {

    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private RegistrationRepository registrationRepository;
    @Autowired
    private StudentService studentService;

    @Override
    public Course create(Course course) throws DemoException {
        if (findCourse(course.getCode()) != null) {
            throw new EntityExistsException(course.getCode() + " - exists");
        }
        return save(course);
    }

    @Override
    public Course update(Course course) throws DemoException {
        if (findCourse(course.getCode()) == null) {
            throw new EntityNotFoundException(course.getCode() + " - not exists");
        }
        return save(course);
    }

    public Course save(Course course) throws DemoException {
        try {
            return courseRepository.save(course);
        } catch (Exception e) {
            throw new DemoException(e);
        }
    }

    @Override
    public void delete(String code) throws DemoException {
        if (findCourse(code) == null) {
            throw new EntityNotFoundException(code + " - not exists");
        }
        try {
            courseRepository.deleteById(code);
        } catch (Exception e) {
            throw new DemoException(e);
        }
    }

    private Course findCourse(String courseId) {
        Optional<Course> course = courseRepository.findById(courseId);
        if (course.isPresent()) {
            return course.get();
        }
        return null;
    }

    @Override
    public List<Course> findAll() throws DemoException {
        List<Course> list;
        try {
            list = courseRepository.findAll();
            return list;
        } catch (Exception e) {
            throw new DemoException(e);
        }
    }

    @Override
    public List<Course> findStudentId(String studentId) throws DemoException {
        List<Course> list;
        try {
            list = courseRepository.findStudentId(studentId);
            return list;
        } catch (Exception e) {
            throw new DemoException(e);
        }
    }

    @Override
    public Registration registeredStudent(String code, String studentId) throws DemoException {
        if (findCourse(code) == null) {
            throw new EntityNotFoundException(code + " course not exists");
        }

        if (studentService.findStudent(studentId) == null) {
            throw new EntityNotFoundException(studentId + " student not exists");
        }

        if (findRegistration(code, studentId) != null) {
            throw new EntityExistsException(studentId + " already registered " + code);
        }

        try {
            return registrationRepository.save(Registration.builder().code(code).studentId(studentId).build());
        } catch (Exception e) {
            throw new DemoException(e);
        }
    }

    @Override
    public void unRegisteredStudent(String code, String studentId) throws DemoException {
        Registration registration = findRegistration(code, studentId);
        if (registration == null) {
            throw new EntityNotFoundException(studentId + " unregistered " + code);
        }
        try {
            registrationRepository.deleteById(registration.getId());
        } catch (Exception e) {
            throw new DemoException(e);
        }
    }

    private Registration findRegistration(String code, String studentId) {
        List<Registration> registration = registrationRepository.findByCodeAndAndStudentId(code, studentId);
        if (!registration.isEmpty()) {
            return registration.get(0);
        }
        return null;
    }
}
