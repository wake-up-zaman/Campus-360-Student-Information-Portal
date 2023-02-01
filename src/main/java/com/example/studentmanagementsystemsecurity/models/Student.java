package com.example.studentmanagementsystemsecurity.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "students")
@NoArgsConstructor
@Getter
@Setter
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name", length = 100, nullable = false)
    private String student_name;
    @Column(name = "program")
    private String student_program;
    @Column(name = "email")
    private String student_email;
    @Column(name = "batch")
    private String student_batch;
    @Version
    private Long version;
}
