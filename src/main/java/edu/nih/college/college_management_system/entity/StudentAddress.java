package edu.nih.college.college_management_system.entity;

import jakarta.persistence.*;

@Entity
public class StudentAddress {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student student;

    @ManyToOne
    @JoinColumn(name = "address_id")
    private Address address;

    // Getters and setters
}
