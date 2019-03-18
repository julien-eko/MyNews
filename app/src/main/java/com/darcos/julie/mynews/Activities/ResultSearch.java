package com.darcos.julie.mynews.Activities;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.darcos.julie.mynews.Fragments.SearchFragment;
import com.darcos.julie.mynews.R;

public class ResultSearch extends AppCompatActivity implements SearchFragment.test {

    private Toolbar toolbarSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_search);
        this.configureToolBar();

    }

    private void configureToolBar() {
      this.toolbarSearch = (Toolbar) findViewById(R.id.activity_search_result_toolbar);
        setSupportActionBar(toolbarSearch);

        final ActionBar actionBar = getSupportActionBar();
        actionBar.setHomeAsUpIndicator(R.drawable.baseline_arrow_back_white_24);
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle("Articles");
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        Intent intent = new Intent(ResultSearch.this, SearchActivity.class);
        startActivity(intent);

        return super.onOptionsItemSelected(item);
    }

    public static String converDate (String date){
        String year="";
        String month="";
        String day="";
        int i=0;
        while(!date.substring(i,i+1).equals("/")){
            month=month + date.substring(i,i+1);
            i=i+1;
        }
        i=i+1;
        System.out.println(i);
        while(!date.substring(i,i+1).equals("/")){
            day=day + date.substring(i,i+1);
            i=i+1;
        }
        i=i+1;
        for(int j=0;j<4;j++){
            year=year + date.substring(i,i+1);
            i=i+1;
        }

        if(day.length()==1) {
            day = "0" + day;
        }
        if(month.length()==1) {
            month = "0" + month;
        }
        date = year + month + day;

        return date;
    }


    public String beginDate(){
        String begin=getIntent().getStringExtra("beginDate");
        begin=converDate(begin);
        return begin;
    }

    public String endDate(){
        String end=getIntent().getStringExtra("endDate");
        end=converDate(end);
        return end;
    }

    public String querySearch(){
        String query="sports";
        return query;
    }
}
