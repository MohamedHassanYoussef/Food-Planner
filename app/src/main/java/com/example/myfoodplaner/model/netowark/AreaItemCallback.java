package com.example.myfoodplaner.model.netowark;

import com.example.myfoodplaner.model.Dtopresenter.AreaItem;

import java.util.List;

public interface AreaItemCallback {
    public void onSuccessResult(List<AreaItem>areaItemList);
    public void onFailureResult(String errorMsg);
}
