package com.example.myfoodplaner.model.Dtopresenter;

import java.util.ArrayList;

public class GeneratingIngridentsArrayLists {
    public static ArrayList<Measurement> getIngridentsArray(MealsItem mealsItem){
        ArrayList<Measurement> measurements = new ArrayList<>();
        setArray(mealsItem,measurements);
        return measurements;
    }


    private static void setArray(MealsItem mealsItem, ArrayList<Measurement> measurements) {
        addToArray(mealsItem.getStrIngredient1(),mealsItem.getStrMeasure1(),measurements);
        addToArray(mealsItem.getStrIngredient2(),mealsItem.getStrMeasure2(),measurements);
        addToArray(mealsItem.getStrIngredient3(),mealsItem.getStrMeasure3(),measurements);
        addToArray(mealsItem.getStrIngredient4(),mealsItem.getStrMeasure4(),measurements);
        addToArray(mealsItem.getStrIngredient5(),mealsItem.getStrMeasure5(),measurements);
        addToArray(mealsItem.getStrIngredient6(),mealsItem.getStrMeasure6(),measurements);
        addToArray(mealsItem.getStrIngredient7(),mealsItem.getStrMeasure7(),measurements);
        addToArray(mealsItem.getStrIngredient8(),mealsItem.getStrMeasure8(),measurements);
        addToArray(mealsItem.getStrIngredient9(),mealsItem.getStrMeasure9(),measurements);
        addToArray(mealsItem.getStrIngredient10(),mealsItem.getStrMeasure10(),measurements);
        addToArray(mealsItem.getStrIngredient11(),mealsItem.getStrMeasure11(),measurements);
        addToArray(mealsItem.getStrIngredient12(),mealsItem.getStrMeasure12(),measurements);
        addToArray(mealsItem.getStrIngredient13(),mealsItem.getStrMeasure13(),measurements);
        addToArray(mealsItem.getStrIngredient14(),mealsItem.getStrMeasure14(),measurements);
        addToArray(mealsItem.getStrIngredient15(),mealsItem.getStrMeasure15(),measurements);
        addToArray(mealsItem.getStrIngredient16(),mealsItem.getStrMeasure16(),measurements);
        addToArray(mealsItem.getStrIngredient17(),mealsItem.getStrMeasure17(),measurements);
        addToArray(mealsItem.getStrIngredient18(),mealsItem.getStrMeasure18(),measurements);
        addToArray(mealsItem.getStrIngredient19(),mealsItem.getStrMeasure19(),measurements);
        addToArray(mealsItem.getStrIngredient20(),mealsItem.getStrMeasure20(),measurements);
    }

    private static void addToArray(String strIngredient1, String strMeasure1, ArrayList<Measurement> measurements) {
        if (strIngredient1 != null && !strIngredient1.isEmpty()) {
            String measure;
            if(strMeasure1 == null){
                measure = "";
            }else{
                measure = strMeasure1;
            }

            measurements.add(new Measurement(strIngredient1, measure));
        }
    }

}
