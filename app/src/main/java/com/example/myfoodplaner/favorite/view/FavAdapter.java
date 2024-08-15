package com.example.myfoodplaner.favorite.view;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.myfoodplaner.R;
import com.example.myfoodplaner.model.Dtopresenter.ListsDetails;
import com.example.myfoodplaner.model.Dtopresenter.MealsItem;
import com.makeramen.roundedimageview.RoundedImageView;

import java.util.List;

public class FavAdapter extends RecyclerView.Adapter<FavAdapter.ViewHolder>{

    private final Context context;
    private List<MealsItem> favMealsList;
    private final OnFavoriteMealClickListener listener;


    public FavAdapter(Context context, List<MealsItem> favMealsList, OnFavoriteMealClickListener listener) {
        this.context = context;
        this.favMealsList = favMealsList;
        this.listener = listener;
    }

    public void setFavMealsList(List<MealsItem> favMealsList) {
        this.favMealsList = favMealsList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View v = layoutInflater.inflate(R.layout.fan_item, parent, false);
        return new ViewHolder(v);
    }



    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {


        MealsItem meal = favMealsList.get(position);
        holder.mealNameTextView.setText(meal.getStrMeal());
        Glide.with(context)
                .load(meal.getStrMealThumb())
                .into(holder.mealImage);

        holder.cardView.setOnClickListener(v -> {
            Bundle bundle = new Bundle();
            bundle.putSerializable("item", meal);
            Navigation.findNavController(v).navigate(R.id.action_favoriteFragment_to_mealDetailsFragment, bundle);
        });
        holder.deleteMealIcon.setOnClickListener(v -> listener.onDeleteItemClick(meal));
    }

    @Override
    public int getItemCount() {
        return favMealsList.size();
    }

    public  class ViewHolder extends RecyclerView.ViewHolder {
        TextView mealNameTextView;
        RoundedImageView mealImage;
        RoundedImageView deleteMealIcon;
        CardView cardView;

        public ViewHolder(@NonNull View v) {
            super(v);
            mealNameTextView = v.findViewById(R.id.tv_meal_title_fav_item);
            mealImage = v.findViewById(R.id.imageView_meal_fav_item);
            deleteMealIcon = v.findViewById(R.id.ic_remove_meal_fav_item);
            cardView = v.findViewById(R.id.cardViewRandomMeal);
        }
    }
}
