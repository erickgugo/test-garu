package com.example.demo.repository;

import com.example.demo.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student, String> {

    @Query(
            value = "SELECT s.* " +
                    "FROM STUDENT s, REGISTRATION r " +
                    "WHERE s.student_id = r.student_id " +
                    "AND r.code = ?1",
            nativeQuery = true)
    List<Student> findCourseCode(String code);
}
