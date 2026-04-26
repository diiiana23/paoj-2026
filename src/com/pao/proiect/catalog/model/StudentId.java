package com.pao.proiect.catalog.model;

public final class StudentId {
    private final int id;
    public StudentId(int id){
        this.id = id;
    }

    public int getId(){
        return id;
    }

    @Override
    public String toString(){
        return "StudentId (" + id + ")";
    }
    @Override
    public boolean equals(Object obj){
        if (this == obj)
            return true;
        if (!(obj instanceof StudentId))
            return false;
        StudentId s = (StudentId) obj;
        return id == s.id;
    }
    @Override
    public int hashCode(){
        return java.util.Objects.hash(id);
    }
}