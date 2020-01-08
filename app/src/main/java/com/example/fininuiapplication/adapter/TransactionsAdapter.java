package com.example.fininuiapplication.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fininuiapplication.R;
import com.example.fininuiapplication.model.TransactionsDto;

import java.util.List;

public class TransactionsAdapter extends RecyclerView.Adapter<TransactionsAdapter.ViewHolder> {

    private static final String SWIGGY = "SWIGGY";
    private static final String CULT_FIT = "Cult Fit";
    private static final String STARBUCKS = "STARBUCKS";

    private List<TransactionsDto> transactionsDtoList;
    Context context;


    public TransactionsAdapter(Context context, List<TransactionsDto> transactionsDtoList) {
        this.context = context;
        this.transactionsDtoList = transactionsDtoList;
    }

    @Override
    @NonNull
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View groceryProductView = LayoutInflater.from(parent.getContext()).inflate(R.layout.transaction_list_item, parent, false);
        return new ViewHolder(groceryProductView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        TransactionsDto transactionsDto = transactionsDtoList.get(position);

        if (transactionsDto != null) {

            String name = transactionsDto.getName();
            holder.name.setText(name);

            holder.icon.setImageDrawable(getImageAccordingToName(name));
            holder.category.setText(transactionsDto.getType());
            holder.time.setText(transactionsDto.getDateTime());
            String priceStr = transactionsDto.getPrice() + "";
            holder.price.setText(priceStr);
        }
    }

    private Drawable getImageAccordingToName(String name) {
        if (name.equalsIgnoreCase(SWIGGY)) {
            return ContextCompat.getDrawable(context, R.drawable.swiggy_icon);
        } else if (name.equalsIgnoreCase(CULT_FIT)) {
            return ContextCompat.getDrawable(context, R.drawable.curl_fit_icon);
        } else if (name.equalsIgnoreCase(STARBUCKS)) {
            return ContextCompat.getDrawable(context, R.drawable.starbucks_logo);
        }
        return null;
    }

    @Override
    public int getItemCount() {
        if (transactionsDtoList != null && transactionsDtoList.size() > 0) {
            return transactionsDtoList.size();
        }
        return 0;
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView icon;
        TextView name, category, time, price;

        public ViewHolder(View view) {
            super(view);
            icon = view.findViewById(R.id.icon);
            name = view.findViewById(R.id.name);
            category = view.findViewById(R.id.category);
            time = view.findViewById(R.id.time);
            price = view.findViewById(R.id.priceTv);
        }
    }
}
