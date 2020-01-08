package com.example.fininuiapplication.adapter;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fininuiapplication.R;
import com.example.fininuiapplication.model.SuggestionDto;
import com.example.fininuiapplication.model.TransactionsDto;

import java.util.List;

public class SuggestionAdapter extends RecyclerView.Adapter<SuggestionAdapter.ViewHolder> {

    private static final String FOOD_DRINK = "Food & Drink";
    private static final String HOUSING = "Housing";
    private static final String TRANSPORTATION = "Transportation";

    private List<SuggestionDto> suggestionDtoList;
    Context context;


    public SuggestionAdapter(Context context, List<SuggestionDto> transactionsDtoList) {
        this.context = context;
        this.suggestionDtoList = transactionsDtoList;
    }

    @Override
    @NonNull
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View groceryProductView = LayoutInflater.from(parent.getContext()).inflate(R.layout.suggestion_list, parent, false);
        return new ViewHolder(groceryProductView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        SuggestionDto suggestionDto = suggestionDtoList.get(position);

        if (suggestionDto != null) {

            String name = suggestionDto.getName();
            holder.name.setText(name);

            holder.icon.setImageDrawable(getImageAccordingToName(name, holder.icon));
            String priceStr = suggestionDto.getPrice() + "";
            holder.price.setText(priceStr);
        }
    }

    private Drawable getImageAccordingToName(String name, ImageView icon) {
        if (name.equalsIgnoreCase(FOOD_DRINK)) {
            return ContextCompat.getDrawable(context, R.mipmap.ic_person_icon);
        } else if (name.equalsIgnoreCase(HOUSING)) {
            return ContextCompat.getDrawable(context, R.mipmap.ic_mobile_icon);
        } else if (name.equalsIgnoreCase(TRANSPORTATION)) {
            return ContextCompat.getDrawable(context, R.mipmap.ic_srike_icon);
        }
        return null;
    }

    @Override
    public int getItemCount() {
        if (suggestionDtoList != null && suggestionDtoList.size() > 0) {
            return suggestionDtoList.size();
        }
        return 0;
    }


    class ViewHolder extends RecyclerView.ViewHolder {
        ImageView icon;
        TextView name, price;

        ViewHolder(View view) {
            super(view);
            icon = view.findViewById(R.id.icon);
            name = view.findViewById(R.id.name);
            price = view.findViewById(R.id.priceTv);
        }
    }
}
