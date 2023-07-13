package com.anime.mangekyo.model.stream;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import javax.annotation.Generated;

@Generated("jsonschema2pojo")
public class EpisodeLinksModel extends BaseObservable {

    @SerializedName("headers")
    @Expose
    private Headers headers;
    @SerializedName("sources")
    @Expose
    private List<Source> sources;
    @SerializedName("download")
    @Expose
    private String download;

    @Bindable
    public Headers getHeaders() {
        return headers;
    }

    public void setHeaders(Headers headers) {
        this.headers = headers;
    }

    @Bindable
    public List<Source> getSources() {
        return sources;
    }

    public void setSources(List<Source> sources) {
        this.sources = sources;
    }

    @Bindable
    public String getDownload() {
        return download;
    }

    public void setDownload(String download) {
        this.download = download;
    }

}