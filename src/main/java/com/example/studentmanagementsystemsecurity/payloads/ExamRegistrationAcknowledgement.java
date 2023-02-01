package com.example.studentmanagementsystemsecurity.payloads;


import com.example.studentmanagementsystemsecurity.models.Applicant_Details;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExamRegistrationAcknowledgement {

    private String status;
    private double paidAmount;
    private Applicant_Details examRegistrationStudentInfo;
}
