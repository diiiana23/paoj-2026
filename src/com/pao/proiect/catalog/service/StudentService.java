package com.pao.proiect.catalog.service;

import com.pao.proiect.catalog.model.Student;
import com.pao.proiect.catalog.exception.StudentNotFoundException;

import java.util.*;

public class StudentService {
    private static StudentService instance;
    private Map<Integer, Student> studenti = new HashMap<>();

    private StudentService() {}

    public static StudentService getInstance() {
        if (instance == null)
            instance = new StudentService();
        return instance;
    }

    public void adaugaStudent(Student student){
        studenti.put(student.getId(), student);
    }
    public Student getStudent(int id){
        if (!studenti.containsKey(id))
            throw new StudentNotFoundException("Studentul nu exista!");
        return studenti.get(id);
    }
    public Student cautaDupaNume(String nume){
        for (Student s : studenti.values())
            if (s.getNume().equalsIgnoreCase(nume))
                return s;
        throw new StudentNotFoundException("Studentul nu exista!");
    }
    public void stergeStudent(int id){
        if (!studenti.containsKey(id))
            throw new StudentNotFoundException("Studentul nu exista!");
        studenti.remove(id);
    }
    public List<Student> getTotiStudentii(){
        return new ArrayList<>(studenti.values());
    }
}