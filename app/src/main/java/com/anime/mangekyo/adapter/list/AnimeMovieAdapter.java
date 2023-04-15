package com.anime.mangekyo.adapter.list;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.anime.mangekyo.R;
import com.anime.mangekyo.activity.AnimeDetails;
import com.anime.mangekyo.model.list.Result;
import com.bumptech.glide.Glide;

import java.util.List;

public class AnimeMovieAdapter extends RecyclerView.Adapter<AnimeMovieAdapter.ViewHolder> {
    public List<Result> data;

    public AnimeMovieAdapter(List<Result> data) {
        this.data = data;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater  = LayoutInflater.from(parent.getContext());
        View listItem = inflater.inflate(R.layout.popularanime_layout,parent, false);
        return new ViewHolder(listItem);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String imageUrl = data.get(position).getImage();
        Glide.with(holder.itemView.getContext()).load(imageUrl).into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageView);
            itemView.setOnClickListener(view -> {
                Intent intent = new Intent(view.getContext(), AnimeDetails.class);
                int position = getAbsoluteAdapterPosition();
                Result anime = data.get(position);
                String id = anime.getId();
                String englishTitle = anime.getTitle().getEnglish();
                String romajiTitle = anime.getTitle().getRomaji();
                String nativeTitle = anime.getTitle().getNative();
                String title = englishTitle == null ? romajiTitle == null ? nativeTitle == null ? "-" : nativeTitle : romajiTitle : englishTitle;

                intent.putExtra("id", id);
                intent.putExtra("title", title);
                view.getContext().startActivity(intent);
            });
        }
    }
}
