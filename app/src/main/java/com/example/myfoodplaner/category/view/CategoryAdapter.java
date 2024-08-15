package com.example.myfoodplaner.category.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.myfoodplaner.R;
import com.example.myfoodplaner.category.view.OnCategoryDetailsClickListener;
import com.example.myfoodplaner.model.Dtopresenter.ListsDetails;

import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.ViewHolder> {

    private Context context;
    private List<ListsDetails> categoryDetailsList;
    private OnCategoryDetailsClickListener categoryDetailsClickListener;

    public CategoryAdapter(Context context, List<ListsDetails> categoryDetailsList, OnCategoryDetailsClickListener categoryDetailsClickListener) {
        this.context = context;
        this.categoryDetailsList = categoryDetailsList;
        this.categoryDetailsClickListener = categoryDetailsClickListener;
    }



    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_category_layout, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        ListsDetails categoriesDetailsItem = categoryDetailsList.get(position);
        holder.textViewcatagory.setText(categoriesDetailsItem.getStrMeal());
        Glide.with(context).load(categoriesDetailsItem.getStrMealThumb()).into(holder.imageView);

        holder.imageView.setOnClickListener(v -> {
            if (categoryDetailsClickListener != null) {
                categoryDetailsClickListener.onCategoryClick(categoriesDetailsItem);
            }
        });
    }

    @Override
    public int getItemCount() {
        return categoryDetailsList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView imageView;
        public TextView textViewcatagory;

        public ViewHolder(View v) {
            super(v);
            imageView = v.findViewById(R.id.categoryDetailsItemImage);
            textViewcatagory = v.findViewById(R.id.txtCategoryDetailsItemName);
        }
    }
    public void setList(List<ListsDetails> categoryDetailsList) {
        this.categoryDetailsList = categoryDetailsList;
        notifyDataSetChanged();
    }
}
