package com.will.enrollmentmanager.controller;

import com.will.enrollmentmanager.exception.ResourceNotFoundException;
import com.will.enrollmentmanager.model.Course;
import com.will.enrollmentmanager.model.Enrollment;
import com.will.enrollmentmanager.model.Student;
import com.will.enrollmentmanager.repository.CourseRepository;
import com.will.enrollmentmanager.repository.EnrollmentRepository;
import com.will.enrollmentmanager.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;
import java.util.List;

@RestController
@CrossOrigin(origins = "*")
public class EnrollmentController {

    @Autowired
    private EnrollmentRepository enrollmentRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private CourseRepository courseRepository;

    //test add enrollment
    @GetMapping("/enrollment1")
    public Enrollment addToEnrollment() {
        Student student1 = studentRepository.findById((long)2).get();
        Course course1 = courseRepository.findById((long)1).get();

        Enrollment enrollment1 = new Enrollment();
        enrollment1.setStudent(student1);
        enrollment1.setCourse(course1);
        enrollment1.setFee("500$");

        return enrollmentRepository.save(enrollment1);
    }

    @GetMapping("/enrollments")
    public List<Enrollment> getAllEnrollment() {
        return enrollmentRepository.findAll();
    }

    @PostMapping("/enrollments")
    public Enrollment createEnrollment(@Valid @RequestBody Enrollment enrollment) {
        return enrollmentRepository.save(enrollment);
    }

    @DeleteMapping("/enrollments/{enrollmentId}")
    public ResponseEntity<?> deleteEnrollment(@PathVariable Long enrollmentId) {
        return enrollmentRepository.findById(enrollmentId).map(enrollment -> {
            enrollmentRepository.delete(enrollment);
            return ResponseEntity.ok().build();
        }).orElseThrow(()-> new ResourceNotFoundException("EnrollmentId "+enrollmentId+" not found" ));
    }
}
