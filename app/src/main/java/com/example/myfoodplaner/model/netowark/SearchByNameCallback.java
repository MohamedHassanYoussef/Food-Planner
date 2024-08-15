package com.example.myfoodplaner.model.netowark;

import com.example.myfoodplaner.model.Dtopresenter.MealsItem;

import java.util.List;

public interface SearchByNameCallback {
    public void onSuccessResult(List<MealsItem> searchByNameList);
    public void onFailureResult(String errorMsg);
}
