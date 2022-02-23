package com.example.demo.service.impl;

import com.example.demo.entity.Course;
import com.example.demo.entity.Student;
import com.example.demo.exception.DemoException;
import com.example.demo.repository.CourseRepository;
import com.example.demo.repository.StudentRepository;
import com.example.demo.service.StudentService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
@Data
public class StudenServiceImpl implements StudentService {

    private final CourseRepository courseRepository;

    private final StudentRepository studentRepository;

    @Autowired
    public StudenServiceImpl(final CourseRepository courseRepository, final StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
        this.courseRepository = courseRepository;
    }

    @Override
    public Student create(Student student) throws DemoException {
        if (findStudent(student.getStudentId()) != null) {
            throw new EntityExistsException(student.getStudentId() + " - exists");
        }
        return save(student);
    }

    @Override
    public Student update(Student student) throws DemoException {
        if (findStudent(student.getStudentId()) == null) {
            throw new EntityNotFoundException(student.getStudentId() + " - not exists");
        }
        return save(student);
    }

    public Student save(Student student) throws DemoException {
        try {
            return studentRepository.save(student);
        } catch (Exception e) {
            throw new DemoException(e);
        }
    }

    @Override
    public void delete(String studentId) throws DemoException {
        if (findStudent(studentId) == null) {
            throw new EntityNotFoundException(studentId + " - not exists");
        }
        try {
            studentRepository.deleteById(studentId);
        } catch (Exception e) {
            throw new DemoException(e);
        }
    }

    @Override
    public Student findStudent(String studentId) {
        Optional<Student> student = studentRepository.findById(studentId);
        if (student.isPresent()) {
            return student.get();
        }
        return null;
    }

    @Override
    public List<Student> findAll() throws DemoException {
        List<Student> list;
        try {
            list = studentRepository.findAll();
            return list;
        } catch (Exception e) {
            throw new DemoException(e);
        }
    }

    @Override
    public List<Student> findCourseCode(String code) throws DemoException {
        Optional<Course> course = courseRepository.findById(code);
        if (!course.isPresent()) {
            throw new EntityNotFoundException(code + " - not exists");
        }
        try {
            return studentRepository.findCourseCode(code);
        } catch (Exception e) {
            throw new DemoException(e);
        }
    }
}
