package com.anime.mangekyo.utility;

import androidx.room.TypeConverter;

import com.anime.mangekyo.model.details.Episode;
import com.anime.mangekyo.model.details.Title;
import com.anime.mangekyo.model.list.Result;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.Collections;
import java.util.List;

public class TypeConverterUtil {

    static Gson gson = new Gson();

    @TypeConverter
    public static List<Result> stringToObjectList(String data) {
        if (data == null) {
            return Collections.emptyList();
        }

        Type listType = new TypeToken<List<Result>>() {
        }.getType();

        return gson.fromJson(data, listType);
    }

    @TypeConverter
    public static String objectListToString(List<Result> resultList) {
        return gson.toJson(resultList);
    }

    @TypeConverter
    public static List<Episode> stringToEpisodeList(String data) {
        if (data == null) {
            return Collections.emptyList();
        }

        Type listType = new TypeToken<List<Episode>>() {
        }.getType();

        return gson.fromJson(data, listType);
    }

    @TypeConverter
    public static String episodeListToString(List<Episode> episodeList) {
        return gson.toJson(episodeList);
    }

    @TypeConverter
    public static List<String> stringToStringList(String data) {
        if (data == null) {
            return Collections.emptyList();
        }

        Type listType = new TypeToken<List<String>>() {
        }.getType();

        return gson.fromJson(data, listType);
    }

    @TypeConverter
    public static String stringListToString(List<String> stringList) {
        return gson.toJson(stringList);
    }

    @TypeConverter
    public static Title stringToTitle(String value) {
        if (value == null) {
            return null;
        }
        return gson.fromJson(value, Title.class);
    }

    @TypeConverter
    public static String titleToString(Title title) {
        return gson.toJson(title);
    }
}