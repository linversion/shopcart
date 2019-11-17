package com.linversion.commondemo;

import java.util.List;

public class bean{

    private String name;
    private List<Item> items;

    private boolean choosed;
    private boolean groupChoosed;

    public bean() {

    }

    public boolean isChoosed() {
        return choosed;
    }

    public void setChoosed(boolean choosed) {
        this.choosed = choosed;
    }

    public void setName(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }

    public void setList(List<Item> items) {
        this.items = items;
    }
    public List<Item> getItems() {
        return items;
    }

    public void setGroupChoosed(boolean groupChoosed) {
        this.groupChoosed = groupChoosed;
    }

    public boolean isGroupChoosed() {
        return groupChoosed;
    }
}
