package com.darcos.julie.mynews.Activities;




import android.support.v4.app.DialogFragment;
import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import com.darcos.julie.mynews.R;
import com.darcos.julie.mynews.Fragments.DatePickerFragment;

import java.util.ArrayList;
import java.util.List;

public class SearchActivity extends AppCompatActivity implements View.OnClickListener{
    private Toolbar toolbarSearch;

    private List<String> listChecked;
    private Button searchButton;
    private EditText editText;
    private static String beginDate;
    private static String endDate;
    private Button beginDateButton;
    private Button endDateButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        this.configureToolBar();

        this.listChecked=new ArrayList<>();
        for(int i=0;i<6;i++){
            this.listChecked.add(null);
        }

        this.searchButton = (Button) findViewById(R.id.search_button);
        this.editText = (EditText) findViewById(R.id.search_query_term);
        this.beginDateButton = (Button) findViewById(R.id.begin_date_button);
        this.endDateButton = (Button) findViewById(R.id.end_date_button);

        searchButton.setOnClickListener(this);


    }


    @Override
    public void onClick(View v) {
        Intent intent = new Intent(SearchActivity.this, ResultSearch.class);
        intent.putExtra("beginDate",beginDate);
        intent.putExtra("endDate",endDate);
        startActivity(intent);
    }

    public void showDatePickerDialogBegin(View v) {
        DialogFragment newFragment = new DatePickerFragment();

        newFragment.show(getSupportFragmentManager(), "begin");

    }
    public void showDatePickerDialogEnd(View v) {
        DialogFragment newFragment = new DatePickerFragment();

        newFragment.show(getSupportFragmentManager(), "end");

    }


    private void configureToolBar() {
        this.toolbarSearch = (Toolbar) findViewById(R.id.activity_search_toolbar);
        setSupportActionBar(toolbarSearch);

        final ActionBar actionBar = getSupportActionBar();
        actionBar.setHomeAsUpIndicator(R.drawable.baseline_arrow_back_white_24);
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle("Search Articles");
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        Intent intent = new Intent(SearchActivity.this, MainActivity.class);
        startActivity(intent);

        return super.onOptionsItemSelected(item);
    }


    public void onCheckboxClicked(View view){
        boolean checked = ((CheckBox)view).isChecked();

        switch (view.getId()){
            case R.id.checkbox_arts:
                if (checked)
                    this.listChecked.set(0,"arts");
                else
                    this.listChecked.set(0,null);
                break;

            case R.id.checkbox_politics:
                if (checked)
                    this.listChecked.set(1,"politics");
                else
                    this.listChecked.set(1,null);
                break;

            case R.id.checkbox_business:
                if (checked)
                    this.listChecked.set(2,"business");
                else
                    this.listChecked.set(2,null);
                break;
            case R.id.checkbox_sports:
                if (checked)
                    this.listChecked.set(3,"sports");
                else
                    this.listChecked.set(3,null);
                break;
            case R.id.checkbox_entrepreneurs:
                if (checked)
                    this.listChecked.set(4,"entrepreneurs");
                else
                    this.listChecked.set(4,null);
                break;
            case R.id.checkbox_travel:
                if (checked)
                    this.listChecked.set(5,"travel");
                else
                    this.listChecked.set(5,null);
                break;
        }

    }


    public static String getBeginDate() {
        return beginDate;
    }

    public static void setBeginDate(String date) {
        beginDate = date;
    }

    public static String getEndDate() {
        return endDate;
    }

    public static void setEndDate(String date) {
        endDate = date;
    }
}
