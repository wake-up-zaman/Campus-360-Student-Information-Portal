package com.example.studentmanagementsystemsecurity.serviceImpl;

import com.example.studentmanagementsystemsecurity.models.Applicant_Payment;
import com.example.studentmanagementsystemsecurity.models.Applicant_Details;
import com.example.studentmanagementsystemsecurity.payloads.ExamRegistrationAcknowledgement;
import com.example.studentmanagementsystemsecurity.payloads.ApplicantRequest;
import com.example.studentmanagementsystemsecurity.repositories.Applicant_PaymentRepository;
import com.example.studentmanagementsystemsecurity.repositories.Applicant_DetailsRepository;
import com.example.studentmanagementsystemsecurity.services.ExamRegistrationService;
import com.example.studentmanagementsystemsecurity.utils.PaymentUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ExamRegistrationServiceImpl implements ExamRegistrationService {

    @Autowired
    private Applicant_DetailsRepository applicant_detailsRepository;
    @Autowired
    private Applicant_PaymentRepository applicant_paymentRepository;


    //Transaction_Management
    @Override
    @Transactional
    public ExamRegistrationAcknowledgement examRegistration(ApplicantRequest request) {
        Applicant_Details applicantInfo= request.getApplicant_details();
        applicantInfo = applicant_detailsRepository.save(applicantInfo);

        Applicant_Payment applicant_payment = request.getApplicant_payment();

        PaymentUtils.validateCreditLimit(applicant_payment.getPaidAmount());
        applicant_paymentRepository.save(applicant_payment);
        return new ExamRegistrationAcknowledgement("SUCCESS", applicant_payment.getPaidAmount(), applicantInfo);
    }
}

