package com.example.demo;

public class Person {
    private int id;
    private String name;

    // No-argument constructor (required by Jackson)
    public Person() {
        // Jackson needs this
    }

    // Constructor (el def __init__ de python)
    public Person(int id, String name) {
        this.id = id;
        this.name = name;
    }

    // Getters and setters
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
}
