package com.will.enrollmentmanager.repository;

import com.will.enrollmentmanager.model.Enrollment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EnrollmentRepository extends JpaRepository<Enrollment, Long> {

    @Query("SELECT e from Enrollment e where e.student.id = ?1")
    List<Enrollment> findEnrollmentsByStudentId(Long id);

    @Query("SELECT e from Enrollment e where e.course.id = ?1")
    List<Enrollment> findEnrollmentByCourseId(Long id);
}
