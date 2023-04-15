package com.anime.mangekyo.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.anime.mangekyo.R;
import com.anime.mangekyo.adapter.details.AnimeDetailsAdapter;
import com.anime.mangekyo.model.details.AnimeDetailsModel;
import com.anime.mangekyo.model.stream.EpisodeLinksModel;
import com.anime.mangekyo.model.stream.Source;
import com.anime.mangekyo.service.ApiInterface;
import com.anime.mangekyo.service.RetrofitInstance;
import com.bumptech.glide.Glide;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AnimeDetails extends AppCompatActivity {

    RecyclerView episodeListRecyclerView;
    AnimeDetailsAdapter animeDetailsAdapter;
    LinearLayout animeDetailsLayout;
    ImageView animeImage;
    TextView animeName;
    TextView animeGenre;
    TextView totalEpisodes;
    ProgressBar detailsLoader;
    ActionBar actionBar;
    private boolean loading = false;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.anime_details_layout);
        actionBar = getSupportActionBar();

        detailsLoader = findViewById(R.id.detailsLoader);
        detailsLoader.setIndeterminate(true);
        detailsLoader.setVisibility(View.VISIBLE);
        animeImage = findViewById(R.id.animeImage);
        animeName = findViewById(R.id.animeName);
        animeGenre =findViewById(R.id.animeGenre);
        totalEpisodes = findViewById(R.id.totalEpisodes);
        animeDetailsLayout = findViewById(R.id.animeDetailsLayout);
        animeDetailsLayout.setVisibility(View.GONE);

        Intent intent = getIntent();
        String id = intent.getStringExtra("id");
        String title = intent.getStringExtra("title");
        if (title != null) {
            actionBar.setTitle(title);
        }

        ApiInterface retrofitInstance = RetrofitInstance.getRetrofitInstance();

        getAnimeDetails(retrofitInstance, id);
    }

    private void getAnimeDetails(ApiInterface retrofitInstance, String id) {
        episodeListRecyclerView = findViewById(R.id.episodeListRecyclerView);

        Call<AnimeDetailsModel> call = retrofitInstance.getAnimeDetails(id);
        call.enqueue(new Callback<AnimeDetailsModel>() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onResponse(@NonNull Call<AnimeDetailsModel> call, @NonNull Response<AnimeDetailsModel> response) {
                AnimeDetailsModel result = response.body();
                if(result != null){
                    Glide.with(getApplicationContext()).load(result.getImage()).into(animeImage);
                    String englishTitle = result.getTitle().getEnglish();
                    String romajiTitle = result.getTitle().getRomaji();
                    String nativeTitle = result.getTitle().getNative();

                    String title = englishTitle == null ? romajiTitle == null ? nativeTitle == null ? "-" : nativeTitle : romajiTitle : englishTitle;

                    if(title.length() > 25){
                        title = title.substring(0,26) + "...";
                    }
                    animeName.setText(title);
                    totalEpisodes.setText("Total Episodes: " + result.getTotalEpisodes().toString());
                    StringBuilder genre = new StringBuilder();
                    for(String s: result.getGenres()){
                        if(genre.toString().equals("")){
                            genre.append(s);
                        }else {
                            genre.append(" . ").append(s);
                        }
                    }
                    animeGenre.setText(genre.toString());
                    animeDetailsAdapter = new AnimeDetailsAdapter(result.getEpisodes(), episode -> {
                        if(loading){
                            return;
                        }
                        loading = true;
                        detailsLoader.setVisibility(View.VISIBLE);

                        Call<EpisodeLinksModel> linksModelCall = retrofitInstance.getEpisodeLinks(episode.getId());
                        linksModelCall.enqueue(new Callback<EpisodeLinksModel>() {
                            @Override
                            public void onResponse(@NonNull Call<EpisodeLinksModel> call1, @NonNull Response<EpisodeLinksModel> response1) {
                                EpisodeLinksModel episodeLinksModel = response1.body();
                                if(episodeLinksModel != null){
                                    Intent intent = new Intent(getApplicationContext(), VideoPlayer.class);
                                    for(Source source: episodeLinksModel.getSources()){
                                        intent.putExtra(source.getQuality(), source.getUrl());
                                    }
                                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                    getApplicationContext().startActivity(intent);
                                }
                                detailsLoader.setVisibility(View.GONE);
                                loading = false;
                            }

                            @Override
                            public void onFailure(@NonNull Call<EpisodeLinksModel> call1, @NonNull Throwable t) {
                                detailsLoader.setVisibility(View.GONE);
                                loading = false;
                            }
                        });
                    });
                    episodeListRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                    episodeListRecyclerView.setAdapter(animeDetailsAdapter);
                }
                detailsLoader.setVisibility(View.GONE);
                animeDetailsLayout.setVisibility(View.VISIBLE);
            }

            @Override
            public void onFailure(@NonNull Call<AnimeDetailsModel> call, @NonNull Throwable t) {
                detailsLoader.setVisibility(View.GONE);
            }
        });
    }
}
