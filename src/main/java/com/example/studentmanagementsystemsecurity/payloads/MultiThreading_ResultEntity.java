package com.example.studentmanagementsystemsecurity.payloads;


import com.example.studentmanagementsystemsecurity.models.Student_Advisor;
import com.example.studentmanagementsystemsecurity.models.Student_Parent;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MultiThreading_ResultEntity {

    private Long id;
    private String name;
    private String email;
    private String program;
    private Student_Parent parentDetails;
    private Student_Advisor advisorDetails;

}
