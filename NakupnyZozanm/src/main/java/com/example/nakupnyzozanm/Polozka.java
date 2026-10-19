package com.example.nakupnyzozanm;

public class Polozka {
    private String nazov;
    private int pocet;

    public Polozka(String n, int p) {
        setNazov(n);
        setPocet(p);
    }
    public String getNazov() {return nazov;}
    public int getPocet() {return pocet;}
    public void setNazov(String n) {
        if (n.isEmpty()) {
            nazov = "nic";
        }
        else {
            nazov = n;
        }
    }
    public void setPocet(int p) {
        if (p < 0)
            pocet = 0;
        else
            pocet = p;
    }
    @Override
    public String toString() {
        return nazov + " - " + pocet + " ks";
    }
}
