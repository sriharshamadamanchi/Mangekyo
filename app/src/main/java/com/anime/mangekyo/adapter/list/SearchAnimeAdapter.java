package com.anime.mangekyo.adapter.list;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.anime.mangekyo.R;
import com.anime.mangekyo.activity.AnimeDetails;
import com.anime.mangekyo.model.list.Result;
import com.bumptech.glide.Glide;

import java.util.List;

public class SearchAnimeAdapter extends RecyclerView.Adapter<SearchAnimeAdapter.ViewHolder> {
    public List<Result> data;

    public SearchAnimeAdapter(List<Result> data) {
        this.data = data;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater  = LayoutInflater.from(parent.getContext());
        View listItem = inflater.inflate(R.layout.activity_search_details,parent, false);
        return new ViewHolder(listItem);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Result anime = data.get(position);
        String imageUrl = anime.getImage();
        Glide.with(holder.itemView.getContext()).load(imageUrl).into(holder.animeImage);
        String englishTitle = anime.getTitle().getEnglish();
        String romajiTitle = anime.getTitle().getRomaji();
        String nativeTitle = anime.getTitle().getNative();
        String title = englishTitle == null ? romajiTitle == null ? nativeTitle == null ? "-" : nativeTitle : romajiTitle : englishTitle;
        if(title.length() > 25){
            title = title.substring(0,26) + "...";
        }
        holder.animeName.setText(title);
        StringBuilder genre = new StringBuilder();
        for(String s: anime.getGenres()){
            if(genre.toString().equals("")){
                genre.append(s);
            }else {
                genre.append(" . ").append(s);
            }
        }
        holder.animeGenre.setText(genre);
        holder.totalEpisodes.setText("Total Episodes: " + String.valueOf(anime.getTotalEpisodes()));
        holder.type.setText("Type: "+anime.getType());
        holder.status.setText("Status: "+anime.getStatus());
        holder.releaseDate.setText("Release Date: " + String.valueOf(anime.getReleaseDate()));
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView animeImage;
        TextView animeName;
        TextView animeGenre;
        TextView totalEpisodes;
        TextView type;
        TextView status;
        TextView releaseDate;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            animeImage = itemView.findViewById(R.id.animeImage);
            animeName = itemView.findViewById(R.id.animeName);
            animeGenre = itemView.findViewById(R.id.animeGenre);
            totalEpisodes = itemView.findViewById(R.id.totalEpisodes);
            type = itemView.findViewById(R.id.type);
            status = itemView.findViewById(R.id.status);
            releaseDate = itemView.findViewById(R.id.releaseDate);

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
