package com.example.fininuiapplication.activity;

import android.graphics.PorterDuff;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.Html;
import android.text.Spannable;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fininuiapplication.R;
import com.example.fininuiapplication.adapter.SuggestionAdapter;
import com.example.fininuiapplication.adapter.TransactionsAdapter;
import com.example.fininuiapplication.model.SuggestionDto;
import com.example.fininuiapplication.model.TransactionsDto;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    ProgressBar progressbar;
    private RecyclerView transactionsRecyclerView, suggestionRecyclerView;
    private TextView startPrice, spendPrice, endPrice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        transactionsRecyclerView = findViewById(R.id.transactionsRecyclerViewLayout).findViewById(R.id.recyclerView);
        suggestionRecyclerView = findViewById(R.id.suggestionRecyclerViewLayout).findViewById(R.id.recyclerView);

        startPrice = findViewById(R.id.start_price_layout).findViewById(R.id.price);
        startPrice.setText("4,300");
        startPrice.setTypeface(Typeface.DEFAULT_BOLD);
        spendPrice = findViewById(R.id.spend_price_layout).findViewById(R.id.price);
        spendPrice.setText("150/day");
        endPrice = findViewById(R.id.end_price_layout).findViewById(R.id.price);
        endPrice.setText("10,000");
        endPrice.setTypeface(Typeface.DEFAULT_BOLD);

        progressbar = findViewById(R.id.progressBar);
        progressbar.setProgress(50);

        setTransactionsList();
        setSuggestionList();
    }

    private void setSuggestionList() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(this,
                RecyclerView.VERTICAL, false);
        suggestionRecyclerView.setLayoutManager(layoutManager);

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this,
                layoutManager.getOrientation());
        suggestionRecyclerView.addItemDecoration(dividerItemDecoration);

        SuggestionAdapter suggestionAdapter = new SuggestionAdapter(this, getSuggestionList());
        suggestionRecyclerView.setAdapter(suggestionAdapter);
    }

    private List<SuggestionDto> getSuggestionList() {
        List<SuggestionDto> suggestionDtoList = new ArrayList<>();
        SuggestionDto suggestionDto;

        suggestionDto = new SuggestionDto("Food & Drink", 10000d);
        suggestionDtoList.add(suggestionDto);

        suggestionDto = new SuggestionDto("Housing", 10000d);
        suggestionDtoList.add(suggestionDto);

        suggestionDto = new SuggestionDto("Transportation", 10000d);
        suggestionDtoList.add(suggestionDto);

        return suggestionDtoList;
    }

    private void setTransactionsList() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(this,
                RecyclerView.VERTICAL, false);
        transactionsRecyclerView.setLayoutManager(layoutManager);

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this,
                layoutManager.getOrientation());
        transactionsRecyclerView.addItemDecoration(dividerItemDecoration);

        TransactionsAdapter transactionsAdapter = new TransactionsAdapter(this, getTransactionsList());

        transactionsRecyclerView.setAdapter(transactionsAdapter);
    }

    private List<TransactionsDto> getTransactionsList() {
        List<TransactionsDto> transactionsDtoList = new ArrayList<>();
        TransactionsDto transactionsDto;

        transactionsDto = new TransactionsDto("Swiggy", 999.90d, "Food & Drink",
                "Today 11:34 AM");
        transactionsDtoList.add(transactionsDto);

        transactionsDto = new TransactionsDto("Cult Fit", 999.90d, "Food & Drink",
                "12/11/2019 12:12PM");
        transactionsDtoList.add(transactionsDto);

        transactionsDto = new TransactionsDto("Starbucks", 999.90d, "Food & Drink",
                "12/11/2019 12:12PM");

        transactionsDtoList.add(transactionsDto);

        return transactionsDtoList;
    }
}
