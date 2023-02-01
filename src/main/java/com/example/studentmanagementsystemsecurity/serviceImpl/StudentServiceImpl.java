package com.example.studentmanagementsystemsecurity.serviceImpl;

import com.example.studentmanagementsystemsecurity.models.Student;
import com.example.studentmanagementsystemsecurity.exceptions.ResourceNotFoundException;
import com.example.studentmanagementsystemsecurity.models.Student_Advisor;
import com.example.studentmanagementsystemsecurity.models.Student_Parent;
import com.example.studentmanagementsystemsecurity.payloads.MultiThreading_ResultEntity;
import com.example.studentmanagementsystemsecurity.payloads.StudentDto;
import com.example.studentmanagementsystemsecurity.repositories.StudentRepository;
import com.example.studentmanagementsystemsecurity.repositories.Student_AdvisorRepository;
import com.example.studentmanagementsystemsecurity.repositories.Student_ParentRepository;
import com.example.studentmanagementsystemsecurity.services.StudentService;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private Student_ParentRepository studentParentRepository;

    @Autowired
    private Student_AdvisorRepository studentAdvisorRepository;

    @Autowired
    private ModelMapper modelMapper;

    Logger logger = LoggerFactory.getLogger(StudentServiceImpl.class);

    @Override
    public StudentDto createStudent(StudentDto studentDto) {
        Student student = this.modelMapper.map(studentDto, Student.class);
        Student addedStudent = this.studentRepository.save(student);
        return this.modelMapper.map(addedStudent, StudentDto.class);
    }

    @Override
    public StudentDto updateStudent(StudentDto studentDto, Long studentId) {
        Student student = this.studentRepository.findById(studentId).orElseThrow(()-> new ResourceNotFoundException("Student","Student Id", studentId));
        student.setStudent_name(studentDto.getStudent_name());
        student.setStudent_program(studentDto.getStudent_program());
        student.setStudent_email(studentDto.getStudent_email());
        student.setStudent_batch(studentDto.getStudent_batch());
        Student updatedStudent = this.studentRepository.save(student);
        return this.modelMapper.map(updatedStudent, StudentDto.class);
    }

    @Override
    public void deleteStudent(Long studentId) {
        Student student = this.studentRepository.findById(studentId).orElseThrow(()-> new ResourceNotFoundException("Student", "Student id",studentId));
        this.studentRepository.delete(student);
    }

    @Override
    public StudentDto getStudent(Long studentId) {
        Student student = this.studentRepository.findById(studentId).orElseThrow(()->new ResourceNotFoundException("Student","Student id",studentId));
        return this.modelMapper.map(student, StudentDto.class);
    }

    @Async
    @Override
    public CompletableFuture<StudentDto> findStudent(Long studentId, MultiThreading_ResultEntity multiThreadingResultEntity) {
        logger.info(Thread.currentThread().getName()+ "Running for executing Student Service ");
        Student student =studentRepository.findById(studentId).get();
        System.out.println(student);

        multiThreadingResultEntity.setId(student.getId());
        multiThreadingResultEntity.setName(student.getStudent_name());
        multiThreadingResultEntity.setEmail(student.getStudent_email());
        multiThreadingResultEntity.setProgram(student.getStudent_program());

        return null;

    }

    @Async
    @Override
    public CompletableFuture<List<StudentDto>> getAllStudents(){
        logger.info("All Students Running Thread "+Thread.currentThread().getName());
        List<Student> students =studentRepository.findAll();
        List<StudentDto> studentDtos = students.stream().map(student-> this.modelMapper.map(student, StudentDto.class)).collect(Collectors.toList());
        return CompletableFuture.completedFuture(studentDtos);
    }


    @Async
    @Override
    public CompletableFuture<List<Student>> saveStudents(MultipartFile file) throws Exception {
        long start = System.currentTimeMillis();
        List<Student> students = parseCSVFile(file);
        logger.info("saving list of users of size {}", students.size(), "" + Thread.currentThread().getName());
        students = studentRepository.saveAll(students);
        long end = System.currentTimeMillis();
        logger.info("Total time {}", (end - start));
        return CompletableFuture.completedFuture(students);
    }

    private List<Student> parseCSVFile(final MultipartFile file) throws Exception {
        final List<Student> student = new ArrayList<>();
        try {
            try (final BufferedReader br = new BufferedReader(new InputStreamReader(file.getInputStream()))) {
                String line;
                while ((line = br.readLine()) != null) {
                    final String[] data = line.split(",");
                    final Student students = new Student();
                    students.setStudent_email(data[0]);
                    students.setStudent_email(data[1]);
                    students.setStudent_batch(data[2]);
                    students.setStudent_program(data[3]);
                    student.add(students);
                }
                return student;
            }
        } catch (final IOException e) {
            logger.error("Failed to parse CSV file {}", e);
            throw new Exception("Failed to parse CSV file {}", e);
        }
    }

    //Student_Parent Service
    @Async
    @Override
    public CompletableFuture<Student_Parent> getParent(Long id, MultiThreading_ResultEntity multiThreadingResultEntity) {
        logger.info(Thread.currentThread().getName()+ "Running for executing Parent Service ");
        Student_Parent studentParentThreads = studentParentRepository.findById(id).get();
        System.out.println(studentParentThreads);

        multiThreadingResultEntity.setParentDetails(studentParentThreads);
        return null;
    }

    //Student_Advisor Service
    @Async
    @Override
    public CompletableFuture<Student_Advisor> getAdvisor(Long studentId, MultiThreading_ResultEntity multiThreadingResultEntity) {
        logger.info(Thread.currentThread().getName()+ "Running for executing Advisor Service ");
        Student_Advisor studentAdvisorThreads = studentAdvisorRepository.findById(studentId).get();

        multiThreadingResultEntity.setAdvisorDetails(studentAdvisorThreads);
        System.out.println(studentAdvisorThreads);
        return null;
    }


}
