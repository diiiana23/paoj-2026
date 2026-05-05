package com.pao.laboratory09.exercise1;

import java.io.Serializable;

public class Tranzactie implements Serializable {
    private static final long serialVersionUID = 1L;

    public int id;
    public double suma;
    public String data;
    public String contSursa;
    public String contDestinatie;
    public TipTranzactie tip;

    public transient String note;
}