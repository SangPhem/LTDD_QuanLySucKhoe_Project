package com.example.doan;

public class WeightDataPoint {
    int xValue, yValue;

    public WeightDataPoint(int xValue, int yValue){
        this.xValue = xValue;
        this.yValue = yValue;
    }

    public WeightDataPoint(){

    }

    public int getxValue(){
        return xValue;
    }

    public int getyValue(){
        return yValue;
    }
}
