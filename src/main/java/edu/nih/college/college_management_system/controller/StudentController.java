package edu.nih.college.college_management_system.controller;

import edu.nih.college.college_management_system.model.Student;
import edu.nih.college.college_management_system.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentController {

    @Autowired
    private StudentService studentService;

    // GET all students
    @GetMapping
    public ResponseEntity<List<Student>> getAllStudents() {
        List<Student> students = studentService.getAllStudents();
        return new ResponseEntity<>(students, HttpStatus.OK);
    }

    // GET student by ID
    @GetMapping("/{id}")
    public ResponseEntity<Student> getStudentById(@PathVariable("id") Long id) {
        try {
            Student student = studentService.getStudentById(id);
            return new ResponseEntity<>(student, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    // POST new student
    @PostMapping
    public ResponseEntity<Student> createStudent(@RequestBody Student student) {
        try {
            Student createdStudent = studentService.addStudent(student);
            return new ResponseEntity<>(createdStudent, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    // PUT update existing student
    @PutMapping("/{id}")
    public ResponseEntity<Student> updateStudent(@PathVariable("id") Long id, @RequestBody Student student) {
        try {
            // Ensure the student exists before attempting to update
            student.setId(id); // Ensures the ID remains the same during update
            Student updatedStudent = studentService.updateStudent(student);
            return new ResponseEntity<>(updatedStudent, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    // DELETE student by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteStudent(@PathVariable("id") Long id) {
        try {
            studentService.deleteStudent(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // DELETE all students (for testing or cleanup)
    @DeleteMapping
    public ResponseEntity<HttpStatus> deleteAllStudents() {
        try {
            List<Student> students = studentService.getAllStudents();
            if (!students.isEmpty()) {
                students.forEach(student -> studentService.deleteStudent(student.getId()));
            }
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
