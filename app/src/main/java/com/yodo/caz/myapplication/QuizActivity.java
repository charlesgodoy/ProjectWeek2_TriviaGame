package com.yodo.caz.myapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;
import java.util.Random;

public class QuizActivity extends AppCompatActivity implements QuestionResponseListener {

    private Category selectedCategory;
    private String userName;
    private String AMOUNT = "amount=10";
    private String CATEGORY_HOLDER = "category=";
    private String REQUEST_CALL = Constants.BASE_QUESTION_URL;
    private String MULTIPLE_TYPE = "&type=multiple";
    private List<Question> loadedQuestions;
    private int current_question = 0;
    private TextView tvQuestion;


    private CardView cvOne;
    private CardView cvTwo;
    private CardView cvThree;
    private CardView cvFour;


    private TextView tvOptionOne;
    private TextView tvOptionTwo;
    private TextView tvOptionThree;
    private TextView tvOptionFour;


    private ImageView ivNext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);


        this.selectedCategory = (Category) getIntent().getExtras().getSerializable("selectedCategory");
        this.userName = getIntent().getExtras().getString("userName");


        REQUEST_CALL = Constants.BASE_QUESTION_URL + AMOUNT + "&" + CATEGORY_HOLDER + this.selectedCategory.getId() + MULTIPLE_TYPE;
        setupReferences();
        loadQuestions();
    }
    private void setupReferences(){

        tvQuestion = findViewById(R.id.tvQuestion);

        tvOptionOne = findViewById(R.id.tvOptionOne);
        tvOptionTwo = findViewById(R.id.tvOptionTwo);
        tvOptionThree = findViewById(R.id.tvOptionThree);
        tvOptionFour = findViewById(R.id.tvOptionFour);


        cvOne = findViewById(R.id.cvOne);
        cvTwo = findViewById(R.id.cvTwo);
        cvThree = findViewById(R.id.cvThree);
        cvFour = findViewById(R.id.cvFour);

    }
    private void loadQuestions(){

        GetQuestionsTask task = new GetQuestionsTask(this);
        task.execute(REQUEST_CALL);
    }

    @Override
    public void onQuestionsLoaded(List<Question> questions) {
        this.loadedQuestions = questions;
        Log.d("Quiz Questions", this.loadedQuestions.toString());
        startGame();
    }
    private void startGame(){
        Question toDisplay = this.loadedQuestions.get(0);
        tvQuestion.setText(this.loadedQuestions.get(0).getQuestionText());
        Random random = new Random();
        int correct_answer_position = random.nextInt(5);

        tvOptionOne.setText(toDisplay.getCorrectAnswer());
        tvOptionTwo.setText(toDisplay.getIncorrectAnswers().get(0));
        tvOptionThree.setText(toDisplay.getIncorrectAnswers().get(1));
        tvOptionFour.setText(toDisplay.getIncorrectAnswers().get(2));
    }
}
