package edu.nih.college.college_management_system.repository;

import edu.nih.college.college_management_system.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {

    // Find all addresses for a specific student
    List<Address> findByStudentId(Long studentId);

    // Custom query: Find unique addresses (avoiding duplicates)
    @Query("SELECT DISTINCT a FROM Address a WHERE a.student.id = :studentId")
    List<Address> findUniqueAddressesByStudentId(@Param("studentId") Long studentId);

    // Custom query: Find all addresses for all students
    @Query("SELECT a FROM Address a")
    List<Address> findAllAddresses();

    // Custom query to delete address by student ID (if needed)
    @Query("DELETE FROM Address a WHERE a.student.id = :studentId")
    void deleteAddressesByStudentId(@Param("studentId") Long studentId);
}
