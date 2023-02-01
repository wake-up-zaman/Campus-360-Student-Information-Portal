package com.example.studentmanagementsystemsecurity.controllers;


import com.example.studentmanagementsystemsecurity.payloads.MultiThreading_ResultEntity;
import com.example.studentmanagementsystemsecurity.serviceImpl.MultiThread_ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.ExecutionException;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/result")
public class MultiThread_ServiceController {

    @Autowired
    private MultiThread_ServiceImpl multiThread_serviceImpl;

    @GetMapping("/{id}")
    MultiThreading_ResultEntity getResult(@PathVariable long id) throws ExecutionException {
        return multiThread_serviceImpl.mergeResult(id);
    }

}
