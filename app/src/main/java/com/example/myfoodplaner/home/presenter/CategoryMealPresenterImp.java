package com.example.myfoodplaner.home.presenter;

import com.example.myfoodplaner.home.view.CategoryMealView;
import com.example.myfoodplaner.model.Dtopresenter.CategoriesItem;
import com.example.myfoodplaner.model.MealRepositoryView;
import com.example.myfoodplaner.model.netowark.CategoryItemCallBack;

import java.util.List;

public class CategoryMealPresenterImp implements CategoryMealPresenterView, CategoryItemCallBack {
    private final CategoryMealView categoryMealView;
    private final MealRepositoryView repositoryView;

    public CategoryMealPresenterImp(CategoryMealView categoryMealView, MealRepositoryView repositoryView) {
        this.categoryMealView = categoryMealView;
        this.repositoryView = repositoryView;
    }


    @Override
    public void getCategory() {
        repositoryView.CategoryNetworkCall(this);
    }

    @Override
    public void onSuccessResult(List<CategoriesItem> categoriesItemList) {
        categoryMealView.showCategoryData(categoriesItemList);
    }

    @Override
    public void onFailureResult(String errorMsg) {
        categoryMealView.showErrorMsgCategory(errorMsg);
    }
}
