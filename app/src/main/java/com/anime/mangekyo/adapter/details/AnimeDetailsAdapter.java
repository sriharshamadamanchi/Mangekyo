package com.anime.mangekyo.adapter.details;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.anime.mangekyo.R;
import com.anime.mangekyo.model.details.Episode;
import com.bumptech.glide.Glide;

import java.util.List;

public class AnimeDetailsAdapter extends RecyclerView.Adapter<AnimeDetailsAdapter.ViewHolder> {
    private final List<Episode> data;
    private final OnClickListener onClickListener;

    public AnimeDetailsAdapter(List<Episode> data, OnClickListener onClickListener) {
        this.data = data;
        this.onClickListener = onClickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View listItem = inflater.inflate(R.layout.episodes_layout, parent, false);
        return new ViewHolder(listItem);
    }

    @SuppressLint("DefaultLocale")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Episode episode = data.get(position);
        String title = episode.getTitle();
        if(title != null){
            title = episode.getNumber() + " . " + title;
        }else{
            title = "Episode " + episode.getNumber();
        }
        holder.episodeTitle.setText(title);
        Glide.with(holder.itemView.getContext()).load(episode.getImage()).into(holder.episodeImage);

        holder.itemView.setOnClickListener(v -> onClickListener.onClick(episode));
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public interface OnClickListener {
        void onClick(Episode episode);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        ImageView episodeImage;
        TextView episodeTitle;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            episodeImage = itemView.findViewById(R.id.episodeImage);
            episodeTitle = itemView.findViewById(R.id.episodeTitle);
        }
    }
}
