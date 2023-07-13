package com.anime.mangekyo.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.anime.mangekyo.model.details.AnimeDetailsModel;
import com.anime.mangekyo.model.list.AnimeModel;

@Database(entities = {AnimeModel.class, AnimeDetailsModel.class}, version = 1)
public abstract class AnimeDatabase extends RoomDatabase {

    public static AnimeDatabase instance;

    public static synchronized AnimeDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(
                            context.getApplicationContext(),
                            AnimeDatabase.class, "animeDatabase").
                    fallbackToDestructiveMigration()
                    .build();
        }
        return instance;
    }

    public abstract AnimeDAO animeDAO();
}
