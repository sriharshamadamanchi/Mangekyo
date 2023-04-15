package com.anime.mangekyo.model.details;

import java.util.List;
import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("jsonschema2pojo")
public class AnimeDetailsModel {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("title")
    @Expose
    private Title title;
    @SerializedName("malId")
    @Expose
    private Integer malId;
    @SerializedName("synonyms")
    @Expose
    private List<String> synonyms;
    @SerializedName("isLicensed")
    @Expose
    private Boolean isLicensed;
    @SerializedName("isAdult")
    @Expose
    private Boolean isAdult;
    @SerializedName("countryOfOrigin")
    @Expose
    private String countryOfOrigin;
    @SerializedName("image")
    @Expose
    private String image;
    @SerializedName("popularity")
    @Expose
    private Integer popularity;
    @SerializedName("color")
    @Expose
    private String color;
    @SerializedName("cover")
    @Expose
    private String cover;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("releaseDate")
    @Expose
    private Integer releaseDate;
    @SerializedName("startDate")
    @Expose
    private StartDate startDate;
    @SerializedName("endDate")
    @Expose
    private EndDate endDate;
    @SerializedName("totalEpisodes")
    @Expose
    private Integer totalEpisodes;
    @SerializedName("currentEpisode")
    @Expose
    private Integer currentEpisode;
    @SerializedName("rating")
    @Expose
    private Integer rating;
    @SerializedName("duration")
    @Expose
    private Integer duration;
    @SerializedName("genres")
    @Expose
    private List<String> genres;
    @SerializedName("season")
    @Expose
    private String season;
    @SerializedName("studios")
    @Expose
    private List<String> studios;
    @SerializedName("subOrDub")
    @Expose
    private String subOrDub;
    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("recommendations")
    @Expose
    private List<Recommendation> recommendations;
    @SerializedName("characters")
    @Expose
    private List<Character> characters;
    @SerializedName("relations")
    @Expose
    private List<Relation> relations;

    @SerializedName("episodes")
    @Expose
    private List<Episode> episodes;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Title getTitle() {
        return title;
    }

    public void setTitle(Title title) {
        this.title = title;
    }

    public Integer getMalId() {
        return malId;
    }

    public void setMalId(Integer malId) {
        this.malId = malId;
    }

    public List<String> getSynonyms() {
        return synonyms;
    }

    public void setSynonyms(List<String> synonyms) {
        this.synonyms = synonyms;
    }

    public Boolean getIsLicensed() {
        return isLicensed;
    }

    public void setIsLicensed(Boolean isLicensed) {
        this.isLicensed = isLicensed;
    }

    public Boolean getIsAdult() {
        return isAdult;
    }

    public void setIsAdult(Boolean isAdult) {
        this.isAdult = isAdult;
    }

    public String getCountryOfOrigin() {
        return countryOfOrigin;
    }

    public void setCountryOfOrigin(String countryOfOrigin) {
        this.countryOfOrigin = countryOfOrigin;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Integer getPopularity() {
        return popularity;
    }

    public void setPopularity(Integer popularity) {
        this.popularity = popularity;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Integer releaseDate) {
        this.releaseDate = releaseDate;
    }

    public StartDate getStartDate() {
        return startDate;
    }

    public void setStartDate(StartDate startDate) {
        this.startDate = startDate;
    }

    public EndDate getEndDate() {
        return endDate;
    }

    public void setEndDate(EndDate endDate) {
        this.endDate = endDate;
    }

    public Integer getTotalEpisodes() {
        return totalEpisodes;
    }

    public void setTotalEpisodes(Integer totalEpisodes) {
        this.totalEpisodes = totalEpisodes;
    }

    public Integer getCurrentEpisode() {
        return currentEpisode;
    }

    public void setCurrentEpisode(Integer currentEpisode) {
        this.currentEpisode = currentEpisode;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public List<String> getGenres() {
        return genres;
    }

    public void setGenres(List<String> genres) {
        this.genres = genres;
    }

    public String getSeason() {
        return season;
    }

    public void setSeason(String season) {
        this.season = season;
    }

    public List<String> getStudios() {
        return studios;
    }

    public void setStudios(List<String> studios) {
        this.studios = studios;
    }

    public String getSubOrDub() {
        return subOrDub;
    }

    public void setSubOrDub(String subOrDub) {
        this.subOrDub = subOrDub;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<Recommendation> getRecommendations() {
        return recommendations;
    }

    public void setRecommendations(List<Recommendation> recommendations) {
        this.recommendations = recommendations;
    }

    public List<Character> getCharacters() {
        return characters;
    }

    public void setCharacters(List<Character> characters) {
        this.characters = characters;
    }

    public List<Relation> getRelations() {
        return relations;
    }

    public void setRelations(List<Relation> relations) {
        this.relations = relations;
    }

    public List<Episode> getEpisodes() {
        return episodes;
    }

    public void setEpisodes(List<Episode> episodes) {
        this.episodes = episodes;
    }
}