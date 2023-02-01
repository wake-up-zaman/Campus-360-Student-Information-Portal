package com.example.studentmanagementsystemsecurity.serviceImpl;

import com.example.studentmanagementsystemsecurity.models.Student_Advisor;
import com.example.studentmanagementsystemsecurity.models.Student_Parent;
import com.example.studentmanagementsystemsecurity.payloads.MultiThreading_ResultEntity;
import com.example.studentmanagementsystemsecurity.payloads.StudentDto;
import com.example.studentmanagementsystemsecurity.services.MultiThread_Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.concurrent.CompletableFuture;


@Service
public class MultiThread_ServiceImpl implements MultiThread_Service {

    @Autowired
    private StudentServiceImpl studentService;

    //Here multiple threads concurrently running through different entity and repository to get Student and their regarding Parent and Advisor information for improving  the performance

    public MultiThreading_ResultEntity mergeResult(Long id) {

        MultiThreading_ResultEntity multiThreadingResultEntity = new MultiThreading_ResultEntity();

        CompletableFuture<StudentDto> student= studentService.findStudent(id, multiThreadingResultEntity);
        CompletableFuture<Student_Parent> parent= studentService.getParent(id, multiThreadingResultEntity);
        CompletableFuture<Student_Advisor> advisor= studentService.getAdvisor(id, multiThreadingResultEntity);

        CompletableFuture<Void> completableFutureCombined = CompletableFuture.allOf(student,parent,advisor);
        completableFutureCombined.join();

        System.out.println("All thread worked");
        return multiThreadingResultEntity;
    }

}





















//    public CompletableFuture<List<StudentThread>> mergeAllResult() throws InterruptedException, ExecutionException {
//        CompletableFuture<List<StudentThread>> students= studentThreadService.findAllUsers();
//        CompletableFuture<List<ParentThread>> parents=parentThreadService.findAllParents();
//        CompletableFuture<List<AdvisorThread>> advisors=advisorThreadService.findAllAdvisors();
//        CompletableFuture<Void> completableFutureCombined = CompletableFuture.allOf(students,parents,advisors);
//        completableFutureCombined.join();
//        System.out.println("All thread worked");
//
//        return students;
//    }