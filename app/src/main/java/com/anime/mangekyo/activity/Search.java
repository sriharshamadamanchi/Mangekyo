package com.anime.mangekyo.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.Menu;

import com.anime.mangekyo.R;
import com.anime.mangekyo.adapter.list.SearchAnimeAdapter;
import com.anime.mangekyo.model.list.AnimeModel;
import com.anime.mangekyo.service.ApiInterface;
import com.anime.mangekyo.service.RetrofitInstance;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Search extends AppCompatActivity {

    ApiInterface retrofitInstance;
    RecyclerView searchAnimeRecyclerView;
    SearchAnimeAdapter searchAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        retrofitInstance = RetrofitInstance.getRetrofitInstance();
        searchAnimeRecyclerView = findViewById(R.id.searchAnimeRecyclerView);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_search, menu);
        return true;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        SearchView searchView = (SearchView) menu.findItem(R.id.search_bar_search).getActionView();
        searchView.setIconifiedByDefault(true);
        searchView.setFocusable(true);
        searchView.setIconified(false);
        searchView.requestFocusFromTouch();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if(newText.trim().equals("")){
                    return false;
                }
                Call<AnimeModel> call = retrofitInstance.searchAnime(newText);
                call.enqueue(new Callback<AnimeModel>() {
                    @Override
                    public void onResponse(@NonNull Call<AnimeModel> call, @NonNull Response<AnimeModel> response) {
                        AnimeModel results = response.body();
                        if(results != null && results.getResults() != null){
                            searchAdapter = new SearchAnimeAdapter(results.getResults());
                            searchAnimeRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                            searchAnimeRecyclerView.setAdapter(searchAdapter);
                        }
                    }

                    @Override
                    public void onFailure(@NonNull Call<AnimeModel> call, @NonNull Throwable t) {

                    }
                });
                return false;
            }
        });
        return super.onPrepareOptionsMenu(menu);
    }
}