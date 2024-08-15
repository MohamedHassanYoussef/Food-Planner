package com.example.myfoodplaner.weekplan.view;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.icu.text.SimpleDateFormat;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;
import android.widget.Toast;

import com.example.myfoodplaner.R;
import com.example.myfoodplaner.model.Dtopresenter.WeekPlan;
import com.example.myfoodplaner.model.MealRepositoryImpl;
import com.example.myfoodplaner.model.netowark.MealRemoteDataSourceImpl;
import com.example.myfoodplaner.model.netowark.database.MealLocalDataSourceImpl;
import com.example.myfoodplaner.weekplan.presenter.WeekPlanMealPresenterImp;
import com.example.myfoodplaner.weekplan.presenter.WeekPlanMealPresenterView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class CalenderFragment extends Fragment implements WeekPlanMealView, OnWeekPlanMealClickListener {
    private OnWeekPlanMealClickListener onWeekPlanMealClickListener ;
    private RecyclerView recyclerView;
    private WeekPlanMealAdapter weekPlanMealAdapter ;
    private WeekPlanMealPresenterView weekPlanMealPresenterView;
    private Single<List<WeekPlan>> weekPlanMealList;
    private Calendar calendar;
    private CalendarView calendarView;
    private WeekPlan date;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_calender, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        calendarView = view.findViewById(R.id.calendarView);
        calendar = Calendar.getInstance();
        calendarView.setDate(calendar.getTimeInMillis());
        calendarView.setFirstDayOfWeek(Calendar.SUNDAY);


        weekPlanMealPresenterView = new WeekPlanMealPresenterImp(this, MealRepositoryImpl.getInstance(MealRemoteDataSourceImpl.getInstance(), MealLocalDataSourceImpl.getInstance(requireActivity())));

        recyclerView = view.findViewById(R.id.weekPlanRecyclerView);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(requireActivity(), 2);
        recyclerView.setLayoutManager(gridLayoutManager);

        weekPlanMealAdapter = new WeekPlanMealAdapter(requireActivity(), new ArrayList<>(), this, weekPlanMealPresenterView);
        recyclerView.setAdapter(weekPlanMealAdapter);

        showList();
        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                calendar.set(year, month, dayOfMonth);
                String selectedDate = getStringFromDate(calendar.getTime());
                getMealsForDate(selectedDate);
                Log.i("TAG", "onSelectedDayChange: "+selectedDate);
            }
        });
    }
    private void getMealsForDate(String selectedDate) {

        weekPlanMealPresenterView.getMealsForDate(selectedDate)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(planItemList -> {
                    updateUIWithMeals(planItemList);
                }, throwable -> {
                    Log.e("TAG", "Error fetching meals: " + throwable.getMessage());
                });
    }

    private void updateUIWithMeals(List<WeekPlan> mealsItemList) {
        weekPlanMealAdapter.setWeekPlanMealList(mealsItemList);
        weekPlanMealAdapter.notifyDataSetChanged();
    }

    @Override
    public void onDeleteItemClick(WeekPlan weekPlan) {
        new AlertDialog.Builder(getContext())
                .setTitle("Delete Meal")
                .setMessage("Are you sure you want to delete this meal?")
                .setPositiveButton(android.R.string.yes, (dialog, whichButton) -> {
                    weekPlanMealPresenterView.deleteMeal(weekPlan);
                    Toast.makeText(getActivity(), "Deleted Successfully", Toast.LENGTH_SHORT).show();
                    showList(); // Refresh list after deletion
                })
                .setNegativeButton(android.R.string.no, null)
                .show();
    }

    @Override
    public void onWeekPlanMealClick(WeekPlan weekPlan) {
        Bundle bundle = new Bundle();
        bundle.putSerializable("weekPlan", weekPlan);
        Navigation.findNavController(requireView()).navigate(R.id.action_calenderFragment_to_listDetailFragment, bundle);
    }

    private String getStringFromDate(Date date) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.US);
        return format.format(date);
    }

    private String getStringFromDate(int year, int month, int dayOfMonth) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month, dayOfMonth);
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.US);
        return format.format(calendar.getTime());
    }
    @Override
    public void deleteWeekPlan(WeekPlan weekPlan) {

    }
    @Override
    public void showList() {
        weekPlanMealList = weekPlanMealPresenterView.getWeekPlanMealList();
        Log.i("TAG", "showList: "+weekPlanMealList);
        weekPlanMealList.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(weekPlanItemList -> {
                    weekPlanMealAdapter.setWeekPlanMealList(weekPlanItemList);
                    weekPlanMealAdapter.notifyDataSetChanged();
                }, throwable -> {
                    Log.i("TAG", "Unable to show Meal because: "+throwable.getMessage());
                });
    }
}

