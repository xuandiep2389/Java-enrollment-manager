package com.will.enrollmentmanager.controller;

import com.will.enrollmentmanager.model.Enrollment;
import com.will.enrollmentmanager.repository.EnrollmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class EnrollmentController {

    @Autowired
    private EnrollmentRepository enrollmentRepository;

    @GetMapping("/enrollments")
    public List<Enrollment> getAllEnrollment() {
        return enrollmentRepository.findAll();
    }

    @PostMapping("/enrollments")
    public Enrollment createEnrollment(@Valid @RequestBody Enrollment enrollment) {
        return enrollmentRepository.save(enrollment);
    }
}
