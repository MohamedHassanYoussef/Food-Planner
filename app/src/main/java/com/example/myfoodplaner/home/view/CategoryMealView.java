package com.example.myfoodplaner.home.view;


import com.example.myfoodplaner.model.Dtopresenter.CategoriesItem;

import java.util.List;

public interface CategoryMealView {
    public void showCategoryData(List<CategoriesItem> categoriesItemList);
    public void showErrorMsgCategory(String error);
}
