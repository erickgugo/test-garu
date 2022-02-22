package com.example.demo.controller;

import com.example.demo.entity.Student;
import com.example.demo.service.StudentService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.Matchers.*;

@WebMvcTest(StudentController.class)
class StudentControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper mapper;

    @MockBean
    StudentService studentService;

    Student RECORD_1 = new Student("111AAA", "ERICK", "GUTIERREZ");
    Student RECORD_2 = new Student("222BBB", "HILDE", "PLATA");
    Student RECORD_3 = new Student("333CCC", "DIEGO", "MORALES");

    @Test
    @DisplayName(value = "POST: /student")
    void create() throws Exception {
        Student student = Student.builder()
                .studentId("ZZZ999")
                .firstMame("VICTOR")
                .lastName("MURO")
                .build();

        Mockito.when(studentService.create(student)).thenReturn(student);

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.post("/student")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(this.mapper.writeValueAsString(student));

        mockMvc.perform(mockRequest)
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$", notNullValue()))
                .andExpect(jsonPath("$.studentId", is("ZZZ999")));
    }

    @Test
    @DisplayName(value = "PUT: /student")
    void update() throws Exception {
        Student student = Student.builder()
                .studentId("111AAA")
                .firstMame("ERICK ROLANDO")
                .lastName("GUTIERREZ GONZALES")
                .build();

        Mockito.when(studentService.update(student)).thenReturn(student);

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.put("/student")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(this.mapper.writeValueAsString(student));

        mockMvc.perform(mockRequest)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", notNullValue()))
                .andExpect(jsonPath("$.studentId", is("111AAA")));
    }

    @Test
    @DisplayName(value = "DELETE: /student/{studentid}")
    void delete()  throws Exception{
        mockMvc.perform(MockMvcRequestBuilders
                .delete("/student/111AAA")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }

    @Test
    @DisplayName(value = "GET: /student")
    void findAll() throws Exception {
        List<Student> students = new ArrayList<>(Arrays.asList(RECORD_1, RECORD_2, RECORD_3));

        Mockito.when(studentService.findAll()).thenReturn(students);

        mockMvc.perform(MockMvcRequestBuilders
                .get("/student")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(3)))
                .andExpect(jsonPath("$[2].studentId", is("333CCC")));
    }

    @Test
    @DisplayName(value = "GET: /student/find/coursecode/{code}")
    void findCourseCode() throws Exception {
        Mockito.when(studentService.findCourseCode("INF-111")).thenReturn(Arrays.asList(RECORD_1, RECORD_2));

        mockMvc.perform(MockMvcRequestBuilders
                .get("/student/find/coursecode/INF-111")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", notNullValue()))
                .andExpect(jsonPath("$[1].studentId", is("222BBB")));
    }
}