package com.korzhuevadaria.catpic.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class PhotoItem {
    @SerializedName("id")
    @Expose
    private int id;

    @SerializedName("album_id")
    @Expose
    private int albumId;

    @SerializedName("owner_id")
    @Expose
    private int ownerId;

    @SerializedName("user_id")
    @Expose
    private int userId;

    @SerializedName("sizes")
    @Expose
    private List<PhotoSize> photoSizes = new ArrayList<PhotoSize>();;

    @SerializedName("text")
    @Expose
    private String text;

    @SerializedName("date")
    @Expose
    private String date;

    @SerializedName("post_id")
    @Expose
    private String postId;

    public PhotoItem(){}

    public PhotoItem(int id, int albumId, int ownerId, int userId, List<PhotoSize> sizes, String text, String date, String postId){
        this.id = id;
        this.albumId = albumId;
        this.ownerId = ownerId;
        this.userId = userId;
        this.photoSizes = sizes;
        this.text = text;
        this.date = date;
        this.postId = postId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAlbumId() {
        return albumId;
    }

    public void setAlbumId(int albumId) {
        this.albumId = albumId;
    }

    public int getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(int ownerId) {
        this.ownerId = ownerId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public List<PhotoSize> getPhotoSizes() {
        return photoSizes;
    }

    public void setPhotoSizes(List<PhotoSize> photoSizes) {
        this.photoSizes = photoSizes;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getPostId() {
        return postId;
    }

    public void setPostId(String postId) {
        this.postId = postId;
    }
}
