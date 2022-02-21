package com.example.demo.repository;

import com.example.demo.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CourseRepository extends JpaRepository<Course, String> {

    @Query(
            value = "SELECT c.* " +
                    "FROM COURSE c, REGISTRATION r " +
                    "WHERE c.code = r.code " +
                    "AND r.student_id = ?1",
            nativeQuery = true)
    List<Course> findStudentId(String studentId);
}
