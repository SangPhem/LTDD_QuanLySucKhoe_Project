package com.example.doan;

public class FirebaseModel {

    private String weight;
    private String date;

    public FirebaseModel(){

    }

    public FirebaseModel(String weight, String date){
        this.weight = weight;
        this.date = date;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}


