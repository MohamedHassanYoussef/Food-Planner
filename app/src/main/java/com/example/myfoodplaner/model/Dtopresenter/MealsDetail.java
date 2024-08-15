package com.example.myfoodplaner.model.Dtopresenter;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
@Entity(tableName = "mealdetail")
public class MealsDetail implements Serializable {
	@Ignore
	@SerializedName("strImageSource")
	private Object strImageSource;

	@SerializedName("strIngredient10")
	private String strIngredient10;

	public void setStrMeasure14(String strMeasure14) {
		this.strMeasure14 = strMeasure14;
	}

	public void setStrMeasure15(String strMeasure15) {
		this.strMeasure15 = strMeasure15;
	}

	public void setStrYoutube(String strYoutube) {
		this.strYoutube = strYoutube;
	}

	public void setStrMeasure2(String strMeasure2) {
		this.strMeasure2 = strMeasure2;
	}

	public void setStrMeasure9(String strMeasure9) {
		this.strMeasure9 = strMeasure9;
	}

	public void setStrMeasure7(String strMeasure7) {
		this.strMeasure7 = strMeasure7;
	}

	public void setStrMeasure8(String strMeasure8) {
		this.strMeasure8 = strMeasure8;
	}

	public void setStrMeasure5(String strMeasure5) {
		this.strMeasure5 = strMeasure5;
	}

	public void setStrMeasure6(String strMeasure6) {
		this.strMeasure6 = strMeasure6;
	}

	public void setStrMeasure3(String strMeasure3) {
		this.strMeasure3 = strMeasure3;
	}

	public void setStrMeasure4(String strMeasure4) {
		this.strMeasure4 = strMeasure4;
	}

	public void setStrMeasure1(String strMeasure1) {
		this.strMeasure1 = strMeasure1;
	}

	public void setStrMeal(String strMeal) {
		this.strMeal = strMeal;
	}

	public void setStrMeasure12(String strMeasure12) {
		this.strMeasure12 = strMeasure12;
	}

	public void setStrMeasure13(String strMeasure13) {
		this.strMeasure13 = strMeasure13;
	}

	public void setStrMeasure10(String strMeasure10) {
		this.strMeasure10 = strMeasure10;
	}

	public void setStrMeasure11(String strMeasure11) {
		this.strMeasure11 = strMeasure11;
	}

	public void setStrIngredient15(String strIngredient15) {
		this.strIngredient15 = strIngredient15;
	}

	public void setStrIngredient5(String strIngredient5) {
		this.strIngredient5 = strIngredient5;
	}

	public void setStrIngredient4(String strIngredient4) {
		this.strIngredient4 = strIngredient4;
	}

	public void setStrIngredient7(String strIngredient7) {
		this.strIngredient7 = strIngredient7;
	}

	public void setStrIngredient6(String strIngredient6) {
		this.strIngredient6 = strIngredient6;
	}

	public void setStrIngredient9(String strIngredient9) {
		this.strIngredient9 = strIngredient9;
	}

	public void setStrIngredient8(String strIngredient8) {
		this.strIngredient8 = strIngredient8;
	}

	public void setStrMealThumb(String strMealThumb) {
		this.strMealThumb = strMealThumb;
	}

	public void setStrTags(String strTags) {
		this.strTags = strTags;
	}

	public void setIdMeal(String idMeal) {
		this.idMeal = idMeal;
	}

	public void setStrArea(String strArea) {
		this.strArea = strArea;
	}

	public void setStrInstructions(String strInstructions) {
		this.strInstructions = strInstructions;
	}

	public void setStrIngredient1(String strIngredient1) {
		this.strIngredient1 = strIngredient1;
	}

	public void setStrIngredient3(String strIngredient3) {
		this.strIngredient3 = strIngredient3;
	}

	public void setStrIngredient2(String strIngredient2) {
		this.strIngredient2 = strIngredient2;
	}

	public void setStrIngredient10(String strIngredient10) {
		this.strIngredient10 = strIngredient10;
	}

