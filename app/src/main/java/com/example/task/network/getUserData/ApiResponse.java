package com.example.task.network.getUserData;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ApiResponse {
    @SerializedName("meta")
    @Expose
    private MetaData meta;
    @SerializedName("data")
    @Expose
    private List<UserData> userData = null;

    public MetaData getMeta() {
        return meta;
    }

    public void setMeta(MetaData meta) {
        this.meta = meta;
    }

    public List<UserData> getUserData() {
        return userData;
    }

    public void setUserData(List<UserData> data) {
        this.userData = data;
    }
}
