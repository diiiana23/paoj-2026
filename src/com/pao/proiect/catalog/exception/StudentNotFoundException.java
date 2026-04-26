package com.pao.proiect.catalog.exception;

public class StudentNotFoundException extends RuntimeException {
    public StudentNotFoundException(String mesaj) {
        super(mesaj);
    }
}