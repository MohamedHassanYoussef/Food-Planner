package com.example.myfoodplaner.home.view;


import com.example.myfoodplaner.model.Dtopresenter.MealsItem;

import java.util.List;

public interface RandomMealView {
    public void showData(List<MealsItem> mealsItemList);
    public void showErrorMsg(String error);
}
