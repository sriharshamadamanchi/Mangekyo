package com.anime.mangekyo.model.stream;

import java.util.List;
import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("jsonschema2pojo")
public class EpisodeLinksModel {

    @SerializedName("headers")
    @Expose
    private Headers headers;
    @SerializedName("sources")
    @Expose
    private List<Source> sources;
    @SerializedName("download")
    @Expose
    private String download;

    public Headers getHeaders() {
        return headers;
    }

    public void setHeaders(Headers headers) {
        this.headers = headers;
    }

    public List<Source> getSources() {
        return sources;
    }

    public void setSources(List<Source> sources) {
        this.sources = sources;
    }

    public String getDownload() {
        return download;
    }

    public void setDownload(String download) {
        this.download = download;
    }

}