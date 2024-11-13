package edu.nih.college.college_management_system.service;

import edu.nih.college.college_management_system.model.Student;
import edu.nih.college.college_management_system.repository.AddressRepository;
import edu.nih.college.college_management_system.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private AddressRepository addressRepository;

    // Find all students
    @Transactional(readOnly = true)
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    // Find student by ID
    @Transactional(readOnly = true)
    public Student getStudentById(Long id) {
        Optional<Student> student = studentRepository.findById(id);
        return student.orElseThrow(() -> new RuntimeException("Student not found"));
    }

    // Create a new student
    @Transactional
    public Student addStudent(Student student) {
        return studentRepository.save(student);
    }

    // Update student information
    @Transactional
    public Student updateStudent(Student student) {
        Optional<Student> existingStudent = studentRepository.findById(student.getId());
        if (existingStudent.isPresent()) {
            return studentRepository.save(student);
        } else {
            throw new RuntimeException("Student not found");
        }
    }

    // Delete a student by ID
    @Transactional
    public void deleteStudent(Long id) {
        studentRepository.deleteById(id);
    }

    // Method to add a student and their multiple addresses
    @Transactional
    public Student addStudentWithAddresses(Student student, List<Address> addresses) {
        // Save student
        Student savedStudent = studentRepository.save(student);

        // Insert multiple addresses
        for (Address address : addresses) {
            address.setStudent(savedStudent);  // Associate the address with the student
            addressRepository.save(address);
        }

        return savedStudent;
    }
}
