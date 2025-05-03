package com.itba.challenge.service;

import com.itba.challenge.controller.response.StudentResponse;
import com.itba.challenge.dto.StudentDTO;
import com.itba.challenge.utils.exception.StudentNotFoundException;

import java.util.List;

public interface StudentService {
    StudentResponse createStudent(StudentDTO studentDTO);
    StudentResponse updateStudent(StudentDTO studentDTO) throws StudentNotFoundException;
    void deleteStudent(Long id) throws StudentNotFoundException;
    StudentResponse getStudentById(Long id) throws StudentNotFoundException;
    List<StudentResponse> getAllStudents();
}
