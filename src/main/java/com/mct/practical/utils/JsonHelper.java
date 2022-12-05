package com.mct.practical.utils;

import com.google.gson.Gson;
import org.json.JSONArray;
import org.json.JSONException;
import org.springframework.lang.NonNull;

import java.util.ArrayList;
import java.util.List;

public class JsonHelper {

    public static String objToJson(Object o) {
        return new Gson().toJson(o);
    }

    public static <T> T jsonToObj(String strJson, Class<T> clazz) {
        Gson gson = new Gson();
        return gson.fromJson(strJson, clazz);
    }

    @NonNull
    public static <T> List<T> jsonToList(String strJson, Class<T> clazz) {
        List<T> list = new ArrayList<>();
        try {
            JSONArray jsonArray = new JSONArray(strJson);
            Gson gson = new Gson();
            for (int i = 0; i < jsonArray.length(); i++) {
                list.add(gson.fromJson(jsonArray.getJSONObject(i).toString(), clazz));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return list;
    }

}
