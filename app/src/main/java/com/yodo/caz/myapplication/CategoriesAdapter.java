package com.yodo.caz.myapplication;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class CategoriesAdapter extends RecyclerView.Adapter<CategoriesAdapter.CategoryViewHolder> {


    private Context context;
    private  List<Category> categories;
    public CategoriesAdapter(Context context, List<Category> categories){
        this.context = context;
        this.categories = categories;
    }
    @NonNull
    @Override
    public CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.category_item_layout, viewGroup, false);
        return new CategoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryViewHolder categoryViewHolder, int i) {
        String name = categories.get(i).getName();

        categoryViewHolder.setCategoryName(name);
    }

    @Override
    public int getItemCount() {
        return this.categories.size();
    }

    public static class CategoryViewHolder extends RecyclerView.ViewHolder{

        private TextView categoryName;
        private ImageView ivCategory;
        public CategoryViewHolder(@NonNull View itemView) {
            super(itemView);

            categoryName = itemView.findViewById(R.id.tvCategoryName);
        }
        public void setCategoryName(String name){
            this.categoryName.setText(name);
        }
    }

}
