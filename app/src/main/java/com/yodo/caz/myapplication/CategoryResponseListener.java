package com.yodo.caz.myapplication;

import java.util.List;

public interface CategoryResponseListener {
    public void onCategoriesLoaded(List<Category> categories);
}
