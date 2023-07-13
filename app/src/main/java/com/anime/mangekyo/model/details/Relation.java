package com.anime.mangekyo.model.details;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import javax.annotation.Generated;

@Generated("jsonschema2pojo")
public class Relation {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("relationType")
    @Expose
    private String relationType;
    @SerializedName("malId")
    @Expose
    private Integer malId;
    @SerializedName("title")
    @Expose
    private Title__2 title;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("episodes")
    @Expose
    private Integer episodes;
    @SerializedName("image")
    @Expose
    private String image;
    @SerializedName("color")
    @Expose
    private String color;
    @SerializedName("type")
    @Expose
    private Object type;
    @SerializedName("cover")
    @Expose
    private String cover;
    @SerializedName("rating")
    @Expose
    private Object rating;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRelationType() {
        return relationType;
    }

    public void setRelationType(String relationType) {
        this.relationType = relationType;
    }

    public Integer getMalId() {
        return malId;
    }

    public void setMalId(Integer malId) {
        this.malId = malId;
    }

    public Title__2 getTitle() {
        return title;
    }

    public void setTitle(Title__2 title) {
        this.title = title;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getEpisodes() {
        return episodes;
    }

    public void setEpisodes(Integer episodes) {
        this.episodes = episodes;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Object getType() {
        return type;
    }

    public void setType(Object type) {
        this.type = type;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public Object getRating() {
        return rating;
    }

    public void setRating(Object rating) {
        this.rating = rating;
    }

}