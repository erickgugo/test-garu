package com.example.demo.controller;

import com.example.demo.entity.Course;
import com.example.demo.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/course", produces = MediaType.APPLICATION_JSON_VALUE)
public class CourseController {

    @Autowired
    private CourseService courseService;

    @PostMapping
    public ResponseEntity<?> create(@RequestBody Course course) {
        return new ResponseEntity<>(courseService.create(course), HttpStatus.CREATED);
    }

    @PutMapping()
    public ResponseEntity<?> update(@RequestBody Course course) {
        return new ResponseEntity<>(courseService.update(course), HttpStatus.OK);

    }

    @DeleteMapping("/{code}")
    public ResponseEntity<?> delete(@PathVariable("code") String code) {
        courseService.delete(code);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping
    public ResponseEntity<?> findAll() {
        return new ResponseEntity<>(courseService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/find/studentid/{studentId}")
    public ResponseEntity<?> findStudentId(@PathVariable("studentId") String studentId) {
        return new ResponseEntity<>(courseService.findStudentId(studentId), HttpStatus.OK);
    }

    @GetMapping("/registered/{code}/studentid/{studentId}")
    public ResponseEntity<?> registeredStudent(@PathVariable("code") String code, @PathVariable("studentId") String studentId) {
        return new ResponseEntity<>(courseService.registeredStudent(code, studentId), HttpStatus.OK);
    }

    @GetMapping("/unregistered/{code}/studentid/{studentId}")
    public ResponseEntity<?> unRegisteredStudent(@PathVariable("code") String code, @PathVariable("studentId") String studentId) {
        courseService.unRegisteredStudent(code, studentId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
