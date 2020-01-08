package com.example.fininuiapplication.activity;

import android.graphics.PorterDuff;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.Html;
import android.text.Spannable;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Spinner;
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
import com.highsoft.highcharts.common.hichartsclasses.HIChart;
import com.highsoft.highcharts.common.hichartsclasses.HIColumn;
import com.highsoft.highcharts.common.hichartsclasses.HICredits;
import com.highsoft.highcharts.common.hichartsclasses.HIExporting;
import com.highsoft.highcharts.common.hichartsclasses.HIOptions;
import com.highsoft.highcharts.common.hichartsclasses.HIPlotOptions;
import com.highsoft.highcharts.common.hichartsclasses.HISeries;
import com.highsoft.highcharts.common.hichartsclasses.HISubtitle;
import com.highsoft.highcharts.common.hichartsclasses.HITitle;
import com.highsoft.highcharts.common.hichartsclasses.HIXAxis;
import com.highsoft.highcharts.common.hichartsclasses.HIYAxis;
import com.highsoft.highcharts.core.HIChartView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    ProgressBar progressbar;
    private RecyclerView transactionsRecyclerView, suggestionRecyclerView;
    private TextView startPrice, spendPrice, endPrice;
    HIChartView chartView;

    private Spinner mSpinnerType, mSpinnerMonth;
    private String[] mTypeArray, mMonthArray;
    private ArrayAdapter<String> mTypeAdapter, mMonthAdapter;
    private TextView budgetTitle,monthTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        transactionsRecyclerView = findViewById(R.id.transactionsRecyclerViewLayout).findViewById(R.id.recyclerView);
        suggestionRecyclerView = findViewById(R.id.suggestionRecyclerViewLayout).findViewById(R.id.recyclerView);

        chartView = findViewById(R.id.hc);

        startPrice = findViewById(R.id.start_price_layout).findViewById(R.id.price);
        startPrice.setText("4,300");
        startPrice.setTypeface(Typeface.DEFAULT_BOLD);
        spendPrice = findViewById(R.id.spend_price_layout).findViewById(R.id.price);
        spendPrice.setText("150/day");
        endPrice = findViewById(R.id.end_price_layout).findViewById(R.id.price);
        endPrice.setText("10,000");
        endPrice.setTypeface(Typeface.DEFAULT_BOLD);

        setTransactionsList();
        setSuggestionList();
        setChartView();


        budgetTitle = findViewById(R.id.purpose_budget_layout).findViewById(R.id.title);
        budgetTitle.setText(getString(R.string.purpose_of_budget));

        monthTitle = findViewById(R.id.month_layout).findViewById(R.id.title);
        monthTitle.setText(getString(R.string.month));

        mSpinnerType = findViewById(R.id.purpose_budget_layout).findViewById(R.id.spinner_type);
        mSpinnerMonth = findViewById(R.id.month_layout).findViewById(R.id.spinner_type);

        setSpinnerAdapters();



    }

    private void setSpinnerAdapters() {
        mTypeArray = getResources().getStringArray(R.array.type_array);
        mMonthArray = getResources().getStringArray(R.array.month_array);

        mTypeAdapter = new ArrayAdapter<String>(this, R.layout.spineer_item, mTypeArray);
        setAdapterOptions(mTypeAdapter, mSpinnerType);


        mMonthAdapter = new ArrayAdapter<String>(this, R.layout.spineer_item, mMonthArray);
        setAdapterOptions(mMonthAdapter, mSpinnerMonth);
    }

    private void setAdapterOptions(ArrayAdapter<String> adapter, Spinner spinner){
        if(adapter != null && spinner != null) {
            adapter.setDropDownViewResource(R.layout.spineer_item);
            spinner.setAdapter(adapter);
        }
    }

    private void setChartView() {
        HIOptions options = new HIOptions();

        HIChart chart = new HIChart();
        chart.setType("column");
        options.setChart(chart);

        HITitle title = new HITitle();
        title.setText("");
        options.setTitle(title);

        HISubtitle subtitle = new HISubtitle();
        subtitle.setText("");
        options.setSubtitle(subtitle);

        HIExporting hiExporting = new HIExporting();
        hiExporting.setEnabled(false);
        options.setExporting(hiExporting);

        HICredits hiCredits = new HICredits();
        hiCredits.setEnabled(false);
        options.setCredits(hiCredits);

        ArrayList<String> hiColors = new ArrayList<>();
        hiColors.add("#DC555B");
        hiColors.add("#FEBD01");
        hiColors.add("#E9731D");
        hiColors.add("#3FC6F3");
        hiColors.add("#465866");
        hiColors.add("#12D59C");

        options.setColors(hiColors);



        HIXAxis xaxis = new HIXAxis();
        xaxis.setCategories(new ArrayList<>(Arrays.asList("1", "2", "3", "4", "5", "6", "7")));
        options.setXAxis(new ArrayList<>(Collections.singletonList(xaxis)));

        HIYAxis yAxis = new HIYAxis();
        yAxis.setTitle(title);
        yAxis.setShowFirstLabel(false);
        yAxis.setShowLastLabel(false);
        yAxis.setVisible(false);
        options.setYAxis(new ArrayList<>(Collections.singletonList(yAxis)));



        Number[] columnData = new Number[] { 5, 1, 2, 5, 1, 5, 1 };

        HISeries hiSeries = new HISeries();
        hiSeries.setData(new ArrayList<>(Arrays.asList(columnData)));
        options.setSeries(new ArrayList<>(Arrays.asList(hiSeries)));


        HIPlotOptions hiPlotOptions = new HIPlotOptions();
        HIColumn hiColumn = new HIColumn();
        hiColumn.setColors(hiColors);
        hiColumn.setAllowPointSelect(false);

        hiPlotOptions.setColumn(hiColumn);
        options.setPlotOptions(hiPlotOptions);

        hiSeries.setName("");
        hiSeries.setShowInLegend(false);
        hiSeries.setAllowPointSelect(false);




        chartView.setOptions(options);
        chartView.invalidate();
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
