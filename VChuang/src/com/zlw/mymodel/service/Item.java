package com.zlw.mymodel.service;

/**
 * Created by zlw on 2016/6/28 0028.
 */
public class Item {
    private int id;
    private String name;
    private String content;
    public Item() {

    }

    public Item(int id, String name, String content) {
        this.id = id;
        this.name = name;
        this.content = content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getContent() {
        return content;
    }

    public String getName() {
        return name;
    }


}
