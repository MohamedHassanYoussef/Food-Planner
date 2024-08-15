package com.example.myfoodplaner.model.Dtopresenter;

import java.util.List;

import com.example.myfoodplaner.model.Dtopresenter.MealsItem;
import com.google.gson.annotations.SerializedName;

public class MealsItemResponse {

	@SerializedName("meals")
	private List<MealsItem> meals;

	public List<MealsItem> getRandomMealList(){
		return meals;
	}
}