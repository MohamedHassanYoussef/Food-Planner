package com.example.myfoodplaner.favorite.presenter;


import com.example.myfoodplaner.model.Dtopresenter.MealsItem;

import java.util.List;

import io.reactivex.rxjava3.core.Single;

public interface FavouriteMealPresenterView {
    public Single<List<MealsItem>> getFavMealList();
    public void deleteMeal(MealsItem mealsItem);
}