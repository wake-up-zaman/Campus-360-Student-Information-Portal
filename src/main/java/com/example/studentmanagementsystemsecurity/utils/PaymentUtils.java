package com.example.studentmanagementsystemsecurity.utils;
import com.example.studentmanagementsystemsecurity.exceptions.InsufficientAmountException;

public class PaymentUtils {

    private static double Exam_Registration_Amount=20000;
    public static boolean validateCreditLimit(double paidAmount) {
        if (paidAmount != Exam_Registration_Amount) {
            throw new InsufficientAmountException("insufficient fund..!");
        } else {
            return true;
        }
    }
}
