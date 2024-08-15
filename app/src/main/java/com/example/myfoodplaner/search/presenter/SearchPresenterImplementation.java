package com.example.myfoodplaner.search.presenter;


import com.example.myfoodplaner.home.presenter.CategoryMealPresenterView;
import com.example.myfoodplaner.home.view.CategoryMealView;
import com.example.myfoodplaner.model.Dtopresenter.CategoriesItem;
import com.example.myfoodplaner.model.Dtopresenter.ListsDetailsResponse;
import com.example.myfoodplaner.model.Dtopresenter.MealsDetailResponse;
import com.example.myfoodplaner.model.Dtopresenter.MealsItemResponse;
import com.example.myfoodplaner.model.MealRepositoryView;
import com.example.myfoodplaner.model.netowark.CategoryItemCallBack;

import java.util.List;

import io.reactivex.rxjava3.core.Single;

public class SearchPresenterImplementation implements CategoryMealPresenterView, CategoryItemCallBack{

    private static SearchPresenterImplementation searchPresenter;
    private MealRepositoryView mealsRepo;
    CategoryMealView categoryMealView;

    public SearchPresenterImplementation(MealRepositoryView mealsRepo, CategoryMealView categoryMealView) {
        this.mealsRepo = mealsRepo;
        this.categoryMealView = categoryMealView;
    }

    public static synchronized SearchPresenterImplementation getInstance(MealRepositoryView mealsRepo){
        if(searchPresenter == null){
            searchPresenter = new SearchPresenterImplementation(mealsRepo);
        }
        return searchPresenter;
    }

    public SearchPresenterImplementation(MealRepositoryView mealsRepo){
        this.mealsRepo = mealsRepo;
    }

    public void getCategory() {
        mealsRepo.CategoryNetworkCall(this);
    }


    public void onSuccessResult(List<CategoriesItem> categoriesItemList) {
        categoryMealView.showCategoryData(categoriesItemList);
    }


    public void onFailureResult(String errorMsg) {
        categoryMealView.showErrorMsgCategory(errorMsg);
    }
}
