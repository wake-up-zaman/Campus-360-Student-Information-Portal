package com.example.studentmanagementsystemsecurity.controllers;

import com.example.studentmanagementsystemsecurity.payloads.ExamRegistrationAcknowledgement;
import com.example.studentmanagementsystemsecurity.payloads.ApplicantRequest;
import com.example.studentmanagementsystemsecurity.services.ExamRegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/v1")
@EnableTransactionManagement
public class ExamRegistrationController {

    @Autowired
    private ExamRegistrationService examRegistrationService;

    @RequestMapping(value={"/studentForm"},method=RequestMethod.POST)
    public ExamRegistrationAcknowledgement registerStudent(@RequestBody ApplicantRequest request){
        return examRegistrationService.examRegistration(request);
    }

}
