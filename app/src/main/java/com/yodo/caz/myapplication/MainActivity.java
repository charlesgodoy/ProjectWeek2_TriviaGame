package com.yodo.caz.myapplication;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import java.util.List;

public class MainActivity extends AppCompatActivity implements CategoryResponseListener, CategoryRecyclerViewClickListener{

    List<Category> categories;
    CategoriesAdapter categoriesAdapter;
    RecyclerView recyclerViewCategories;
    RecyclerView.LayoutManager mlayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerViewCategories = findViewById(R.id.rvCategory);
        mlayoutManager = new LinearLayoutManager(this);
        recyclerViewCategories.setLayoutManager(mlayoutManager);

        GetCategoriesTask task = new GetCategoriesTask(this);
        task.execute(Constants.CATEGORY_URL);
    }

    @Override
    public void onCategoriesLoaded(List<Category> categories) {
        this.categories = categories;
        Log.d("CategoriesInMain", this.categories.toString());

        categoriesAdapter = new CategoriesAdapter(this, categories);
        recyclerViewCategories.setLayoutManager(mlayoutManager);
        recyclerViewCategories.setAdapter(categoriesAdapter);
    }

    @Override
    public void onCategoryRecyclerViewClicked(final Category category) {


        final Dialog dialog = new Dialog(MainActivity.this);
        View view = getLayoutInflater().inflate(R.layout.user_input_dialog_box, null);
        dialog.setContentView(view);


        Button btnCancel = view.findViewById(R.id.btnDismissDialog);
        final EditText etUserName = view.findViewById(R.id.etUserName);
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.cancel();
            }
        });
        Button btnStart = view.findViewById(R.id.btnStartGame);
        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String username = etUserName.getText().toString();
                if(username == null || username.length()==0){
                    Toast.makeText(getApplicationContext(), "Please enter name", Toast.LENGTH_SHORT).show();
                    return;
                }
                Intent intent = new Intent(MainActivity.this, QuizActivity.class);
                intent.putExtra("selectedCategory", category);
                intent.putExtra("userName", username);
                startActivity(intent);
            }
        });
        dialog.create();
        dialog.show();
    }

}
