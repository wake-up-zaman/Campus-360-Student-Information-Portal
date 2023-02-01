package com.example.studentmanagementsystemsecurity.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Applicant_Payment")
public class Applicant_Payment {

    @Id
    private String transactionId;
    private String accountName;
    private double paidAmount;
}