	public void setStrIngredient12(String strIngredient12) {
		this.strIngredient12 = strIngredient12;
	}

	public void setStrIngredient11(String strIngredient11) {
		this.strIngredient11 = strIngredient11;
	}

	public void setStrIngredient14(String strIngredient14) {
		this.strIngredient14 = strIngredient14;
	}

	public void setStrCategory(String strCategory) {
		this.strCategory = strCategory;
	}

	public void setStrIngredient13(String strIngredient13) {
		this.strIngredient13 = strIngredient13;
	}

	@SerializedName("strIngredient12")
	private String strIngredient12;

	@SerializedName("strIngredient11")
	private String strIngredient11;

	@SerializedName("strIngredient14")
	private String strIngredient14;

	@SerializedName("strCategory")
	private String strCategory;

	@SerializedName("strIngredient13")
	private String strIngredient13;
	@Ignore
	@SerializedName("strIngredient16")
	private Object strIngredient16;

	@SerializedName("strIngredient15")
	private String strIngredient15;
	@Ignore
	@SerializedName("strIngredient18")
	private Object strIngredient18;
	@Ignore
	@SerializedName("strIngredient17")
	private Object strIngredient17;

	@SerializedName("strArea")
	private String strArea;
	@Ignore
	@SerializedName("strCreativeCommonsConfirmed")
	private Object strCreativeCommonsConfirmed;
	@Ignore
	@SerializedName("strIngredient19")
	private Object strIngredient19;

	@SerializedName("strTags")
	private String strTags;
	@PrimaryKey
	@NonNull
	@SerializedName("idMeal")
	private String idMeal;

	@SerializedName("strInstructions")
	private String strInstructions;

	@SerializedName("strIngredient1")
	private String strIngredient1;

	@SerializedName("strIngredient3")
	private String strIngredient3;

	@SerializedName("strIngredient2")
	private String strIngredient2;
	@Ignore
	@SerializedName("strIngredient20")
	private Object strIngredient20;

	@SerializedName("strIngredient5")
	private String strIngredient5;

	@SerializedName("strIngredient4")
	private String strIngredient4;

	@SerializedName("strIngredient7")
	private String strIngredient7;

	@SerializedName("strIngredient6")
	private String strIngredient6;

	@SerializedName("strIngredient9")
	private String strIngredient9;

	@SerializedName("strIngredient8")
	private String strIngredient8;

	@SerializedName("strMealThumb")
	private String strMealThumb;
	@Ignore
	@SerializedName("strMeasure20")
	private Object strMeasure20;

	@SerializedName("strYoutube")
	private String strYoutube;

	@SerializedName("strMeal")
	private String strMeal;

	@SerializedName("strMeasure12")
	private String strMeasure12;

	@SerializedName("strMeasure13")
	private String strMeasure13;

	@SerializedName("strMeasure10")
	private String strMeasure10;

	@SerializedName("strMeasure11")
	private String strMeasure11;
	@Ignore
	@SerializedName("dateModified")
	private Object dateModified;
	@Ignore
	@SerializedName("strDrinkAlternate")
	private Object strDrinkAlternate;
	@Ignore
	@SerializedName("strSource")
	private Object strSource;

	@SerializedName("strMeasure9")
	private String strMeasure9;

	@SerializedName("strMeasure7")
	private String strMeasure7;

	@SerializedName("strMeasure8")
	private String strMeasure8;

	@SerializedName("strMeasure5")
	private String strMeasure5;

	@SerializedName("strMeasure6")
	private String strMeasure6;

	@SerializedName("strMeasure3")
	private String strMeasure3;

	@SerializedName("strMeasure4")
	private String strMeasure4;

	@SerializedName("strMeasure1")
	private String strMeasure1;
	@Ignore
	@SerializedName("strMeasure18")
	private Object strMeasure18;

	@SerializedName("strMeasure2")
	private String strMeasure2;
	@Ignore
	@SerializedName("strMeasure19")
	private Object strMeasure19;
	@Ignore
	@SerializedName("strMeasure16")
	private Object strMeasure16;
	@Ignore
	@SerializedName("strMeasure17")
	private Object strMeasure17;

