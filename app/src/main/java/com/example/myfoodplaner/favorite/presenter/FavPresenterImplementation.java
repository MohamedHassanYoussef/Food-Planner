package com.example.myfoodplaner.favorite.presenter;

import android.util.Log;


import com.example.myfoodplaner.favorite.presenter.FavouriteMealPresenterView;
import com.example.myfoodplaner.favorite.view.FavouriteMealView;
import com.example.myfoodplaner.model.Dtopresenter.MealsItem;
import com.example.myfoodplaner.model.MealRepositoryView;

import java.util.List;

import io.reactivex.rxjava3.core.Single;

public class FavPresenterImplementation implements FavouriteMealPresenterView {
    private static FavPresenterImplementation favoritePresenter;
    private FavouriteMealView favouriteMealView;
    private MealRepositoryView mealRepositoryView;

    public static synchronized FavPresenterImplementation getInstance(FavouriteMealView favouriteMealView, MealRepositoryView mealRepositoryView) {
        if (favoritePresenter == null) {
            favoritePresenter = new FavPresenterImplementation(favouriteMealView, mealRepositoryView);
        }
        return favoritePresenter;
    }

    private FavPresenterImplementation(FavouriteMealView favouriteMealView, MealRepositoryView mealRepositoryView) {
        this.favouriteMealView = favouriteMealView;
        this.mealRepositoryView = mealRepositoryView;
    }



    @Override
    public Single<List<MealsItem>> getFavMealList() {
        Log.i("TAG", "getFavoriteMeals: Fav Presenter Live Data");
        return mealRepositoryView.getFavoriteMeals();
    }

    @Override
    public void deleteMeal(MealsItem mealsItem) {
        mealRepositoryView.deleteMeal(mealsItem);
        Log.i("TAG", "deleteMeal: Presenter");
    }
}
