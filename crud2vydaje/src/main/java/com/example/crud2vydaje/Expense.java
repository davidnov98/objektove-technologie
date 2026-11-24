package com.example.crud2vydaje;

import javafx.beans.property.*;

import java.time.LocalDate;

public class Expense {
    private IntegerProperty id = new SimpleIntegerProperty();
    private DoubleProperty price  = new SimpleDoubleProperty();
    private ObjectProperty<LocalDate> date =  new SimpleObjectProperty<>();
    private ObjectProperty<Shop> shop =  new SimpleObjectProperty<>();
    private ObjectProperty<Category> category = new SimpleObjectProperty<>();




    public int getId() {
        return id.get();
    }

    public IntegerProperty idProperty() {
        return id;
    }

    public void setId(int id) {
        this.id.set(id);
    }


    public double getPrice() {
        return price.get();
    }

    public DoubleProperty priceProperty() {
        return price;
    }

    public void setPrice(double price) {
        this.price.set(price);
    }
    public LocalDate getDate() {
        return date.get();
    }
    public ObjectProperty<LocalDate> dateProperty() {
        return date;
    }
    public void setDate(LocalDate date) {
        this.date.set(date);
    }

    public Shop getShop() {
        return shop.get();
    }

    public ObjectProperty<Shop> shopProperty() {
        return shop;
    }
    public void setShop(Shop shop) {
        this.shop.set(shop);
    }


    public Category getCategory() {
        return category.get();
    }

    public ObjectProperty<Category> categoryProperty() {
        return category;
    }

    public void setCategory(Category category) {
        this.category.set(category);
    }



    public Expense(int id, double price, LocalDate date, Shop shop, Category category) {
        setId(id);
        setPrice(price);
        setDate(date);
        setShop(shop);
        setCategory(category);
    }
}
