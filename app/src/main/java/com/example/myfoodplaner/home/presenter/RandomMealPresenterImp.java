package com.example.myfoodplaner.home.presenter;


import com.example.myfoodplaner.home.view.RandomMealView;
import com.example.myfoodplaner.model.Dtopresenter.MealsItem;
import com.example.myfoodplaner.model.MealRepositoryView;
import com.example.myfoodplaner.model.netowark.RandomMealItemCallback;

import java.util.List;

public class RandomMealPresenterImp implements RandomMealPresenterView, RandomMealItemCallback {

    private final RandomMealView randomMealView;
    private final MealRepositoryView mealRepositoryView;

    public RandomMealPresenterImp(RandomMealView randomMealView, MealRepositoryView mealRepositoryView){
        this.randomMealView = randomMealView;
        this.mealRepositoryView = mealRepositoryView;
    }

    @Override
    public void getMeal() {
        mealRepositoryView.RandomMealNetworkCall(this);
    }

    @Override
    public void onSuccessResult(List<MealsItem> mealsItemList) {
            randomMealView.showData(mealsItemList);
    }

    @Override
    public void onFailureResult(String errorMsg) {
        randomMealView.showErrorMsg(errorMsg);
    }
}
