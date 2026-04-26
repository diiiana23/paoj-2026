package com.pao.proiect.catalog.service;

import com.pao.proiect.catalog.model.Profesor;

import java.util.*;

public class ProfesorService {
    private static ProfesorService instance;
    private Map<Integer, Profesor> profesori = new HashMap<>();

    private ProfesorService() {}

    public static ProfesorService getInstance() {
        if (instance == null)
            instance = new ProfesorService();
        return instance;
    }

    public void adaugaProfesor(Profesor profesor){
        profesori.put(profesor.getId(), profesor);
    }
    public Profesor getProfesor(int id){
        return profesori.get(id);
    }
    public void stergeProfesor(int id){
        profesori.remove(id);
    }
    public List<Profesor> getTotiProfesorii(){
        return new ArrayList<>(profesori.values());
    }
}