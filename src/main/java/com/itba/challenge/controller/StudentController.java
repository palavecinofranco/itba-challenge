package com.itba.challenge.controller;

import com.itba.challenge.controller.request.StudentCreateRequest;
import com.itba.challenge.controller.request.StudentUpdateRequest;
import com.itba.challenge.controller.response.StudentResponse;
import com.itba.challenge.service.StudentService;
import com.itba.challenge.utils.exception.StudentNotFoundException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/students")
@RequiredArgsConstructor
public class StudentController {
    private final StudentService studentService;

    @GetMapping
    public ResponseEntity<List<StudentResponse>> getAllStudents() {
        return ResponseEntity.ok(studentService.getAllStudents());
    }

    @GetMapping("/{id}")
    public ResponseEntity<StudentResponse> getStudentById(@PathVariable final Long id) throws StudentNotFoundException {
        return ResponseEntity.ok(studentService.getStudentById(id));
    }

    @PostMapping("/create")
    public ResponseEntity<StudentResponse> createStudent(@RequestBody @Valid final StudentCreateRequest request) {
        return new ResponseEntity<>(studentService.createStudent(request), HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteStudent(@PathVariable final Long id) throws StudentNotFoundException {
        studentService.deleteStudent(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<StudentResponse> updateStudent(@PathVariable final Long id, @RequestBody final StudentUpdateRequest request) throws StudentNotFoundException {
        return ResponseEntity.ok(studentService.updateStudent(id, request));
    }



}
