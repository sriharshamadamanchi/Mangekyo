package com.anime.mangekyo.model.stream;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import javax.annotation.Generated;

@Generated("jsonschema2pojo")
public class Source {

    @SerializedName("url")
    @Expose
    private String url;
    @SerializedName("isM3U8")
    @Expose
    private Boolean isM3U8;
    @SerializedName("quality")
    @Expose
    private String quality;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Boolean getIsM3U8() {
        return isM3U8;
    }

    public void setIsM3U8(Boolean isM3U8) {
        this.isM3U8 = isM3U8;
    }

    public String getQuality() {
        return quality;
    }

    public void setQuality(String quality) {
        this.quality = quality;
    }

}