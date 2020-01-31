package com.korzhuevadaria.catpic.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class VkPhotosResponse{
    @SerializedName("response")
    @Expose
    public PhotoResponse response;

    public VkPhotosResponse(){}

    public VkPhotosResponse(PhotoResponse response){
        this.response = response;
    }

    public PhotoResponse getResponse() {
        return response;
    }

    public void setResponse(PhotoResponse response) {
        this.response = response;
    }
}
