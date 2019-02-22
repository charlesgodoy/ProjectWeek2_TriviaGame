package com.yodo.caz.myapplication;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;

public class Question implements Serializable {
    private String category;
    private String difficulty;
    private String questionText;
    private String correctAnswer;
    private ArrayList<String> incorrectAnswers;

    public Question(String category, String difficulty, String questionText, String correctAnswer, ArrayList<String> incorrectAnswers) {
        this.category = category;
        this.difficulty = difficulty;
        this.questionText = questionText;
        this.correctAnswer = correctAnswer;
        this.incorrectAnswers = incorrectAnswers;
    }

    public Question(){

    }

    public Question(JSONObject questionJson){

        try {
            this.category = questionJson.getString("category");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            this.difficulty = questionJson.getString("difficulty");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            this.questionText = questionJson.getString("question");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        JSONArray wrongAnswers = null;
        try {
            wrongAnswers = questionJson.getJSONArray("incorrect_answers");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        ArrayList<String> wrongAnswersList = new ArrayList<>();
        for(int i=0; i<wrongAnswers.length(); ++i){
            try {
                String ans = wrongAnswers.getString(i);
                wrongAnswersList.add(ans);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        this.incorrectAnswers = wrongAnswersList;
        try {
            this.correctAnswer = questionJson.getString("correct_answer");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    public String getQuestionText() {
        return questionText;
    }

    public void setQuestionText(String questionText) {
        this.questionText = questionText;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(String correctAnswer) {
        this.correctAnswer = correctAnswer;
    }

    public ArrayList<String> getIncorrectAnswers() {
        return incorrectAnswers;
    }

    public void setIncorrectAnswers(ArrayList<String> incorrectAnswers) {
        this.incorrectAnswers = incorrectAnswers;
    }

    @Override
    public String toString() {
        return "Question{" +
                "category='" + category + '\'' +
                ", difficulty='" + difficulty + '\'' +
                ", questionText='" + questionText + '\'' +
                ", correctAnswer='" + correctAnswer + '\'' +
                ", incorrectAnswers=" + incorrectAnswers +
                '}';
    }
}
