package com.example.myfoodplaner.weekplan.view;


import com.example.myfoodplaner.model.Dtopresenter.WeekPlan;

public interface OnWeekPlanMealClickListener {
    void onDeleteItemClick(WeekPlan weekPlan);
    void onWeekPlanMealClick(WeekPlan weekPlan);

}
