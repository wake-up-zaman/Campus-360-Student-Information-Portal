package com.example.studentmanagementsystemsecurity.models;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@Table(name = "USER_ADVISOR")
@AllArgsConstructor
@NoArgsConstructor

public class Student_Advisor {

    @Id
    private Long id;
    private String advisor_name;
    private String research_field;
    private String advisor_email;
}
