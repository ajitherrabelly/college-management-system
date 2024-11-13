package edu.nih.college.college_management_system.repository;

import edu.nih.college.college_management_system.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

    // Custom query method to find students by name, safely using parameterized queries
    List<Student> findByName(String name);

    // Custom query to find students by email, automatically parameterized
    Student findByEmail(String email);

    // Custom query using JPQL (safe) to fetch students with their addresses
    @Query("SELECT s FROM Student s JOIN FETCH s.addresses WHERE s.name = :name")
    List<Student> findStudentsWithAddressesByName(@Param("name") String name);
}
