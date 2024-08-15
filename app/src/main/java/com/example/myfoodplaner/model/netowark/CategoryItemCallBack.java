package com.example.myfoodplaner.model.netowark;

import com.example.myfoodplaner.model.Dtopresenter.CategoriesItem;

import java.util.List;

public interface CategoryItemCallBack {

    public void  onSuccessResult (List<CategoriesItem> categoriesItemList);
    public void onFailureResult(String errorMsg);
}
