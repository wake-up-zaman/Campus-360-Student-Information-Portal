package com.example.studentmanagementsystemsecurity.services;

import com.example.studentmanagementsystemsecurity.models.Student;
import com.example.studentmanagementsystemsecurity.models.Student_Advisor;
import com.example.studentmanagementsystemsecurity.models.Student_Parent;
import com.example.studentmanagementsystemsecurity.payloads.MultiThreading_ResultEntity;
import com.example.studentmanagementsystemsecurity.payloads.StudentDto;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.multipart.MultipartFile;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@EnableAsync(proxyTargetClass=true)
@EnableCaching(proxyTargetClass=true)
public interface StudentService {

    StudentDto createStudent(StudentDto studentDto);

    StudentDto updateStudent(StudentDto studentDto, Long studentId);

    void deleteStudent(Long studentId);

    StudentDto getStudent(Long studentId);

    @Async
    CompletableFuture<List<Student>> saveStudents(MultipartFile file) throws Exception;

    @Async
    CompletableFuture<List<StudentDto>> getAllStudents();

    @Async
    CompletableFuture<StudentDto> findStudent(Long studentId, MultiThreading_ResultEntity multiThreadingResultEntity);

    @Async
    CompletableFuture<Student_Parent> getParent(Long studentId, MultiThreading_ResultEntity multiThreadingResultEntity);

    @Async
    CompletableFuture<Student_Advisor> getAdvisor(Long studentId, MultiThreading_ResultEntity multiThreadingResultEntity);

}