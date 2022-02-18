package com.example.task.network.getUserData;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MetaData {
    @SerializedName("pagination")
    @Expose
    private PaginationData paginationData;

    public PaginationData getPagination() {
        return paginationData;
    }

    public void setPagination(PaginationData paginationData) {
        this.paginationData = paginationData;
    }
}
