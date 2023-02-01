package com.example.studentmanagementsystemsecurity.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;


@Data
@Entity
@Table(name = "USER_PARENT")
@AllArgsConstructor
@NoArgsConstructor
public class Student_Parent {

    @Id
    private Long id;
    private String father_name;
    private String mother_name;
    private String father_mobile;

}
