package com.itba.challenge.service.impl;

import com.itba.challenge.controller.response.StudentResponse;
import com.itba.challenge.dto.StudentDTO;
import com.itba.challenge.dto.mapper.StudentMapper;
import com.itba.challenge.model.entity.Student;
import com.itba.challenge.model.enums.AuditLogAction;
import com.itba.challenge.repository.StudentRepository;
import com.itba.challenge.service.StudentAuditLogService;
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
    private final StudentAuditLogService studentAuditLogService;

    @Override
    public StudentResponse createStudent(final StudentDTO studentDTO) {
        Student studentEntity = studentRepository.save(studentMapper.toEntity(studentDTO));
        studentAuditLogService.saveAuditLog(studentEntity.getId(), AuditLogAction.CREATE.getAction());
        return studentMapper.toResponse(studentEntity);
    }

    @Override
    public StudentResponse updateStudent(final Long id, final StudentDTO studentDTO) throws StudentNotFoundException {
        Student studentEntity = studentRepository.findById(id)
                .orElseThrow(() -> this.buildNotFoundException(id));
        studentEntity.setName(studentDTO.name());
        studentEntity.setLastname(studentDTO.lastname());
        studentEntity.setEmail(studentDTO.email());
        studentEntity.setAddress(studentDTO.address());
        studentAuditLogService.saveAuditLog(studentEntity.getId(), AuditLogAction.UPDATE.getAction());
        return studentMapper.toResponse(studentRepository.save(studentEntity));
    }

    @Override
    public void deleteStudent(final Long id) throws StudentNotFoundException {
        Student studentEntity = studentRepository.findById(id)
                .orElseThrow(() -> this.buildNotFoundException(id));
        studentRepository.delete(studentEntity);
        studentAuditLogService.saveAuditLog(studentEntity.getId(), AuditLogAction.DELETE.getAction());
    }

    @Override
    public StudentResponse getStudentById(final Long id) throws StudentNotFoundException {
        Student studentEntity = studentRepository.findById(id)
                .orElseThrow(() -> this.buildNotFoundException(id));
        return studentMapper.toResponse(studentEntity);
    }

    @Override
    public List<StudentResponse> getAllStudents() {
        List<Student> students = studentRepository.findAll();
        return students.stream()
                .map(studentMapper::toResponse)
                .toList();
    }

    private StudentNotFoundException buildNotFoundException(final Long id) {
        String userMessage = messageUtil.getMessage("error.student.not.found", null);
        String backendMessage = "Student with id " + id + " was not found in the database";
        return new StudentNotFoundException(userMessage, backendMessage);
    }


}
