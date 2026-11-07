package com.example.firma;

import java.time.LocalDate;

public class Hardware {
    private String name, type, sn;
    private double buyPrice;
    private LocalDate endWarrantyDate;
    private static boolean printDate = false;
    public Hardware(String name, String type, String sn, double buyPrice, LocalDate endWarrantyDate) {
        if (name.isEmpty() || type.isEmpty() || sn.isEmpty() || buyPrice < 0.0)
            throw new RuntimeException("empty");


        this.name = name;
        this.type = type;
        this.sn = sn;
        this.buyPrice = buyPrice;
        this.endWarrantyDate = endWarrantyDate;
    }
    public static void setPrintState(boolean date) {
        printDate = date;
    }
    public String getName() {
        return name;
    }
    public String getType() {
        return type;
    }
    public double getBuyPrice() {
        return buyPrice;
    }
    public String getSn() {
        return sn;
    }
    public LocalDate getWarranty() {
        return endWarrantyDate;
    }

    public String getInfo() {
        return sn + " - " + name;
    }
    @Override
    public String toString() {
        if (!printDate)
            return getInfo();
        else
            return getInfoWarranty();
    }
    public String getInfoWarranty() {
        return sn + " - " + endWarrantyDate;
    }
}
