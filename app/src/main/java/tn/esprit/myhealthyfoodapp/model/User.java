package tn.esprit.myhealthyfoodapp.model;

import java.io.Serializable;

public class User implements Serializable {
    private String name;
    private int age;
    private float height;
    private float weight;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public float getHeight() {
        return height;
    }

    public void setHeight(float height) {
        this.height = height;
    }

    public float getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }

    public User(String name, int age, float height, float weight) {
        this.name = name;
        this.age = age;
        this.height = height;
        this.weight = weight;
    }
}
