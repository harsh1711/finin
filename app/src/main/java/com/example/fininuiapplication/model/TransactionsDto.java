package com.example.fininuiapplication.model;

public class TransactionsDto {
    private String name;
    private Double price;
    private String type;
    private String dateTime;

    public TransactionsDto(String name, Double price, String type, String dateTime) {
        this.name = name;
        this.price = price;
        this.type = type;
        this.dateTime = dateTime;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }
}
