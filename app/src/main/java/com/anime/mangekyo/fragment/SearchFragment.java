package com.anime.mangekyo.fragment;

import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.anime.mangekyo.R;
import com.anime.mangekyo.adapter.SearchAnimeAdapter;
import com.anime.mangekyo.model.list.AnimeModel;
import com.anime.mangekyo.service.ApiInterface;
import com.anime.mangekyo.service.RetrofitInstance;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchFragment extends Fragment {
    ApiInterface retrofitInstance;
    RecyclerView searchAnimeRecyclerView;
    SearchAnimeAdapter searchAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_search, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Toolbar toolbar = view.findViewById(R.id.toolBarLayout);
        toolbar.inflateMenu(R.menu.empty_menu);
        toolbar.setNavigationOnClickListener(backView -> Navigation.findNavController(backView).navigateUp());

        EditText editText = toolbar.findViewById(R.id.searchAnime);
        InputMethodManager imm = (InputMethodManager) requireContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.showSoftInput(editText, InputMethodManager.SHOW_IMPLICIT);

        editText.requestFocus();

        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence newText, int i, int i1, int i2) {
                if (String.valueOf(newText).trim().equals("")) {
                    return;
                }
                Call<AnimeModel> call = retrofitInstance.searchAnime((String.valueOf(newText)));
                call.enqueue(new Callback<AnimeModel>() {
                    @Override
                    public void onResponse(@NonNull Call<AnimeModel> call, @NonNull Response<AnimeModel> response) {
                        AnimeModel results = response.body();
                        if (results != null && results.getResults() != null) {
                            searchAdapter = new SearchAnimeAdapter(results.getResults());
                            searchAnimeRecyclerView.setLayoutManager(new LinearLayoutManager(requireContext().getApplicationContext()));
                            searchAnimeRecyclerView.setAdapter(searchAdapter);
                        }
                    }

                    @Override
                    public void onFailure(@NonNull Call<AnimeModel> call, @NonNull Throwable t) {

                    }
                });
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        retrofitInstance = RetrofitInstance.getRetrofitInstance();
        searchAnimeRecyclerView = view.findViewById(R.id.searchAnimeRecyclerView);
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.activity_search, menu);
    }

    @Override
    public void onPrepareOptionsMenu(Menu menu) {
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
                if (newText.trim().equals("")) {
                    return false;
                }
                Call<AnimeModel> call = retrofitInstance.searchAnime(newText);
                call.enqueue(new Callback<AnimeModel>() {
                    @Override
                    public void onResponse(@NonNull Call<AnimeModel> call, @NonNull Response<AnimeModel> response) {
                        AnimeModel results = response.body();
                        if (results != null && results.getResults() != null) {
                            searchAdapter = new SearchAnimeAdapter(results.getResults());
                            searchAnimeRecyclerView.setLayoutManager(new LinearLayoutManager(requireContext().getApplicationContext()));
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
    }
}