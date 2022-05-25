package com.example.doan.FoodRecommend;

public class FoodsModel {
    private String title;
    private String descrip;
    private String image;

    private  FoodsModel(){}
    private  FoodsModel(String title, String descrip, String image){
        this.title = title;
        this.descrip = descrip;
        this.image = image;

    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescrip() {
        return descrip;
    }

    public void setDescrip(String descrip) {
        this.descrip = descrip;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
