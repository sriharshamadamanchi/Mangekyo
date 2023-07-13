package com.anime.mangekyo.fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.anime.mangekyo.R;
import com.anime.mangekyo.adapter.AnimeDetailsAdapter;
import com.anime.mangekyo.databinding.FragmentAnimeDetailsBinding;
import com.anime.mangekyo.model.details.AnimeDetailsModel;
import com.anime.mangekyo.model.details.Episode;
import com.anime.mangekyo.model.stream.EpisodeLinksModel;
import com.anime.mangekyo.model.stream.Source;
import com.anime.mangekyo.service.ApiInterface;
import com.anime.mangekyo.service.RetrofitInstance;
import com.anime.mangekyo.viewholder.AnimeDetailsFragmentViewHolder;
import com.bumptech.glide.Glide;
import com.facebook.shimmer.ShimmerFrameLayout;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AnimeDetailsFragment extends Fragment {
    AnimeDetailsAdapter animeDetailsAdapter;
    ShimmerFrameLayout detailsLoader;
    ShimmerFrameLayout episodesLoader;
    AnimeDetailsFragmentViewHolder animeDetailsFragmentViewHolder;
    FragmentAnimeDetailsBinding fragmentAnimeDetailsBinding;
    AnimeDetailsModel animeDetails;
    private boolean loading = false;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        fragmentAnimeDetailsBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_anime_details, container, false);
        return fragmentAnimeDetailsBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Toolbar toolbar = view.findViewById(R.id.toolBarLayout);
        toolbar.inflateMenu(R.menu.empty_menu);
        toolbar.setNavigationOnClickListener(backView -> Navigation.findNavController(backView).navigateUp());
        TextView toolBarText = toolbar.findViewById(R.id.toolBarText);

        animeDetailsFragmentViewHolder = new ViewModelProvider(this).get(AnimeDetailsFragmentViewHolder.class);
        detailsLoader = fragmentAnimeDetailsBinding.detailsShimmer.getRoot();
        episodesLoader = fragmentAnimeDetailsBinding.episodesShimmer.getRoot();

        AnimeDetailsFragmentArgs animeDetailsFragmentArgs = AnimeDetailsFragmentArgs.fromBundle(requireArguments());

        String id = animeDetailsFragmentArgs.getId();
        String title = animeDetailsFragmentArgs.getTitle();
        toolBarText.setText(title);

        ApiInterface retrofitInstance = RetrofitInstance.getRetrofitInstance();

        animeDetailsFragmentViewHolder.getAnimeDetails(id).observe(requireActivity(), animeDetailsModels -> {
            if (animeDetailsModels.size() < 1) {
                return;
            }
            AnimeDetailsModel details = animeDetailsModels.get(0);
            if (details != null) {
                animeDetails = details;
                fragmentAnimeDetailsBinding.setDetails(animeDetails);
                Glide.with(requireContext().getApplicationContext()).load(animeDetails.getImage()).into(fragmentAnimeDetailsBinding.animeImage);

                StringBuilder genre = new StringBuilder();
                for (String s : animeDetails.getGenres()) {
                    if (genre.toString().equals("")) {
                        genre.append(s);
                    } else {
                        genre.append(" . ").append(s);
                    }
                }
                fragmentAnimeDetailsBinding.animeGenre.setText(genre.toString());

                animeDetailsAdapter = new AnimeDetailsAdapter(animeDetails.getEpisodes(), episode -> onClickEpisode(retrofitInstance, episode));
                fragmentAnimeDetailsBinding.episodeListRecyclerView.setLayoutManager(new LinearLayoutManager(requireContext().getApplicationContext()));
                fragmentAnimeDetailsBinding.episodeListRecyclerView.setAdapter(animeDetailsAdapter);
                stopDetailsLoader();
            }
        });

        getAnimeDetails(retrofitInstance, id);
    }

    private void getAnimeDetails(ApiInterface retrofitInstance, String id) {
        startDetailsLoader();
        Call<AnimeDetailsModel> call = retrofitInstance.getAnimeDetails(id);
        call.enqueue(new Callback<AnimeDetailsModel>() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onResponse(@NonNull Call<AnimeDetailsModel> call, @NonNull Response<AnimeDetailsModel> response) {
                AnimeDetailsModel result = response.body();
                if (result != null) {
                    try {
                        animeDetailsFragmentViewHolder.insertAnimeDetails(result);
                    } catch (Exception e) {
                        animeDetailsFragmentViewHolder.updateAnimeDetails(result);
                    }
                }
                stopDetailsLoader();
            }

            @Override
            public void onFailure(@NonNull Call<AnimeDetailsModel> call, @NonNull Throwable t) {
                stopDetailsLoader();
            }
        });
    }

    public void onClickEpisode(ApiInterface retrofitInstance, Episode episode) {
        if (loading) {
            return;
        }
        loading = true;
        startEpisodeLinkLoader();

        Call<EpisodeLinksModel> linksModelCall = retrofitInstance.getEpisodeLinks(episode.getId());
        linksModelCall.enqueue(new Callback<EpisodeLinksModel>() {
            @Override
            public void onResponse(@NonNull Call<EpisodeLinksModel> call1, @NonNull Response<EpisodeLinksModel> response1) {
                EpisodeLinksModel episodeLinksModel = response1.body();
                if (episodeLinksModel != null) {
                    Bundle bundle = new Bundle();
                    String title1 = episode.getTitle();
                    if (title1 != null) {
                        title1 = episode.getNumber() + " . " + title1;
                    } else {
                        title1 = "Episode " + episode.getNumber();
                    }
                    bundle.putString("title", title1);
                    for (Source source : episodeLinksModel.getSources()) {
                        bundle.putString("p" + source.getQuality(), source.getUrl());
                    }
                    Navigation.findNavController(requireActivity(), R.id.fragmentContainerView).navigate(R.id.videoPlayerFragment, bundle);
                }
                stopEpisodeLinkLoader();
                loading = false;
            }

            @Override
            public void onFailure(@NonNull Call<EpisodeLinksModel> call1, @NonNull Throwable t) {
                stopEpisodeLinkLoader();
                loading = false;
            }
        });
    }

    public void startDetailsLoader() {
        fragmentAnimeDetailsBinding.animeDetailsLayout.setVisibility(View.GONE);
        detailsLoader.setVisibility(View.VISIBLE);
        episodesLoader.setVisibility(View.VISIBLE);
        detailsLoader.startShimmerAnimation();
        episodesLoader.startShimmerAnimation();
    }

    public void stopDetailsLoader() {
        if (animeDetails == null) {
            return;
        }
        detailsLoader.stopShimmerAnimation();
        episodesLoader.stopShimmerAnimation();
        detailsLoader.setVisibility(View.GONE);
        episodesLoader.setVisibility(View.GONE);
        fragmentAnimeDetailsBinding.animeDetailsLayout.setVisibility(View.VISIBLE);
    }

    public void startEpisodeLinkLoader() {
        fragmentAnimeDetailsBinding.episodeLinkLoader.setIndeterminate(true);
        fragmentAnimeDetailsBinding.episodeLinkLoader.setVisibility(View.VISIBLE);
    }

    public void stopEpisodeLinkLoader() {
        fragmentAnimeDetailsBinding.episodeLinkLoader.setVisibility(View.GONE);
    }
}