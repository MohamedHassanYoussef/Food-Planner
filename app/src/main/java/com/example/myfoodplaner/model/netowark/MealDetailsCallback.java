package com.example.myfoodplaner.model.netowark;

import com.example.myfoodplaner.model.Dtopresenter.MealsDetail;

import java.util.List;

public interface MealDetailsCallback {
    public void onSuccessResult(List<MealsDetail> mealsDetailList);
    public void onFailureResult(String errorMsg);
}
