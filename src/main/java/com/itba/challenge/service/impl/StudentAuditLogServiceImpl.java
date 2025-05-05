package com.itba.challenge.service.impl;

import com.itba.challenge.model.entity.StudentAuditLog;
import com.itba.challenge.repository.StudentAuditLogRepository;
import com.itba.challenge.service.StudentAuditLogService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Slf4j
public class StudentAuditLogServiceImpl implements StudentAuditLogService {

    private final StudentAuditLogRepository studentAuditLogRepository;

    //Lleva registro de los cambios realizados en los Students.
    @Async
    public void saveAuditLog(Long studentId, String action){
        StudentAuditLog studentAuditLog = StudentAuditLog.builder()
                .studentId(studentId)
                .action(action)
                .timestamp(LocalDateTime.now())
                .build();
        studentAuditLogRepository.save(studentAuditLog);
        log.info("Audit log saved for student with id {}: {}", studentId, action);
    }
}
