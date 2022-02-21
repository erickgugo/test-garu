package com.example.demo.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "course")
public class Course {

    @Id
    private String code;
    private String title;
    private String description;
}
