package com.example.myfoodplaner.search.view.countrysearch;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myfoodplaner.R;
import com.example.myfoodplaner.model.Dtopresenter.AreaItem;

import java.util.ArrayList;
import java.util.List;

public class AreaSearchAdapter extends RecyclerView.Adapter<AreaSearchAdapter.ViewHolder> {
    private Context context;
    private List<AreaItem> areaItemList;
    private List<AreaItem> filteredList;
    private OnAreaClickListener onAreaClickListener;


    public AreaSearchAdapter(Context context, List<AreaItem> areaItemList, OnAreaClickListener onAreaClickListener) {
        this.context = context;
        this.areaItemList = areaItemList;
        this.filteredList = new ArrayList<>(areaItemList);
        this.onAreaClickListener=onAreaClickListener;

    }

    public void setList(List<AreaItem> areaItemList) {
        this.areaItemList = areaItemList;
        this.filteredList = new ArrayList<>(areaItemList);
        notifyDataSetChanged();
    }

    public void filter(String query) {
        filteredList.clear();
        if (query.isEmpty()) {
            filteredList.addAll(areaItemList);
        } else {
            for (AreaItem item : areaItemList) {
                if (item.getStrArea().toLowerCase().contains(query.toLowerCase())) {
                    filteredList.add(item);
                }
            }
        }
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.country_search_layout, parent, false);
        context = parent.getContext();
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        AreaItem areaItem = filteredList.get(position);
        holder.txtAreaName.setText(areaItem.getStrArea());

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onAreaClickListener.onAreaClick(areaItem);
            }
        });
    }

    @Override
    public int getItemCount() {
        return filteredList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView txtAreaName;
        private CardView cardView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtAreaName = itemView.findViewById(R.id.country_TextView_search);
            cardView = itemView.findViewById(R.id.card_country_search_item);
        }
    }


}
