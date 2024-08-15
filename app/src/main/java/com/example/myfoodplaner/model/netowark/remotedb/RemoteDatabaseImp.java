package com.example.myfoodplaner.model.netowark.remotedb;


import com.example.myfoodplaner.model.Dtopresenter.MealsItem;
import com.example.myfoodplaner.model.Dtopresenter.WeekPlan;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import io.reactivex.rxjava3.core.Completable;

public class RemoteDatabaseImp implements RemoteDatabaseListener{
    private DBHelper dbHelper;
    private FirebaseAuth firebaseAuth;

    public RemoteDatabaseImp() {
        dbHelper = new DBHelper();
        firebaseAuth = FirebaseAuth.getInstance();
    }
    @Override
    public Completable insertToFavorite(MealsItem mealsItem) {
        FirebaseUser currentUser = firebaseAuth.getCurrentUser();
        if (currentUser != null) {
            String email = currentUser.getEmail();
            String encodeEmail = encodeEmailForFirebase(email);
            dbHelper.addMealToFavorite(encodeEmail, mealsItem);
            return Completable.complete();
        } else {
            return Completable.error(new IllegalStateException("User is not authenticated"));
        }
    }


    @Override
    public Completable insertToWeekPlan(WeekPlan weekPlan) {
        String email = firebaseAuth.getCurrentUser().getEmail();
        String encodeEmail = encodeEmailForFirebase(email);
        dbHelper.addMealToPlan(encodeEmail, weekPlan);
        return Completable.complete();
    }

    @Override
    public void deleteFromWeekPlane(WeekPlan weekPlan) {
        String email = firebaseAuth.getCurrentUser().getEmail();
        String encodeEmail = encodeEmailForFirebase(email);
        dbHelper.deleteMealPlan(encodeEmail,weekPlan);
    }

    @Override
    public void deleteFromFavorite(MealsItem mealsItem) {
        String email = firebaseAuth.getCurrentUser().getEmail();
        String encodeEmail = encodeEmailForFirebase(email);
        dbHelper.deleteMealFromFavorite(encodeEmail,mealsItem);
    }

    private String encodeEmailForFirebase(String email) {
        return email.replace(".", ",");
    }
}
