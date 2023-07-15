package com.anime.mangekyo.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.anime.mangekyo.R;
import com.anime.mangekyo.adapter.AnimeListAdapter;
import com.anime.mangekyo.databinding.FragmentMainBinding;
import com.anime.mangekyo.model.list.AnimeModel;
import com.anime.mangekyo.model.list.Result;
import com.anime.mangekyo.service.ApiInterface;
import com.anime.mangekyo.service.RetrofitInstance;
import com.anime.mangekyo.viewholder.MainFragmentViewHolder;
import com.facebook.shimmer.ShimmerFrameLayout;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainFragment extends Fragment {
    MainFragmentViewHolder mainFragmentViewHolder;
    FragmentMainBinding fragmentMainBinding;
    ShimmerFrameLayout popularAnimeLoading;
    ShimmerFrameLayout topAiringAnimeLoading;
    ShimmerFrameLayout recentAnimeLoading;
    ShimmerFrameLayout animeMoviesLoading;
    List<Result> popularAnime;
    List<Result> topAiringAnime;
    List<Result> recentAnime;
    List<Result> animeMovies;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        fragmentMainBinding = FragmentMainBinding.inflate(inflater, container, false);
        return fragmentMainBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Toolbar toolbar = view.findViewById(R.id.toolBarLayout);
        toolbar.inflateMenu(R.menu.activity_main);
        toolbar.findViewById(R.id.search_bar_main).setOnClickListener(this::onSearch);

        popularAnimeLoading = fragmentMainBinding.popularAnimeLoader.getRoot();
        topAiringAnimeLoading = fragmentMainBinding.topAiringAnimeLoader.getRoot();
        recentAnimeLoading = fragmentMainBinding.recentAnimeLoader.getRoot();
        animeMoviesLoading = fragmentMainBinding.animeMovieLoader.getRoot();

        ApiInterface retrofitInstance = RetrofitInstance.getRetrofitInstance();
        mainFragmentViewHolder = new ViewModelProvider(this).get(MainFragmentViewHolder.class);

        getPopularAnime(retrofitInstance);
        getTopAiringAnime(retrofitInstance);
        getRecentAnime(retrofitInstance);
        getAnimeMovie(retrofitInstance);

        mainFragmentViewHolder.getPopularAnime().observe(requireActivity(), animeModels -> {
            if (animeModels.size() > 0) {
                popularAnime = animeModels.get(0).getResults();
                AnimeListAdapter animeListAdapter = new AnimeListAdapter(popularAnime);
                fragmentMainBinding.popularAnimeRecyclerView.setLayoutManager(new LinearLayoutManager(requireContext().getApplicationContext(), LinearLayoutManager.HORIZONTAL, false));
                fragmentMainBinding.popularAnimeRecyclerView.setAdapter(animeListAdapter);

                stopPopularAnimeLoader();
            }
        });

        mainFragmentViewHolder.getRecentAnime().observe(requireActivity(), animeModels -> {
            if (animeModels.size() > 0) {
                recentAnime = animeModels.get(0).getResults();
                AnimeListAdapter animeListAdapter = new AnimeListAdapter(recentAnime);
                fragmentMainBinding.recentAnimeRecyclerView.setLayoutManager(new LinearLayoutManager(requireContext().getApplicationContext(), LinearLayoutManager.HORIZONTAL, false));
                fragmentMainBinding.recentAnimeRecyclerView.setAdapter(animeListAdapter);

                stopRecentAnimeLoader();
            }
        });

        mainFragmentViewHolder.getTopAiringAnime().observe(requireActivity(), animeModels -> {
            if (animeModels.size() > 0) {
                topAiringAnime = animeModels.get(0).getResults();
                AnimeListAdapter animeListAdapter = new AnimeListAdapter(topAiringAnime);
                fragmentMainBinding.topAiringAnimeRecyclerView.setLayoutManager(new LinearLayoutManager(requireContext().getApplicationContext(), LinearLayoutManager.HORIZONTAL, false));
                fragmentMainBinding.topAiringAnimeRecyclerView.setAdapter(animeListAdapter);

                stopTopAiringAnimeLoader();
            }
        });

        mainFragmentViewHolder.getAnimeMovies().observe(requireActivity(), animeModels -> {
            if (animeModels.size() > 0) {
                animeMovies = animeModels.get(0).getResults();
                AnimeListAdapter animeListAdapter = new AnimeListAdapter(animeMovies);
                fragmentMainBinding.animeMovieRecyclerView.setLayoutManager(new LinearLayoutManager(requireContext().getApplicationContext(), LinearLayoutManager.HORIZONTAL, false));
                fragmentMainBinding.animeMovieRecyclerView.setAdapter(animeListAdapter);

                stopAnimeMoviesLoader();
            }
        });
    }

    public void onSearch(View view) {
        Navigation.findNavController(view).navigate(R.id.searchFragment);
    }

    private void getPopularAnime(ApiInterface retrofitInstance) {
        startPopularAnimeLoader();
        Call<AnimeModel> call = retrofitInstance.getPopularAnime();
        call.enqueue(new Callback<AnimeModel>() {
            @Override
            public void onResponse(@NonNull Call<AnimeModel> call, @NonNull Response<AnimeModel> response) {
                AnimeModel animeModel = response.body();
                if (animeModel != null && animeModel.getResults() != null) {
                    List<Result> results = animeModel.getResults();

                    AnimeModel roomAnimeModel = new AnimeModel();
                    roomAnimeModel.setType("POPULAR");
                    roomAnimeModel.setCurrentPage(1);
                    roomAnimeModel.setResults(results);
                    try {
                        mainFragmentViewHolder.insertAnimeList(roomAnimeModel);
                    } catch (Exception e) {
                        mainFragmentViewHolder.updateAnimeList(roomAnimeModel);
                    }
                }
                stopPopularAnimeLoader();
            }

            @Override
            public void onFailure(@NonNull Call<AnimeModel> call, @NonNull Throwable t) {
                stopPopularAnimeLoader();
            }
        });
    }

    private void getTopAiringAnime(ApiInterface retrofitInstance) {
        startTopAiringAnimeLoader();
        Call<AnimeModel> call = retrofitInstance.getTopAiringAnime();
        call.enqueue(new Callback<AnimeModel>() {
            @Override
            public void onResponse(@NonNull Call<AnimeModel> call, @NonNull Response<AnimeModel> response) {
                AnimeModel animeModel = response.body();
                if (animeModel != null && animeModel.getResults() != null) {
                    List<Result> results = animeModel.getResults();

                    AnimeModel roomAnimeModel = new AnimeModel();
                    roomAnimeModel.setType("TOPAIRING");
                    roomAnimeModel.setCurrentPage(1);
                    roomAnimeModel.setResults(results);
                    try {
                        mainFragmentViewHolder.insertAnimeList(roomAnimeModel);
                    } catch (Exception e) {
                        mainFragmentViewHolder.updateAnimeList(roomAnimeModel);
                    }
                }
                stopTopAiringAnimeLoader();
            }

            @Override
            public void onFailure(@NonNull Call<AnimeModel> call, @NonNull Throwable t) {
                stopTopAiringAnimeLoader();
            }
        });
    }

    private void getRecentAnime(ApiInterface retrofitInstance) {
        startRecentAnimeLoader();
        Call<AnimeModel> call = retrofitInstance.getRecentAnime();
        call.enqueue(new Callback<AnimeModel>() {
            @Override
            public void onResponse(@NonNull Call<AnimeModel> call, @NonNull Response<AnimeModel> response) {
                AnimeModel animeModel = response.body();
                if (animeModel != null && animeModel.getResults() != null) {
                    List<Result> results = animeModel.getResults();

                    AnimeModel roomAnimeModel = new AnimeModel();
                    roomAnimeModel.setType("RECENT");
                    roomAnimeModel.setCurrentPage(1);
                    roomAnimeModel.setResults(results);
                    try {
                        mainFragmentViewHolder.insertAnimeList(roomAnimeModel);
                    } catch (Exception e) {
                        mainFragmentViewHolder.updateAnimeList(roomAnimeModel);
                    }
                }
                stopRecentAnimeLoader();
            }

            @Override
            public void onFailure(@NonNull Call<AnimeModel> call, @NonNull Throwable t) {
                stopRecentAnimeLoader();
            }
        });
    }

    private void getAnimeMovie(ApiInterface retrofitInstance) {
        startAnimeMoviesLoader();
        Call<AnimeModel> call = retrofitInstance.getAnimeMovies();
        call.enqueue(new Callback<AnimeModel>() {
            @Override
            public void onResponse(@NonNull Call<AnimeModel> call, @NonNull Response<AnimeModel> response) {
                AnimeModel animeModel = response.body();
                if (animeModel != null && animeModel.getResults() != null) {
                    List<Result> results = animeModel.getResults();

                    AnimeModel roomAnimeModel = new AnimeModel();
                    roomAnimeModel.setType("MOVIE");
                    roomAnimeModel.setCurrentPage(1);
                    roomAnimeModel.setResults(results);
                    try {
                        mainFragmentViewHolder.insertAnimeList(roomAnimeModel);
                    } catch (Exception e) {
                        mainFragmentViewHolder.updateAnimeList(roomAnimeModel);
                    }
                }
                stopAnimeMoviesLoader();
            }

            @Override
            public void onFailure(@NonNull Call<AnimeModel> call, @NonNull Throwable t) {
                stopAnimeMoviesLoader();
            }
        });
    }

    public void startPopularAnimeLoader() {
        fragmentMainBinding.popularAnimeRecyclerView.setVisibility(View.GONE);
        popularAnimeLoading.setVisibility(View.VISIBLE);
        popularAnimeLoading.startShimmerAnimation();
    }

    public void stopPopularAnimeLoader() {
        if (popularAnime == null) {
            return;
        }
        popularAnimeLoading.stopShimmerAnimation();
        popularAnimeLoading.setVisibility(View.GONE);
        fragmentMainBinding.popularAnimeRecyclerView.setVisibility(View.VISIBLE);
    }

    public void startTopAiringAnimeLoader() {
        fragmentMainBinding.topAiringAnimeRecyclerView.setVisibility(View.GONE);
        topAiringAnimeLoading.setVisibility(View.VISIBLE);
        topAiringAnimeLoading.startShimmerAnimation();
    }

    public void stopTopAiringAnimeLoader() {
        if (topAiringAnime == null) {
            return;
        }
        topAiringAnimeLoading.stopShimmerAnimation();
        topAiringAnimeLoading.setVisibility(View.GONE);
        fragmentMainBinding.topAiringAnimeRecyclerView.setVisibility(View.VISIBLE);
    }

    public void startRecentAnimeLoader() {
        fragmentMainBinding.recentAnimeRecyclerView.setVisibility(View.GONE);
        recentAnimeLoading.setVisibility(View.VISIBLE);
        recentAnimeLoading.startShimmerAnimation();
    }

    public void stopRecentAnimeLoader() {
        if (recentAnime == null) {
            return;
        }
        recentAnimeLoading.stopShimmerAnimation();
        recentAnimeLoading.setVisibility(View.GONE);
        fragmentMainBinding.recentAnimeRecyclerView.setVisibility(View.VISIBLE);
    }

    public void startAnimeMoviesLoader() {
        fragmentMainBinding.animeMovieRecyclerView.setVisibility(View.GONE);
        animeMoviesLoading.setVisibility(View.VISIBLE);
        animeMoviesLoading.startShimmerAnimation();
    }

    public void stopAnimeMoviesLoader() {
        if (animeMovies == null) {
            return;
        }
        animeMoviesLoading.stopShimmerAnimation();
        animeMoviesLoading.setVisibility(View.GONE);
        fragmentMainBinding.animeMovieRecyclerView.setVisibility(View.VISIBLE);
    }
}