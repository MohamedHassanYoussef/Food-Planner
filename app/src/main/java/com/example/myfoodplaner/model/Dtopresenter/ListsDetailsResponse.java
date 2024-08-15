package com.example.myfoodplaner.model.Dtopresenter;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ListsDetailsResponse {

	@SerializedName("meals")
	private List<ListsDetails> meals;

	public List<ListsDetails> getListDetails(){
		return meals;
	}
}