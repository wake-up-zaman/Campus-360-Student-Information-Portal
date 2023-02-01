package com.example.studentmanagementsystemsecurity.controllers;
import com.example.studentmanagementsystemsecurity.payloads.StudentDto;
import com.example.studentmanagementsystemsecurity.services.StudentService;
import com.example.studentmanagementsystemsecurity.utils.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.concurrent.CompletableFuture;


@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/v1/student")
public class StudentController {

    @Autowired
    private StudentService studentService;

    //Create
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(value={"post1","post2"},method=RequestMethod.POST)
    public ResponseEntity<StudentDto> createStudent(@RequestBody StudentDto studentDto){
        System.out.println("post");
        StudentDto createStudent = this.studentService.createStudent(studentDto);
        return new ResponseEntity<>(createStudent, HttpStatus.CREATED);
    }


    @PostMapping(value = "/users", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE}, produces = "application/json")
    public ResponseEntity saveStudents(@RequestParam(value = "files") MultipartFile[] files) throws Exception {
        for (MultipartFile file : files) {
            studentService.saveStudents(file);
        }
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    //Update
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(value={"{id}"},method=RequestMethod.PUT)
    public ResponseEntity<StudentDto> updateStudent(@Valid @RequestBody StudentDto studentDto, @PathVariable Long id){
        StudentDto updatedStudent = this.studentService.updateStudent(studentDto,id);
        return new ResponseEntity<>(updatedStudent, HttpStatus.OK);
    }


    //Delete
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(value={"{id}"},method=RequestMethod.DELETE)
    public ResponseEntity<ApiResponse> deleteStudent(@PathVariable Long id){
        this.studentService.deleteStudent(id);
        return new ResponseEntity<>(new ApiResponse("Student is deleted successfully", true), HttpStatus.OK);
    }

    //Get
    @PreAuthorize("hasAnyRole('ROLE_NORMAL','ROLE_ADMIN')")
    @RequestMapping(value={"{id}"},method=RequestMethod.GET)
    public ResponseEntity<StudentDto> getStudent(@PathVariable Long id){
        StudentDto getStudent = this.studentService.getStudent(id);
        return new ResponseEntity<>(getStudent, HttpStatus.OK);
    }

    //Get All by Multithreading
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(value={"/allStudents"},method=RequestMethod.GET)
    public CompletableFuture<ResponseEntity> getAllStudents(){
        return studentService.getAllStudents().thenApply(ResponseEntity::ok);
    }


}
