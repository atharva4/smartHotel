package com.example.firehotel;

public class getBooked {
    public String name, area, cost,toggle;

    getBooked() {
    }

    public getBooked(String name, String area, String cost, String toggle) {
        this.name = name;
        this.area = area;
        this.cost = cost;
        this.toggle = toggle;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getCost() {
        return cost;
    }

    public void setCost(String cost) {
        this.cost = cost;
    }

    public String getToggle() {
        return toggle;
    }

    public void setToggle(String toggle) {
        this.toggle = toggle;
    }
}
