package edu.nih.college.college_management_system.entity;

import jakarta.persistence.*;

@Entity
public class Application {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String applicationNumber;
    private String status;

    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student student;

    // Getters and setters
}
