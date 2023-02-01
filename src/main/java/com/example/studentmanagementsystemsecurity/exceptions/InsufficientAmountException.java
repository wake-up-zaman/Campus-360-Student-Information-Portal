package com.example.studentmanagementsystemsecurity.exceptions;

public class InsufficientAmountException extends RuntimeException {

    public InsufficientAmountException(String msg){
        super(msg);
    }
}
