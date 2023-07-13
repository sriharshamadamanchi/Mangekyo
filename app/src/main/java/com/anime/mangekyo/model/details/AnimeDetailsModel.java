package com.anime.mangekyo.model.details;

import androidx.annotation.NonNull;
import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import java.util.List;
import javax.annotation.Generated;

import com.anime.mangekyo.utility.TypeConverterUtil;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("jsonschema2pojo")
@Entity(tableName = "animeDetails")
public class AnimeDetailsModel extends BaseObservable {

    @SerializedName("id")
    @Expose
    @PrimaryKey
    @NonNull
    private String id;
    @SerializedName("title")
    @Expose
    @ColumnInfo(name = "title")
    @TypeConverters(TypeConverterUtil.class)
    private Title title;
    @SerializedName("malId")
    @Expose
    @ColumnInfo(name = "malId")
    private Integer malId;
    @SerializedName("synonyms")
    @Expose
    @Ignore
    private List<String> synonyms;
    @SerializedName("isLicensed")
    @Expose
    @ColumnInfo(name = "isLicensed")
    private Boolean isLicensed;
    @SerializedName("isAdult")
    @Expose
    @ColumnInfo(name = "isAdult")
    private Boolean isAdult;
    @SerializedName("countryOfOrigin")
    @Expose
    @ColumnInfo(name = "countryOfOrigin")
    private String countryOfOrigin;
    @SerializedName("image")
    @Expose
    @ColumnInfo(name = "image")
    private String image;
    @SerializedName("popularity")
    @Expose
    @ColumnInfo(name = "popularity")
    private Integer popularity;
    @SerializedName("color")
    @Expose
    @ColumnInfo(name = "color")
    private String color;
    @SerializedName("cover")
    @Expose
    @ColumnInfo(name = "cover")
    private String cover;
    @SerializedName("description")
    @Expose
    @ColumnInfo(name = "description")
    private String description;
    @SerializedName("status")
    @Expose
    @ColumnInfo(name = "status")
    private String status;
    @SerializedName("releaseDate")
    @Expose
    @ColumnInfo(name = "releaseDate")
    private Integer releaseDate;
    @SerializedName("startDate")
    @Expose
    @Ignore
    private StartDate startDate;
    @SerializedName("endDate")
    @Expose
    @Ignore
    private EndDate endDate;
    @SerializedName("totalEpisodes")
    @Expose
    @ColumnInfo(name = "totalEpisodes")
    private Integer totalEpisodes;
    @SerializedName("currentEpisode")
    @Expose
    @ColumnInfo(name = "currentEpisode")
    private Integer currentEpisode;
    @SerializedName("rating")
    @Expose
    @ColumnInfo(name = "rating")
    private Integer rating;
    @SerializedName("duration")
    @Expose
    @ColumnInfo(name = "duration")
    private Integer duration;
    @SerializedName("genres")
    @Expose
    @ColumnInfo(name = "genres")
    @TypeConverters(TypeConverterUtil.class)
    private List<String> genres;
    @SerializedName("season")
    @Expose
    @ColumnInfo(name = "season")
    private String season;
    @SerializedName("studios")
    @Expose
    @ColumnInfo(name = "studios")
    @TypeConverters(TypeConverterUtil.class)
    private List<String> studios;
    @SerializedName("subOrDub")
    @Expose
    @ColumnInfo(name = "subOrDub")
    private String subOrDub;
    @SerializedName("type")
    @Expose
    @ColumnInfo(name = "type")
    private String type;
    @SerializedName("recommendations")
    @Expose
    @Ignore
    private List<Recommendation> recommendations;
    @SerializedName("characters")
    @Expose
    @Ignore
    private List<Character> characters;
    @SerializedName("relations")
    @Expose
    @Ignore
    private List<Relation> relations;
    @SerializedName("episodes")
    @Expose
    @ColumnInfo(name = "episodes")
    @TypeConverters(TypeConverterUtil.class)
    private List<Episode> episodes;

    @Bindable
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Bindable
    public Title getTitle() {
        return title;
    }

    public void setTitle(Title title) {
        this.title = title;
    }

    @Bindable
    public Integer getMalId() {
        return malId;
    }

    public void setMalId(Integer malId) {
        this.malId = malId;
    }

    @Bindable
    public List<String> getSynonyms() {
        return synonyms;
    }

    public void setSynonyms(List<String> synonyms) {
        this.synonyms = synonyms;
    }

    @Bindable
    public Boolean getIsLicensed() {
        return isLicensed;
    }

    public void setIsLicensed(Boolean isLicensed) {
        this.isLicensed = isLicensed;
    }

    @Bindable
    public Boolean getIsAdult() {
        return isAdult;
    }

    public void setIsAdult(Boolean isAdult) {
        this.isAdult = isAdult;
    }

    @Bindable
    public String getCountryOfOrigin() {
        return countryOfOrigin;
    }

    public void setCountryOfOrigin(String countryOfOrigin) {
        this.countryOfOrigin = countryOfOrigin;
    }

    @Bindable
    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Bindable
    public Integer getPopularity() {
        return popularity;
    }

    public void setPopularity(Integer popularity) {
        this.popularity = popularity;
    }

    @Bindable
    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @Bindable
    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    @Bindable
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Bindable
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Bindable
    public Integer getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Integer releaseDate) {
        this.releaseDate = releaseDate;
    }

    @Bindable
    public StartDate getStartDate() {
        return startDate;
    }

    public void setStartDate(StartDate startDate) {
        this.startDate = startDate;
    }

    @Bindable
    public EndDate getEndDate() {
        return endDate;
    }

    public void setEndDate(EndDate endDate) {
        this.endDate = endDate;
    }

    @Bindable
    public Integer getTotalEpisodes() {
        return totalEpisodes;
    }

    public void setTotalEpisodes(Integer totalEpisodes) {
        this.totalEpisodes = totalEpisodes;
    }

    @Bindable
    public Integer getCurrentEpisode() {
        return currentEpisode;
    }

    public void setCurrentEpisode(Integer currentEpisode) {
        this.currentEpisode = currentEpisode;
    }

    @Bindable
    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    @Bindable
    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    @Bindable
    public List<String> getGenres() {
        return genres;
    }

    public void setGenres(List<String> genres) {
        this.genres = genres;
    }

    @Bindable
    public String getSeason() {
        return season;
    }

    public void setSeason(String season) {
        this.season = season;
    }

    @Bindable
    public List<String> getStudios() {
        return studios;
    }

    public void setStudios(List<String> studios) {
        this.studios = studios;
    }

    @Bindable
    public String getSubOrDub() {
        return subOrDub;
    }

    public void setSubOrDub(String subOrDub) {
        this.subOrDub = subOrDub;
    }

    @Bindable
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Bindable
    public List<Recommendation> getRecommendations() {
        return recommendations;
    }

    public void setRecommendations(List<Recommendation> recommendations) {
        this.recommendations = recommendations;
    }

    @Bindable
    public List<Character> getCharacters() {
        return characters;
    }

    public void setCharacters(List<Character> characters) {
        this.characters = characters;
    }

    @Bindable
    public List<Relation> getRelations() {
        return relations;
    }

    public void setRelations(List<Relation> relations) {
        this.relations = relations;
    }

    @Bindable
    public List<Episode> getEpisodes() {
        return episodes;
    }

    public void setEpisodes(List<Episode> episodes) {
        this.episodes = episodes;
    }
}