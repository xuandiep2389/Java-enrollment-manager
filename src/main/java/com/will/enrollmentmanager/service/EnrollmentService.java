package com.will.enrollmentmanager.service;

import com.will.enrollmentmanager.repository.EnrollmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional(rollbackFor = Exception.class)
public class EnrollmentService {

   @Autowired
    private EnrollmentRepository enrollmentRepository;





}
