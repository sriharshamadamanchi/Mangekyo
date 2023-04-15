package com.anime.mangekyo.model.details;

import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("jsonschema2pojo")
public class Title__2 {

    @SerializedName("romaji")
    @Expose
    private String romaji;
    @SerializedName("english")
    @Expose
    private Object english;
    @SerializedName("native")
    @Expose
    private String _native;
    @SerializedName("userPreferred")
    @Expose
    private String userPreferred;

    public String getRomaji() {
        return romaji;
    }

    public void setRomaji(String romaji) {
        this.romaji = romaji;
    }

    public Object getEnglish() {
        return english;
    }

    public void setEnglish(Object english) {
        this.english = english;
    }

    public String getNative() {
        return _native;
    }

    public void setNative(String _native) {
        this._native = _native;
    }

    public String getUserPreferred() {
        return userPreferred;
    }

    public void setUserPreferred(String userPreferred) {
        this.userPreferred = userPreferred;
    }

}