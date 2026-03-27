package com.pao.laboratory04.audit;

public record AuditEntry(String action, String target, String timestamp) { }