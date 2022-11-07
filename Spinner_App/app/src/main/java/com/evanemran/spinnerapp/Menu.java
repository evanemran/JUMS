package com.evanemran.spinnerapp;

import java.io.Serializable;

public class Menu implements Serializable {

    public Menu(String name,int details, String image) {
        this.name = name;
        this.image = image;
        this.details = details;
    }

    String name = "";
    int details = R.string.app_name;
    String image = "";
}
