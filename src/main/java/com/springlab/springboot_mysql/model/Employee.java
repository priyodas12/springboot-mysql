package com.springlab.springboot_mysql.model;


import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Builder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity(name = "employees")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long empId;

    @Column(name = "emp_name")
    private String name;
    private String role;
    private double salary;
    @Column(name = "dob")
    private Date dateOfBirth;
    private String pinCode;
    @Column(name = "job_location_city")
    private String jobLocation;
}
