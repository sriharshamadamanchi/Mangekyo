package com.anime.mangekyo.adapter;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.anime.mangekyo.R;
import com.anime.mangekyo.databinding.AnimeItemLayoutBinding;
import com.anime.mangekyo.model.list.Result;
import com.bumptech.glide.Glide;

import java.util.List;

public class AnimeListAdapter extends RecyclerView.Adapter<AnimeListAdapter.ViewHolder> {
    public List<Result> data;

    public AnimeListAdapter(List<Result> data) {
        this.data = data;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        AnimeItemLayoutBinding animeItemLayoutBinding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.getContext()),
                R.layout.anime_item_layout,
                parent,
                false
        );
        return new ViewHolder(animeItemLayoutBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.animeItemLayoutBinding.setAnime(data.get(position));
        String imageUrl = data.get(position).getImage();
        Glide.with(holder.animeItemLayoutBinding.getRoot().getContext()).load(imageUrl).into(holder.animeItemLayoutBinding.imageView);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        AnimeItemLayoutBinding animeItemLayoutBinding;

        public ViewHolder(@NonNull AnimeItemLayoutBinding animeItemLayoutBinding) {
            super(animeItemLayoutBinding.getRoot());
            this.animeItemLayoutBinding = animeItemLayoutBinding;

            animeItemLayoutBinding.imageView.setOnClickListener(view -> {
                int position = getAbsoluteAdapterPosition();
                Result anime = data.get(position);
                String id = anime.getId();
                String englishTitle = anime.getTitle().getEnglish();
                String romajiTitle = anime.getTitle().getRomaji();
                String nativeTitle = anime.getTitle().getNative();
                String title = englishTitle == null ? romajiTitle == null ? nativeTitle == null ? "-" : nativeTitle : romajiTitle : englishTitle;

                Bundle bundle = new Bundle();
                bundle.putString("id", id);
                bundle.putString("title", title);

                Navigation.findNavController(animeItemLayoutBinding.getRoot()).navigate(R.id.animeDetailsFragment, bundle);
            });
        }
    }
}
