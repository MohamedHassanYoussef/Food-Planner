package com.example.myfoodplaner.model.netowark;

import com.example.myfoodplaner.model.Dtopresenter.IngredientsItem;

import java.util.List;

public interface IngredientsItemCallback {
    public void onSuccessResult(List<IngredientsItem>ingredientsItemList);
    public void onFailuresResult( String errorMsg);
}
