package com.cmpt276.asn2.models;

public class Users {
    private String name; 
    private int weight; 
    private int height; 
    private String hairColour; 
    private int gpa;
    
    public Users() {
    }

    public Users(String name, int weight, int height, String hairColour, int gpa) {
        this.name = name;
        this.weight = weight;
        this.height = height;
        this.hairColour = hairColour;
        this.gpa = gpa;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public int getWeight() {
        return weight;
    }
    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getHeight() {
        return height;
    }
    public void setHeight(int height) {
        this.height = height;
    }

    public String getHairColour() {
        return hairColour;
    }
    public void setHairColour(String hairColour) {
        this.hairColour = hairColour;
    }
    
    public int getGpa() {
        return gpa;
    }
    public void setGpa(int gpa) {
        this.gpa = gpa;
    } 
    
}
