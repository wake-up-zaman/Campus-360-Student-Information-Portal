package com.example.studentmanagementsystemsecurity.payloads;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class StudentDto {
    private Long id;
    private String student_name;
    private String student_program;
    private String student_email;
    private String student_batch;
}
