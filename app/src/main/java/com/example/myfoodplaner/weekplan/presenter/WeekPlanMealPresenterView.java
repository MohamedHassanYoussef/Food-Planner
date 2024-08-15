package com.example.myfoodplaner.weekplan.presenter;


import com.example.myfoodplaner.model.Dtopresenter.WeekPlan;

import java.util.List;

import io.reactivex.rxjava3.core.Single;

public interface WeekPlanMealPresenterView {
    public Single<List<WeekPlan>> getWeekPlanMealList();
    public void deleteMeal(WeekPlan weekPlan);
    Single<List<WeekPlan>> getMealsForDate(String date);
}
