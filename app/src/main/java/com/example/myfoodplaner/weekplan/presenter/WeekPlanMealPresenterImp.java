package com.example.myfoodplaner.weekplan.presenter;


import android.util.Log;

import com.example.myfoodplaner.model.Dtopresenter.WeekPlan;
import com.example.myfoodplaner.model.MealRepositoryView;
import com.example.myfoodplaner.weekplan.view.WeekPlanMealView;

import java.util.List;

import io.reactivex.rxjava3.core.Single;

public class WeekPlanMealPresenterImp implements WeekPlanMealPresenterView{

    private WeekPlanMealView weekPlanMealView;
    private MealRepositoryView mealRepositoryView;

    public WeekPlanMealPresenterImp(WeekPlanMealView weekPlanMealView,MealRepositoryView mealRepositoryView){
        this.weekPlanMealView = weekPlanMealView;
        this.mealRepositoryView = mealRepositoryView;
    }
    @Override
    public Single<List<WeekPlan>> getWeekPlanMealList() {
        return mealRepositoryView.getWeekPlanMeals();
    }

    @Override
    public void deleteMeal(WeekPlan weekPlan) {
        mealRepositoryView.deleteWeekPlanMeal(weekPlan);
        Log.i("TAG", "deleteMeal: Presenter");
    }

    @Override
    public Single<List<WeekPlan>> getMealsForDate(String date) {
        return mealRepositoryView.getMealsForDate(date);
    }

}
