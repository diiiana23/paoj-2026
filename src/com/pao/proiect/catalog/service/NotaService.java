package com.pao.proiect.catalog.service;

import com.pao.proiect.catalog.model.*;
import com.pao.proiect.catalog.exception.NotaInvalidaException;

public class NotaService {
    private static NotaService instance;

    private NotaService() {}

    public static NotaService getInstance() {
        if (instance == null)
            instance = new NotaService();
        return instance;
    }

    public void adaugaNota(Student student, int valoare, Materie materie){
        if(valoare<1 || valoare>10)
            throw new NotaInvalidaException("Nota trebuie sa fie intre 1 si 10!");
        Nota nota = new Nota(valoare, materie);
        student.adaugaNota(nota);
    }
}