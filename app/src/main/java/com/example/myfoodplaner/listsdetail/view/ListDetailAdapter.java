package com.example.myfoodplaner.listsdetail.view;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.myfoodplaner.R;
import com.example.myfoodplaner.model.Dtopresenter.Measurement;

import java.util.ArrayList;
import java.util.List;

public class ListDetailAdapter extends RecyclerView.Adapter<ListDetailAdapter.ViewHolder>{
    private Context context;
    private List<Measurement> mealItemDetailList;

    public ListDetailAdapter(Context context) {
        this.context = context;
        this.mealItemDetailList = new ArrayList<>();
    }
    public void setMealItemDetailList(List<Measurement> mealItemDetailList) {
        this.mealItemDetailList = mealItemDetailList;
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.ingredients_item_meal_details, parent, false);
        context = parent.getContext();
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Measurement mealsItem = mealItemDetailList.get(position);
        holder.tvIngridentName.setText(mealsItem.getIngredientName());
        holder.tvIngridentMeasure.setText(mealsItem.getIngredientMeasure());
        Glide.with(context).load("https://www.themealdb.com/images/ingredients/" + mealsItem.getIngredientName()+ "-Small.png").into(holder.ingridentImage);
    }

    @Override
    public int getItemCount() {
        return mealItemDetailList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView ingridentImage;
        private TextView tvIngridentName;
        private TextView tvIngridentMeasure;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ingridentImage = itemView.findViewById(R.id.imageViewIngredientImageBGItem);
            tvIngridentName = itemView.findViewById(R.id.textViewIngredientNameItem_mealDetails);
            tvIngridentMeasure = itemView.findViewById(R.id.textViewIngredientMeasureItem);
        }
    }
}
