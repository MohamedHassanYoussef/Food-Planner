package com.example.myfoodplaner.model.netowark;

import com.example.myfoodplaner.model.Dtopresenter.ListsDetails;

import java.util.List;

public interface AreaDetailsCallback {
    public void onSuccessResult(List<ListsDetails> categoryDetailsList);
    public void onFailureResult(String errorMsg);
}