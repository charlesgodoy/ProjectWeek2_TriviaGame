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

public class GetQuestionsTask extends AsyncTask<String, String, List<Question>> {

    QuestionResponseListener listener;
    public GetQuestionsTask(QuestionResponseListener listener){
        this.listener = listener;
    }
    @Override
    protected List<Question> doInBackground(String... strings) {
        URL url = null;
        try {
            // Convert the string to a valid URL
            url = new URL(strings[0]);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        String response=null;
        try {
            response = Utils.GetRespFromHttpUrl(url);
        } catch (IOException e) {
            e.printStackTrace();
        }
        // Getting valid response, hence parse json
        ArrayList<Question> questions = new ArrayList<>();
        JSONObject jsonResponse = null;
        try {
            jsonResponse = new JSONObject(response);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        JSONArray questionArray = null;
        try {
            questionArray = jsonResponse.getJSONArray("results");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        for(int i=0; i<questionArray.length(); ++i){
            JSONObject questionJson = null;
            try {
                 questionJson = questionArray.getJSONObject(i);
                 Question question = new Question(questionJson);
                 questions.add(question);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        Log.d("Questions: ", questions.toString());
        return questions;
    }

    @Override
    protected void onPostExecute(List<Question> questions) {
        super.onPostExecute(questions);
        listener.onQuestionsLoaded(questions);
    }
}
