package com.example.myfoodplaner.model.netowark.remotedb;


import android.content.Context;

import androidx.annotation.NonNull;

import com.example.myfoodplaner.model.Dtopresenter.MealsItem;
import com.example.myfoodplaner.model.Dtopresenter.WeekPlan;
import com.example.myfoodplaner.model.netowark.database.AppDataBase;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


import java.util.ArrayList;
import java.util.List;

public class DBHelper {
    private DatabaseReference databaseReference;

    public DBHelper() {
        databaseReference = FirebaseDatabase.getInstance().getReference();
    }

    public void addMealToFavorite(String email, MealsItem mealsItem) {
        databaseReference.child("favMeal").child(email).child(mealsItem.getIdMeal()).setValue(mealsItem);
    }

    public void getAllFavorite( Context context) {
        String encodeEmail = encodeEmailForFirebase(FirebaseAuth.getInstance().getCurrentUser().getEmail());
        databaseReference.child("favMeal")
                .child(encodeEmail)
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        List<MealsItem> favItemList = new ArrayList<>();

                        for(DataSnapshot data: snapshot.getChildren()){
                            favItemList.add(data.getValue(MealsItem.class));
                        }
                        new Thread(()->{  for (MealsItem item : favItemList){
                            AppDataBase.getInstance(context).getMealsDAO().insertMealToFavorite(item);
                        }}).start();

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
    }

    public void getAllFavoriteWeelPlan( Context context) {
        String encodeEmail = encodeEmailForFirebase(FirebaseAuth.getInstance().getCurrentUser().getEmail());
        databaseReference.child("planMeal")
                .child(encodeEmail)
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        List<WeekPlan>weekPlanItemList = new ArrayList<>();

                        for(DataSnapshot data: snapshot.getChildren()){
                            weekPlanItemList.add(data.getValue(WeekPlan.class));
                        }
                        new Thread(()->{  for (WeekPlan weekPlan  : weekPlanItemList){
                            AppDataBase.getInstance(context).getMealsDAO().insertWeekPlanMealToCalender(weekPlan);
                        }}).start();

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
    }


    public void deleteMealFromFavorite(String email, MealsItem mealsItem) {
        databaseReference.child("favMeal").child(email).child(mealsItem.getIdMeal()).removeValue();
    }

    public void addMealToPlan(String email, WeekPlan weekPlan) {
        databaseReference.child("planMeal").child(email).child(weekPlan.getIdMeal()).setValue(weekPlan);
    }

    public void deleteMealPlan(String email, WeekPlan weekPlan) {
        databaseReference.child("planMeal").child(email).child(weekPlan.getIdMeal()).removeValue();
    }
    private String encodeEmailForFirebase(String email) {
        return email.replace(".", ",");
    }
}
