package com.example.myfoodplaner.model.Dtopresenter;

import java.util.ArrayList;

public class GeneratingListIngridentsArrayLists {
    public static ArrayList<Measurement> getIngridentsListArray(MealsDetail mealsDetail){
        ArrayList<Measurement> measurements = new ArrayList<>();
        setArray(mealsDetail,measurements);
        return measurements;
    }
    private static void setArray(MealsDetail mealsDetail, ArrayList<Measurement> measurements) {
        addToArray(mealsDetail.getStrIngredient1(),mealsDetail.getStrMeasure1(),measurements);
        addToArray(mealsDetail.getStrIngredient2(),mealsDetail.getStrMeasure2(),measurements);
        addToArray(mealsDetail.getStrIngredient3(),mealsDetail.getStrMeasure3(),measurements);
        addToArray(mealsDetail.getStrIngredient4(),mealsDetail.getStrMeasure4(),measurements);
        addToArray(mealsDetail.getStrIngredient5(),mealsDetail.getStrMeasure5(),measurements);
        addToArray(mealsDetail.getStrIngredient6(),mealsDetail.getStrMeasure6(),measurements);
        addToArray(mealsDetail.getStrIngredient7(),mealsDetail.getStrMeasure7(),measurements);
        addToArray(mealsDetail.getStrIngredient8(),mealsDetail.getStrMeasure8(),measurements);
        addToArray(mealsDetail.getStrIngredient9(),mealsDetail.getStrMeasure9(),measurements);
        addToArray(mealsDetail.getStrIngredient10(),mealsDetail.getStrMeasure10(),measurements);
        addToArray(mealsDetail.getStrIngredient11(),mealsDetail.getStrMeasure11(),measurements);
        addToArray(mealsDetail.getStrIngredient12(),mealsDetail.getStrMeasure12(),measurements);
        addToArray(mealsDetail.getStrIngredient13(),mealsDetail.getStrMeasure13(),measurements);
        addToArray(mealsDetail.getStrIngredient14(),mealsDetail.getStrMeasure14(),measurements);
        addToArray(mealsDetail.getStrIngredient15(),mealsDetail.getStrMeasure15(),measurements);

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
