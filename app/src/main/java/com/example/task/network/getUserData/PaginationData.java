package com.example.task.network.getUserData;

import com.example.task.network.getUserData.LinksData;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PaginationData {

    @SerializedName("total")
    @Expose
    private Integer total;
    @SerializedName("pages")
    @Expose
    private Integer pages;
    @SerializedName("page")
    @Expose
    private Integer page;
    @SerializedName("limit")
    @Expose
    private Integer limit;
    @SerializedName("links")
    @Expose
    private LinksData links;

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public Integer getPages() {
        return pages;
    }

    public void setPages(Integer pages) {
        this.pages = pages;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public LinksData getLinks() {
        return links;
    }

    public void setLinks(LinksData links) {
        this.links = links;
    }
}
