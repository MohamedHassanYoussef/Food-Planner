package com.example.myfoodplaner.search.presenter;


import com.example.myfoodplaner.model.Dtopresenter.IngredientsItem;
import com.example.myfoodplaner.model.MealRepositoryView;
import com.example.myfoodplaner.model.netowark.IngredientsItemCallback;
import com.example.myfoodplaner.search.view.ingredientsearch.IngredientsView;

import java.util.List;

public class IngredientsPresenterImp implements IngredientsPresenterView, IngredientsItemCallback {

    private IngredientsView ingredientsView;
    private MealRepositoryView mealRepositoryView;
    public IngredientsPresenterImp(IngredientsView ingredientsView, MealRepositoryView mealRepositoryView){
        this.ingredientsView = ingredientsView;
        this.mealRepositoryView = mealRepositoryView;
    }
    @Override
    public void onSuccessResult(List<IngredientsItem> IngredientsItemList) {
        ingredientsView.showIngredientsData(IngredientsItemList);
    }

    @Override
    public void onFailuresResult(String errorMsg) {
        ingredientsView.showIngredientsErrorMsg(errorMsg);
    }


    @Override
    public void getIngredient() {
        mealRepositoryView.IngredientsNetworkCall(this);
    }
}
