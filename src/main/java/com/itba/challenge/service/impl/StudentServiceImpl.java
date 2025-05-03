package com.itba.challenge.service.impl;

import com.itba.challenge.controller.response.StudentResponse;
import com.itba.challenge.dto.StudentDTO;
import com.itba.challenge.dto.mapper.StudentMapper;
import com.itba.challenge.entity.Student;
import com.itba.challenge.repository.StudentRepository;
import com.itba.challenge.service.StudentService;
import com.itba.challenge.utils.MessageUtil;
import com.itba.challenge.utils.exception.StudentNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;
    private final StudentMapper studentMapper;
    private final MessageUtil messageUtil;

    @Override
    public StudentResponse createStudent(final StudentDTO studentDTO) {
        Student studentEntity = studentRepository.save(studentMapper.toEntity(studentDTO));
        return studentMapper.toResponse(studentEntity);
    }

    @Override
    public StudentResponse updateStudent(final StudentDTO studentDTO) throws StudentNotFoundException {
        Student studentEntity = studentRepository.findById(studentDTO.id())
                .orElseThrow(() -> new StudentNotFoundException(messageUtil.getMessage("error.student.not.found", null)));
        studentEntity.setName(studentDTO.name());
        studentEntity.setLastname(studentDTO.lastname());
        studentEntity.setEmail(studentDTO.email());
        studentEntity.setAddress(studentDTO.address());
        return studentMapper.toResponse(studentRepository.save(studentEntity));
    }

    @Override
    public void deleteStudent(Long id) throws StudentNotFoundException {
        Student studentEntity = studentRepository.findById(id)
                .orElseThrow(() -> new StudentNotFoundException(messageUtil.getMessage("error.student.not.found", null)));
        studentRepository.delete(studentEntity);
    }

    @Override
    public StudentResponse getStudentById(Long id) throws StudentNotFoundException {
        Student studentEntity = studentRepository.findById(id)
                .orElseThrow(() -> new StudentNotFoundException(messageUtil.getMessage("error.student.not.found", null)));
        return studentMapper.toResponse(studentEntity);
    }

    @Override
    public List<StudentResponse> getAllStudents() {
        List<Student> students = studentRepository.findAll();
        return students.stream()
                .map(studentMapper::toResponse)
                .toList();
    }
}
