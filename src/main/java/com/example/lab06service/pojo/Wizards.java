package com.example.lab06service.pojo;


import java.util.ArrayList;
import java.util.List;

public class Wizards {
    private List<Wizard> wizards;

    public Wizards(){
        this.wizards = new ArrayList<>();
    }

    public List<Wizard> getWiz(){
        return wizards;
    }

    public void setWiz(List<Wizard> wizards){
        this.wizards = wizards;
    }
}
