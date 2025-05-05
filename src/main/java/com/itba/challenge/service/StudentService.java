package com.itba.challenge.service;

import com.itba.challenge.controller.request.StudentCreateRequest;
import com.itba.challenge.controller.request.StudentUpdateRequest;
import com.itba.challenge.controller.response.StudentResponse;
import com.itba.challenge.utils.exception.StudentNotFoundException;

import java.util.List;

public interface StudentService {
    StudentResponse createStudent(StudentCreateRequest request);
    StudentResponse updateStudent(Long id, StudentUpdateRequest request) throws StudentNotFoundException;
    void deleteStudent(Long id) throws StudentNotFoundException;
    StudentResponse getStudentById(Long id) throws StudentNotFoundException;
    List<StudentResponse> getAllStudents();
}
