package com.will.enrollmentmanager.controller;

import com.will.enrollmentmanager.exception.ResourceNotFoundException;
import com.will.enrollmentmanager.model.Course;
import com.will.enrollmentmanager.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@CrossOrigin(origins = "*")
public class CourseController {

    @Autowired
    private CourseRepository courseRepository;

    @GetMapping("/courses")
    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }

    @GetMapping("/courses/{courseId}")
    public Course getCourse(@PathVariable Long courseId) {
        return courseRepository.findById(courseId).orElseThrow(()->new ResourceNotFoundException("CourseId "+courseId+" not found"));
    }

    @PostMapping("/courses")
    public Course createCourse(@Valid @RequestBody Course course) {
        return courseRepository.save(course);
    }


    @PatchMapping("/courses/{courseId}")
    public Course updateCourse(@PathVariable Long courseId, @Valid @RequestBody Course courseRequest) {
        return courseRepository.findById(courseId).map(
                course -> {
                    course.setName(courseRequest.getName());
                    course.setDescription(courseRequest.getDescription());
                    return courseRepository.save(course);
                }
        ).orElseThrow(()->new ResourceNotFoundException("CourseId "+courseId+" not found"));
    }

    @DeleteMapping("/courses/{courseId}")
    public ResponseEntity<?> deleteCourse(@PathVariable Long courseId) {
        return courseRepository.findById(courseId).map(course -> {
            courseRepository.delete(course);
            return ResponseEntity.ok().build();
        }).orElseThrow(()-> new ResourceNotFoundException("CourseId "+courseId+" not found"));
    }
}
