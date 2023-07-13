package com.anime.mangekyo.database;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.anime.mangekyo.model.details.AnimeDetailsModel;
import com.anime.mangekyo.model.list.AnimeModel;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class AnimeRepository {

    public AnimeDAO animeDAO;

    public AnimeRepository(Application application) {
        AnimeDatabase animeDatabase = AnimeDatabase.getInstance(application);

        animeDAO = animeDatabase.animeDAO();
    }

    public void insertAnimeList(AnimeModel animes) {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        executor.execute(() -> animeDAO.insertAnimeList(animes));
    }

    public void updateAnimeList(AnimeModel animes) {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        executor.execute(() -> animeDAO.updateAnimeList(animes));
    }

    public void insertAnimeDetails(AnimeDetailsModel animeDetails) {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        executor.execute(() -> animeDAO.insertAnimeDetails(animeDetails));
    }

    public void updateAnimeDetails(AnimeDetailsModel animeDetails) {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        executor.execute(() -> animeDAO.updateAnimeDetails(animeDetails));
    }

    public LiveData<List<AnimeDetailsModel>> getAnimeDetails(String id) {
        return animeDAO.getAnimeDetails(id);
    }

    public LiveData<List<AnimeModel>> getPopularAnime() {
        return animeDAO.getPopularAnime();
    }

    public LiveData<List<AnimeModel>> getRecentAnime() {
        return animeDAO.getRecentAnime();
    }

    public LiveData<List<AnimeModel>> getTopAiringAnime() {
        return animeDAO.getTopAiringAnime();
    }

    public LiveData<List<AnimeModel>> getAnimeMovies() {
        return animeDAO.getAnimeMovies();
    }

}
