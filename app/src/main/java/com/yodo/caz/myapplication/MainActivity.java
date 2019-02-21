package com.yodo.caz.myapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;


import java.util.List;

public class MainActivity extends AppCompatActivity implements CategoryResponseListener {

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
}
