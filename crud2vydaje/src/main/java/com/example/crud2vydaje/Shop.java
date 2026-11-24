package com.example.crud2vydaje;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Shop {
    private IntegerProperty id = new SimpleIntegerProperty();
    private StringProperty name =  new SimpleStringProperty();

    public Shop(int id, String name) {
        setName(name);
        setId(id);
    }


    public int getId() {
        return id.get();
    }

    public IntegerProperty idProperty() {
        return id;
    }

    public void setId(int id) {
        this.id.set(id);
    }

    public String getName() {
        return name.get();
    }

    public StringProperty nameProperty() {
        return name;
    }

    public void setName(String name) {
        if (name.length() > 75)
            throw new RuntimeException("dlzka mena prilis dlha");
        this.name.set(name);
    }




    @Override
    public String toString() {
        return getName();
    }
}
