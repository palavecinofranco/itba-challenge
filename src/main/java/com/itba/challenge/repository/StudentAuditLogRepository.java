package com.itba.challenge.repository;

import com.itba.challenge.model.entity.StudentAuditLog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentAuditLogRepository extends JpaRepository<StudentAuditLog, Long> {
}
