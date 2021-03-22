package com.example.gameHelper.Struct;

/**
 * GridView Item 自定义结构
 * 用于GridView布局*/
public class GridViewItem {
    private String name;
    private int icon_rid;
    private String url;

    public GridViewItem(String name, int icon_rid, String url){
        super();
        this.name = name;
        this.icon_rid = icon_rid;
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public int getIcon_rid() {
        return icon_rid;
    }

    public String getUrl() {
        return url;
    }
}
