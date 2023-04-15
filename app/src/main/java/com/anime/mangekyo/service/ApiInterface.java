package com.anime.mangekyo.service;

import com.anime.mangekyo.model.details.AnimeDetailsModel;
import com.anime.mangekyo.model.list.AnimeModel;
import com.anime.mangekyo.model.stream.EpisodeLinksModel;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ApiInterface {

    @GET("anilist/popular")
    Call<AnimeModel> getPopularAnime();

    @GET("anilist/trending")
    Call<AnimeModel> getTopAiringAnime();

    @GET("anilist/recent-episodes")
    Call<AnimeModel> getRecentAnime();

    @GET("anilist/advanced-search?format=MOVIE")
    Call<AnimeModel> getAnimeMovies();

    @GET("anilist/info/{id}")
    Call<AnimeDetailsModel> getAnimeDetails(@Path("id") String id);

    @GET("anilist/watch/{id}")
    Call<EpisodeLinksModel> getEpisodeLinks(@Path("id") String id);

    @GET("anilist/{query}")
    Call<AnimeModel> searchAnime(@Path("query") String query);
}
