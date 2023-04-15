package com.anime.mangekyo.model.details;

import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("jsonschema2pojo")
public class Title {

    @SerializedName("romaji")
    @Expose
    private String romaji;
    @SerializedName("english")
    @Expose
    private String english;
    @SerializedName("native")
    @Expose
    private String _native;

    public String getRomaji() {
        return romaji;
    }

    public void setRomaji(String romaji) {
        this.romaji = romaji;
    }

    public String getEnglish() {
        return english;
    }

    public void setEnglish(String english) {
        this.english = english;
    }

    public String getNative() {
        return _native;
    }

    public void setNative(String _native) {
        this._native = _native;
    }

}