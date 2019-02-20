package com.yodo.caz.myapplication;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;

public class Category implements Serializable {

    private String id;
    private String name;


    public Category(){

    }

    public Category(JSONObject categoryJson){
        try {
            this.id = categoryJson.getString("id");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        try {
            this.name = categoryJson.getString("name");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    public Category(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Category{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
