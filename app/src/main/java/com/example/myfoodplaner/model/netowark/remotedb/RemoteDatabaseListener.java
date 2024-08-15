package com.example.myfoodplaner.model.netowark.remotedb;


import com.example.myfoodplaner.model.Dtopresenter.MealsItem;
import com.example.myfoodplaner.model.Dtopresenter.WeekPlan;

import io.reactivex.rxjava3.core.Completable;

public interface RemoteDatabaseListener {
    Completable insertToFavorite(MealsItem mealsItem);
    Completable insertToWeekPlan(WeekPlan weekPlan);
    void deleteFromWeekPlane(WeekPlan weekPlan);
    void deleteFromFavorite(MealsItem mealsItem);
}
