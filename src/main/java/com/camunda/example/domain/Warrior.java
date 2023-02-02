package com.camunda.example.domain;

import java.io.Serializable;

public class Warrior implements Serializable {

    private String name;
    private String title;
    private Integer hp;
    private Boolean isAlive;


    private static final long serialVersionUID = 1L;

    public Warrior() {
    }

    public Warrior(String name, String title, Integer hp, Boolean isAlive) {
        this.name = name;
        this.title = title;
        this.hp = hp;
        this.isAlive = isAlive;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getHp() {
        return hp;
    }

    public void setHp(Integer hp) {
        this.hp = hp;
    }

    public Boolean getAlive() {
        return isAlive;
    }

    public void setAlive(Boolean alive) {
        isAlive = alive;
    }
}
