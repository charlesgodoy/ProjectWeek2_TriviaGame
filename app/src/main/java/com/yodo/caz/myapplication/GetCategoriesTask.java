package com.yodo.caz.myapplication;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class GetCategoriesTask extends AsyncTask<String, String, List<Category>> {

    @Override
    protected List<Category> doInBackground(String... strings) {
        URL url = null;
        try {
            url = new URL(strings[0]);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        String response=null;
        ArrayList<Category> categories = new ArrayList<>();
        try {
            response = Utils.GetRespFromHttpUrl(url);
            JSONObject responseJson = null;
            try {
                responseJson = new JSONObject(response);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            JSONArray jsonCategories = null;
            try {
                jsonCategories = responseJson.getJSONArray("trivia_categories");
            } catch (JSONException e) {
                e.printStackTrace();
            }
            for(int i=0; i<jsonCategories.length(); ++i){
                JSONObject eachCategoryJson = null;
                try {
                    eachCategoryJson = jsonCategories.getJSONObject(i);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                Category category = new Category(eachCategoryJson);
                categories.add(category);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        Log.d("Categories", categories.toString());
        return categories;
    }
}