	@SerializedName("strMeasure14")
	private String strMeasure14;

	@SerializedName("strMeasure15")
	private String strMeasure15;
	@Ignore
	public Object getStrImageSource(){
		return strImageSource;
	}

	public String getStrIngredient10(){
		return strIngredient10;
	}

	public String getStrIngredient12(){
		return strIngredient12;
	}

	public String getStrIngredient11(){
		return strIngredient11;
	}

	public String getStrIngredient14(){
		return strIngredient14;
	}

	public String getStrCategory(){
		return strCategory;
	}

	public String getStrIngredient13(){
		return strIngredient13;
	}
	@Ignore
	public Object getStrIngredient16(){
		return strIngredient16;
	}

	public String getStrIngredient15(){
		return strIngredient15;
	}
	@Ignore
	public Object getStrIngredient18(){
		return strIngredient18;
	}
	@Ignore
	public Object getStrIngredient17(){
		return strIngredient17;
	}

	public String getStrArea(){
		return strArea;
	}
	@Ignore
	public Object getStrCreativeCommonsConfirmed(){
		return strCreativeCommonsConfirmed;
	}
	@Ignore
	public Object getStrIngredient19(){
		return strIngredient19;
	}

	public String getStrTags(){
		return strTags;
	}

	public String getIdMeal(){
		return idMeal;
	}

	public String getStrInstructions(){
		return strInstructions;
	}

	public String getStrIngredient1(){
		return strIngredient1;
	}

	public String getStrIngredient3(){
		return strIngredient3;
	}

	public String getStrIngredient2(){
		return strIngredient2;
	}
	@Ignore
	public Object getStrIngredient20(){
		return strIngredient20;
	}

	public String getStrIngredient5(){
		return strIngredient5;
	}

	public String getStrIngredient4(){
		return strIngredient4;
	}

	public String getStrIngredient7(){
		return strIngredient7;
	}

	public String getStrIngredient6(){
		return strIngredient6;
	}

	public String getStrIngredient9(){
		return strIngredient9;
	}

	public String getStrIngredient8(){
		return strIngredient8;
	}

	public String getStrMealThumb(){
		return strMealThumb;
	}
	@Ignore
	public Object getStrMeasure20(){
		return strMeasure20;
	}

	public String getStrYoutube(){
		return strYoutube;
	}

	public String getStrMeal(){
		return strMeal;
	}

	public String getStrMeasure12(){
		return strMeasure12;
	}

	public String getStrMeasure13(){
		return strMeasure13;
	}

	public String getStrMeasure10(){
		return strMeasure10;
	}

	public String getStrMeasure11(){
		return strMeasure11;
	}
	@Ignore
	public Object getDateModified(){
		return dateModified;
	}
	@Ignore
	public Object getStrDrinkAlternate(){
		return strDrinkAlternate;
	}
	@Ignore
	public Object getStrSource(){
		return strSource;
	}

	public String getStrMeasure9(){
		return strMeasure9;
	}

	public String getStrMeasure7(){
		return strMeasure7;
	}

	public String getStrMeasure8(){
		return strMeasure8;
	}

	public String getStrMeasure5(){
		return strMeasure5;
	}

	public String getStrMeasure6(){
		return strMeasure6;
	}

	public String getStrMeasure3(){
		return strMeasure3;
	}

	public String getStrMeasure4(){
		return strMeasure4;
	}

	public String getStrMeasure1(){
		return strMeasure1;
	}
	@Ignore
	public Object getStrMeasure18(){
		return strMeasure18;
	}

	public String getStrMeasure2(){
		return strMeasure2;
	}
	@Ignore
	public Object getStrMeasure19(){
		return strMeasure19;
	}
	@Ignore
	public Object getStrMeasure16(){
		return strMeasure16;
	}
	@Ignore
	public Object getStrMeasure17(){
		return strMeasure17;
	}

	public String getStrMeasure14(){
		return strMeasure14;
	}

	public String getStrMeasure15(){
		return strMeasure15;
	}
}