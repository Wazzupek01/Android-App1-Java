package com.pedrycz.app1;

public class Grade {
    private String name;
    private int value;

    public Grade(String name, int ocena) {
        this.name = name;
        this.value = ocena;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
