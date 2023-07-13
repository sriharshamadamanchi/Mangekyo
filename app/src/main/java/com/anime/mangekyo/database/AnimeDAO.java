package com.anime.mangekyo.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.anime.mangekyo.model.details.AnimeDetailsModel;
import com.anime.mangekyo.model.list.AnimeModel;

import java.util.List;

@Dao
public interface AnimeDAO {

    @Insert
    void insertAnimeList(AnimeModel animes);

    @Update
    void updateAnimeList(AnimeModel animes);

    @Insert
    void insertAnimeDetails(AnimeDetailsModel animeDetails);

    @Update
    void updateAnimeDetails(AnimeDetailsModel animeDetails);

    @Query("SELECT * FROM animeDetails WHERE id == :id")
    LiveData<List<AnimeDetailsModel>> getAnimeDetails(String id);

    @Query("SELECT * FROM animeList WHERE type = 'POPULAR'")
    LiveData<List<AnimeModel>> getPopularAnime();

    @Query("SELECT * FROM animeList WHERE type = 'RECENT'")
    LiveData<List<AnimeModel>> getRecentAnime();

    @Query("SELECT * FROM animeList WHERE type = 'TOPAIRING'")
    LiveData<List<AnimeModel>> getTopAiringAnime();

    @Query("SELECT * FROM animeList WHERE type = 'MOVIE'")
    LiveData<List<AnimeModel>> getAnimeMovies();
}
