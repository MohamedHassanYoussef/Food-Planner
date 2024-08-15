package com.example.myfoodplaner.listsdetail.view;


import com.example.myfoodplaner.model.Dtopresenter.MealsDetail;

import java.util.List;

public interface ListDetailView {
    public void showMealDetailData(List<MealsDetail> mealsItem);
    public void addMealToFav(MealsDetail mealsItem);
    public void showMealDetailErrorMsg(String error);
}
