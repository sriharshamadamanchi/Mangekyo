package com.anime.mangekyo.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import com.anime.mangekyo.R;
import com.anime.mangekyo.activity.Search;
import com.anime.mangekyo.adapter.list.AnimeMovieAdapter;
import com.anime.mangekyo.adapter.list.PopularAnimeAdapter;
import com.anime.mangekyo.model.list.AnimeModel;
import com.anime.mangekyo.model.list.Result;
import com.anime.mangekyo.adapter.list.RecentAnimeAdapter;
import com.anime.mangekyo.service.ApiInterface;
import com.anime.mangekyo.service.RetrofitInstance;
import com.anime.mangekyo.adapter.list.TopAiringAnimeAdapter;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    RecyclerView popularAnimeRecyclerView;
    PopularAnimeAdapter popularAnimeAdapter;
    RecyclerView topAiringAnimeRecyclerView;
    TopAiringAnimeAdapter topAiringAnimeAdapter;
    RecyclerView recentAnimeRecyclerView;
    RecentAnimeAdapter recentAnimeAdapter;
    RecyclerView animeMovieRecyclerView;
    AnimeMovieAdapter animeMovieAdapter;
    ProgressBar loader;
    LinearLayout topAiringAnimeLayout;
    LinearLayout popularAnimeLayout;
    LinearLayout recentAnimeLayout;
    LinearLayout animeMovieLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        topAiringAnimeLayout = findViewById(R.id.topAiringAnimeLayout);
        popularAnimeLayout = findViewById(R.id.popularAnimeLayout);
        recentAnimeLayout = findViewById(R.id.recentAnimeLayout);
        animeMovieLayout = findViewById(R.id.animeMovieLayout);

        loader = findViewById(R.id.loader);
        loader.setIndeterminate(true);
        loader.setVisibility(View.VISIBLE);

        topAiringAnimeLayout.setVisibility(View.GONE);
        popularAnimeLayout.setVisibility(View.GONE);
        recentAnimeLayout.setVisibility(View.GONE);
        animeMovieLayout.setVisibility(View.GONE);

        ApiInterface retrofitInstance = RetrofitInstance.getRetrofitInstance();

        getPopularAnime(retrofitInstance);
        getTopAiringAnime(retrofitInstance);
        getRecentAnime(retrofitInstance);
        getAnimeMovie(retrofitInstance);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.search_bar_main) {
            Intent intent = new Intent(this, Search.class);
            startActivity(intent);
            return true;
        }
        return(super.onOptionsItemSelected(item));
    }

    private void getPopularAnime(ApiInterface retrofitInstance) {
        popularAnimeRecyclerView = findViewById(R.id.popularAnimeRecyclerView);

        Call<AnimeModel> call = retrofitInstance.getPopularAnime();
        call.enqueue(new Callback<AnimeModel>() {
            @Override
            public void onResponse(@NonNull Call<AnimeModel> call, @NonNull Response<AnimeModel> response) {
                AnimeModel animeModel = response.body();
                if(animeModel != null && animeModel.getResults() != null){
                    List<Result> results = animeModel.getResults();
                    popularAnimeAdapter = new PopularAnimeAdapter(results);
                    popularAnimeRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false));
                    popularAnimeRecyclerView.setAdapter(popularAnimeAdapter);
                    loader.setVisibility(View.GONE);
                    popularAnimeLayout.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onFailure(@NonNull Call<AnimeModel> call, @NonNull Throwable t) {
                loader.setVisibility(View.GONE);
            }
        });
    }

    private void getTopAiringAnime(ApiInterface retrofitInstance) {
        topAiringAnimeRecyclerView = findViewById(R.id.topAiringAnimeRecyclerView);

        Call<AnimeModel> call = retrofitInstance.getTopAiringAnime();
        call.enqueue(new Callback<AnimeModel>() {
            @Override
            public void onResponse(@NonNull Call<AnimeModel> call, @NonNull Response<AnimeModel> response) {
                AnimeModel animeModel = response.body();
                if(animeModel != null && animeModel.getResults() != null){
                    List<Result> results = animeModel.getResults();
                    topAiringAnimeAdapter = new TopAiringAnimeAdapter(results);
                    topAiringAnimeRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false));
                    topAiringAnimeRecyclerView.setAdapter(topAiringAnimeAdapter);
                    loader.setVisibility(View.GONE);
                    topAiringAnimeLayout.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onFailure(@NonNull Call<AnimeModel> call, @NonNull Throwable t) {
                loader.setVisibility(View.GONE);
            }
        });
    }

    private void getRecentAnime(ApiInterface retrofitInstance) {
        recentAnimeRecyclerView = findViewById(R.id.recentAnimeRecyclerView);

        Call<AnimeModel> call = retrofitInstance.getRecentAnime();
        call.enqueue(new Callback<AnimeModel>() {
            @Override
            public void onResponse(@NonNull Call<AnimeModel> call, @NonNull Response<AnimeModel> response) {
                AnimeModel animeModel = response.body();
                if(animeModel != null && animeModel.getResults() != null){
                    List<Result> results = animeModel.getResults();
                    recentAnimeAdapter = new RecentAnimeAdapter(results);
                    recentAnimeRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false));
                    recentAnimeRecyclerView.setAdapter(recentAnimeAdapter);
                    loader.setVisibility(View.GONE);
                    recentAnimeLayout.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onFailure(@NonNull Call<AnimeModel> call, @NonNull Throwable t) {
                loader.setVisibility(View.GONE);
            }
        });
    }

    private void getAnimeMovie(ApiInterface retrofitInstance) {
        animeMovieRecyclerView = findViewById(R.id.animeMovieRecyclerView);

        Call<AnimeModel> call = retrofitInstance.getAnimeMovies();
        call.enqueue(new Callback<AnimeModel>() {
            @Override
            public void onResponse(@NonNull Call<AnimeModel> call, @NonNull Response<AnimeModel> response) {
                AnimeModel animeModel = response.body();
                if(animeModel != null && animeModel.getResults() != null){
                    List<Result> results = animeModel.getResults();
                    animeMovieAdapter = new AnimeMovieAdapter(results);
                    animeMovieRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false));
                    animeMovieRecyclerView.setAdapter(animeMovieAdapter);
                    animeMovieLayout.setVisibility(View.VISIBLE);
                }
                loader.setVisibility(View.GONE);
            }

            @Override
            public void onFailure(@NonNull Call<AnimeModel> call, @NonNull Throwable t) {
                loader.setVisibility(View.GONE);
            }
        });
    }
}