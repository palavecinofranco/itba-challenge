package com.itba.challenge.controller;

import com.itba.challenge.controller.response.StudentResponse;
import com.itba.challenge.dto.StudentDTO;
import com.itba.challenge.service.StudentService;
import com.itba.challenge.utils.exception.StudentNotFoundException;
import lombok.AllArgsConstructor;
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
    public ResponseEntity<StudentResponse> getById(@PathVariable Long id) throws StudentNotFoundException {
        return ResponseEntity.ok(studentService.getStudentById(id));
    }

    @GetMapping("/create")
    public ResponseEntity<StudentResponse> create(@RequestBody StudentDTO studentDTO) {
        return new ResponseEntity<>(studentService.createStudent(studentDTO), HttpStatus.CREATED);
    }

    @GetMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) throws StudentNotFoundException {
        studentService.deleteStudent(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/update")
    public ResponseEntity<StudentResponse> update(@RequestBody StudentDTO studentDTO) throws StudentNotFoundException {
        return ResponseEntity.ok(studentService.updateStudent(studentDTO));
    }



}
