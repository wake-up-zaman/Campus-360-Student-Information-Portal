package com.example.studentmanagementsystemsecurity.services;

import com.example.studentmanagementsystemsecurity.payloads.ExamRegistrationAcknowledgement;
import com.example.studentmanagementsystemsecurity.payloads.ApplicantRequest;

public interface ExamRegistrationService {

    //create
    ExamRegistrationAcknowledgement examRegistration(ApplicantRequest request);
}
