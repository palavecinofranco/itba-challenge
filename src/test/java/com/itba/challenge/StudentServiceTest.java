package com.itba.challenge;

import com.itba.challenge.controller.request.StudentCreateRequest;
import com.itba.challenge.controller.response.StudentResponse;
import com.itba.challenge.dto.mapper.StudentMapper;
import com.itba.challenge.model.entity.Student;
import com.itba.challenge.model.enums.AuditLogAction;
import com.itba.challenge.repository.StudentRepository;
import com.itba.challenge.service.StudentAuditLogService;
import com.itba.challenge.service.impl.StudentServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class StudentServiceTest {

    @Mock
    private StudentRepository studentRepository;

    @Mock
    private StudentMapper studentMapper;

    @Mock
    private StudentAuditLogService studentAuditLogService;

    @InjectMocks
    private StudentServiceImpl studentService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldCreateStudent() {
        StudentCreateRequest request = StudentCreateRequest.builder()
                .name("Franco")
                .lastname("Palavecino")
                .dni("12345678")
                .email("fp@mail.com")
                .address("Calle 123")
                .build();

        Student studentEntity = Student.builder()
                .id(1L)
                .dni("12345678")
                .name("Franco")
                .lastname("Palavecino")
                .email("fp@mail.com")
                .address("Calle 123")
                .build();
        StudentResponse expectedResponse = StudentResponse.builder()
                .fullName("Franco Palavecino")
                .dni("12345678")
                .email("fp@mail.com")
                .address("Calle 123")
                .build();

        when(studentMapper.toEntity(request)).thenReturn(studentEntity);
        when(studentRepository.save(studentEntity)).thenReturn(studentEntity);
        when(studentMapper.toResponse(studentEntity)).thenReturn(expectedResponse);

        StudentResponse result = studentService.createStudent(request);

        assertNotNull(result);
        assertEquals(expectedResponse.dni(), result.dni());
        assertEquals(expectedResponse.fullName(), result.fullName());

        verify(studentRepository).save(studentEntity);
        verify(studentAuditLogService).saveAuditLog(studentEntity.getId(), AuditLogAction.CREATE.getAction());
    }
}