package com.anime.mangekyo.viewholder;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.anime.mangekyo.database.AnimeRepository;
import com.anime.mangekyo.model.details.AnimeDetailsModel;
import com.anime.mangekyo.model.list.AnimeModel;

import java.util.List;

public class AnimeDetailsFragmentViewHolder extends AndroidViewModel {
    AnimeRepository animeRepository;
    public AnimeDetailsFragmentViewHolder(@NonNull Application application) {
        super(application);
        animeRepository = new AnimeRepository(application);
    }

    public void insertAnimeDetails(AnimeDetailsModel animeDetails){
        animeRepository.insertAnimeDetails(animeDetails);
    }

    public void updateAnimeDetails(AnimeDetailsModel animeDetails){
        animeRepository.updateAnimeDetails(animeDetails);
    }

    public LiveData<List<AnimeDetailsModel>> getAnimeDetails(String id){
        return animeRepository.getAnimeDetails(id);
    }
}
