package com.korzhuevadaria.catpic.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class PhotoResponse {
    @SerializedName("count")
    @Expose
    private int count;
    @SerializedName("items")
    @Expose
    private List<PhotoItem> items = new ArrayList<PhotoItem>();

    public PhotoResponse(){}

    public PhotoResponse(int count, List<PhotoItem> items){
        this.count = count;
        this.items = items;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<PhotoItem> getItems() {
        return items;
    }

    public void setItems(List<PhotoItem> items) {
        this.items = items;
    }
}
