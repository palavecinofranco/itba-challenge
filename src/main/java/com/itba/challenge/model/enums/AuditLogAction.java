package com.itba.challenge.model.enums;

import lombok.Getter;

@Getter
public enum AuditLogAction {
    CREATE("Create"),
    UPDATE("Update"),
    DELETE("Delete");

    private final String action;

    AuditLogAction(String action) {
        this.action = action;
    }

}
