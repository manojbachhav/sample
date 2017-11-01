package com.manoj.sampleapp.model;

/**
 * Created by manoj on 1/11/17.
 */


public class ItemModel {
    private int id;
    private String title;

    public ItemModel() {
    }

    public ItemModel(int id, String title) {
        this.title = title;
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String name) {
        this.title = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

}