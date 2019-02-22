package com.yodo.caz.myapplication;

import java.util.List;

public interface QuestionResponseListener {
    public void onQuestionsLoaded(List<Question> questions);
}
