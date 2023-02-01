package com.example.studentmanagementsystemsecurity.payloads;


import com.example.studentmanagementsystemsecurity.models.Applicant_Payment;
import com.example.studentmanagementsystemsecurity.models.Applicant_Details;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApplicantRequest {

        private Applicant_Details applicant_details;
        private Applicant_Payment applicant_payment;

}
