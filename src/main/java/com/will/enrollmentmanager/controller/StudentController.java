package com.will.enrollmentmanager.controller;

import com.will.enrollmentmanager.exception.ResourceNotFoundException;
import com.will.enrollmentmanager.model.Student;
import com.will.enrollmentmanager.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class StudentController {

    @Autowired
    private StudentRepository studentRepository;

    @GetMapping("/students")
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    @GetMapping("/students/{studentId}")
    public Student getStudent(@PathVariable Long studentId) {
        return studentRepository.findById(studentId).orElseThrow(()->new ResourceNotFoundException("StudentId "+studentId+" not found"));
    }

    @PostMapping("/students")
    public Student createStudent(@Valid @RequestBody Student student) {
        return studentRepository.save(student);
    }


    @PutMapping("/students/{studentId}")
    public Student updateStudent(@PathVariable Long studentId, @Valid @RequestBody Student studentRequest) {
        return studentRepository.findById(studentId).map(
                student -> {
                    student.setName(studentRequest.getName());
                    student.setAge(studentRequest.getAge());
                    student.setGender(studentRequest.getGender());
                    student.setCourses(studentRequest.getCourses());
                    return studentRepository.save(student);
                }
        ).orElseThrow(()->new ResourceNotFoundException("StudentId "+studentId+" not found"));
    }

    @DeleteMapping("/students/{studentId}")
    public ResponseEntity<?> deleteStudent(@PathVariable Long studentId) {
        return studentRepository.findById(studentId).map(student -> {
            studentRepository.delete(student);
            return ResponseEntity.ok().build();
        }).orElseThrow(()-> new ResourceNotFoundException("StudentId "+studentId+" not found"));
    }
}
