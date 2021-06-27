package com.example.firehotel;

public class model {

    String Name,Area,Cost,purl;

    model(){

    }

    public model(String Name, String Area, String Cost, String purl) {
        this.Name = Name;
        this.Area = Area;
        this.Cost = Cost;
        this.purl = purl;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getArea() {
        return Area;
    }

    public void setArea(String area) {
        Area = area;
    }

    public String getCost() {
        return Cost;
    }

    public void setCost(String cost) {
        Cost = cost;
    }

    public String getPurl() {
        return purl;
    }

    public void setPurl(String purl) {
        this.purl = purl;
    }

}
