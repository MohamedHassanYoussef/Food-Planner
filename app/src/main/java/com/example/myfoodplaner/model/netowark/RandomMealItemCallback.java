package com.example.myfoodplaner.model.netowark;

import com.example.myfoodplaner.model.Dtopresenter.MealsItem;

import java.util.List;

public interface RandomMealItemCallback {
    public void onSuccessResult(List<MealsItem> mealsItem);
    public void onFailureResult(String errorMsg);
}
