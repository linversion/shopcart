package com.linversion.commondemo;

public class Item {
    private String title;
    private String content;
    private boolean choosed = false;
    private String shopName;
    private boolean groupChoosed = false;
    private int groupPosition;

    public Item() {
    }

    public boolean isChoosed() {
        return choosed;
    }

    public void setChoosed(boolean choosed) {
        this.choosed = choosed;
    }

    public void setTitle(String title) {
        this.title = title;
    }
    public String getTitle() {
        return title;
    }

    public void setContent(String content) {
        this.content = content;
    }
    public String getContent() {
        return content;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public String getShopName() {
        return shopName;
    }

    public void setGroupChoosed(boolean groupChoosed) {
        this.groupChoosed = groupChoosed;
    }

    public boolean isGroupChoosed() {
        return groupChoosed;
    }

    public void setGroupPosition(int groupPosition) {
        this.groupPosition = groupPosition;
    }

    public int getGroupPosition() {
        return groupPosition;
    }
}
