package com.example.myfoodplaner.category.presenter;


import com.example.myfoodplaner.category.view.CategoryDetailsView;
import com.example.myfoodplaner.model.Dtopresenter.ListsDetails;
import com.example.myfoodplaner.model.Dtopresenter.ListsDetailsResponse;
import com.example.myfoodplaner.model.MealRepositoryView;
import com.example.myfoodplaner.model.netowark.CategoryDetailsCallback;

import java.util.List;

import io.reactivex.rxjava3.core.Single;

public class CategoryDetailsPresenterImp implements CategoryDetailsPresenterView, CategoryDetailsCallback {
    private final CategoryDetailsView categoryDetailsView;
    private final MealRepositoryView mealRepositoryView;

    public CategoryDetailsPresenterImp(CategoryDetailsView categoryDetailsView, MealRepositoryView mealRepositoryView) {
        this.categoryDetailsView = categoryDetailsView;
        this.mealRepositoryView = mealRepositoryView;
    }

    @Override
    public Single<ListsDetailsResponse> getCategoryDetail(String category) {
        return mealRepositoryView.CategoryDetailsNetworkCall(category);
    }

    @Override
    public Single<ListsDetailsResponse> getMealsByArea(String areaId) {
        return mealRepositoryView.getMealsByArea(areaId);
    }

    @Override
    public Single<ListsDetailsResponse> IngredientDetailsNetworkCall(String id) {
        return mealRepositoryView.IngredientDetailsNetworkCall(id);
    }

    @Override
    public void onSuccessResult(List<ListsDetails> categoryDetailsList) {
        categoryDetailsView.showCategoryDetailsData(categoryDetailsList);
    }

    @Override
    public void onFailureResult(String errorMsg) {
        categoryDetailsView.showCategoryDetailsErrorMsg(errorMsg);
    }
}
