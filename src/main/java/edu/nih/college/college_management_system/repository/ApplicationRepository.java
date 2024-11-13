package edu.nih.college.college_management_system.repository;

import edu.nih.college.college_management_system.model.Application;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ApplicationRepository extends JpaRepository<Application, Long> {

    // Find all applications for a specific student
    List<Application> findByStudentId(Long studentId);

    // Custom query to find applications by status
    @Query("SELECT a FROM Application a WHERE a.status = :status")
    List<Application> findApplicationsByStatus(@Param("status") String status);

    // Custom query to delete application by student ID (if needed)
    @Query("DELETE FROM Application a WHERE a.student.id = :studentId")
    void deleteApplicationByStudentId(@Param("studentId") Long studentId);
}
