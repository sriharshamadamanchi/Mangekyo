package com.anime.mangekyo.viewholder;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.anime.mangekyo.database.AnimeRepository;
import com.anime.mangekyo.databinding.FragmentMainBinding;
import com.anime.mangekyo.model.list.AnimeModel;

import java.util.List;

public class MainFragmentViewHolder extends AndroidViewModel {
    AnimeRepository animeRepository;
    FragmentMainBinding fragmentMainBinding;

    public MainFragmentViewHolder(@NonNull Application application) {
        super(application);
        animeRepository = new AnimeRepository(application);
    }

    public void insertAnimeList(AnimeModel animes) {
        animeRepository.insertAnimeList(animes);
    }

    public void updateAnimeList(AnimeModel animes) {
        animeRepository.updateAnimeList(animes);
    }

    public LiveData<List<AnimeModel>> getPopularAnime() {
        return animeRepository.getPopularAnime();
    }

    public LiveData<List<AnimeModel>> getRecentAnime() {
        return animeRepository.getRecentAnime();
    }

    public LiveData<List<AnimeModel>> getTopAiringAnime() {
        return animeRepository.getTopAiringAnime();
    }

    public LiveData<List<AnimeModel>> getAnimeMovies() {
        return animeRepository.getAnimeMovies();
    }

}
