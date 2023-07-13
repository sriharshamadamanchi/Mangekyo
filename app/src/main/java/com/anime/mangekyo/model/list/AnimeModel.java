package com.anime.mangekyo.model.list;

import androidx.annotation.NonNull;
import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import com.anime.mangekyo.utility.TypeConverterUtil;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import javax.annotation.Generated;

@Generated("jsonschema2pojo")
@Entity(tableName = "animeList")
public class AnimeModel extends BaseObservable {

    @NonNull
    @PrimaryKey
    public String type;
    @SerializedName("currentPage")
    @Expose
    @ColumnInfo(name = "currentPage")
    private Integer currentPage;

    @TypeConverters(TypeConverterUtil.class)
    @SerializedName("results")
    @Expose
    @ColumnInfo(name = "results")
    private List<Result> results;

    @Bindable
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Bindable
    public Integer getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
    }

    @Bindable
    public List<Result> getResults() {
        return results;
    }

    public void setResults(List<Result> results) {
        this.results = results;
    }

}