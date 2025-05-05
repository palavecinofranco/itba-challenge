package com.itba.challenge.service;

public interface StudentAuditLogService {
   void saveAuditLog(Long studentId, String action);
}
