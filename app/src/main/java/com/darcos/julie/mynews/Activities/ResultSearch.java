package com.darcos.julie.mynews.Activities;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.Toast;

import com.darcos.julie.mynews.Fragments.SearchFragment;
import com.darcos.julie.mynews.R;

import java.time.Year;
import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ResultSearch extends AppCompatActivity implements SearchFragment.callback {

    @BindView(R.id.activity_search_result_toolbar) Toolbar toolbarSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_search);
        ButterKnife.bind(this);

        this.configureToolBar();

    }

    //Configure toolbar
    private void configureToolBar() {
        setSupportActionBar(toolbarSearch);

        final ActionBar actionBar = getSupportActionBar();
        actionBar.setHomeAsUpIndicator(R.drawable.baseline_arrow_back_white_24);
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle("Articles");
    }

    //button return in toolbar
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        Intent intent = new Intent(ResultSearch.this, SearchActivity.class);
        startActivity(intent);

        return super.onOptionsItemSelected(item);
    }

    /**
     * formatin date
     * @param date dd/mm/yyyy
     * @return yyyymmdd
     */
    public static String converDate (String date){
        String year="";
        String month="";
        String day="";
        int i=0;

        System.out.println(i);
        while(!date.substring(i,i+1).equals("/")){
            day=day + date.substring(i,i+1);
            i=i+1;
        }
        i=i+1;
        while(!date.substring(i,i+1).equals("/")){
            month=month + date.substring(i,i+1);
            i=i+1;
        }

        i=i+1;
        for(int j=0;j<4;j++){
            year=year + date.substring(i,i+1);
            i=i+1;
        }


        date = year + month + day;

        return date;
    }

    /**
     * begin date choose by the user if no choice return 1 janury 2019
     * @return begin date
     */
    public String beginDate(){
        String begin=getIntent().getStringExtra("beginDate");
        if(begin == null){
            begin="20190101";
        }else{
            begin=converDate(begin);
        }
        return begin;
    }

    /**
     *
     * @return end date chose by the user
     * if no choice return date of the day
     */
    public String endDate(){
        String end=getIntent().getStringExtra("endDate");
        if(end == null){
            final Calendar c = Calendar.getInstance();
            int yearI = c.get(Calendar.YEAR);
            int monthI = c.get(Calendar.MONTH);
            int dayI = c.get(Calendar.DAY_OF_MONTH);
            String day = Integer.toString(dayI);
            String month = Integer.toString(monthI+1);
            String year = Integer.toString(yearI);


            if(day.length()==1) {
                day = "0" + day;
            }
            if(month.length()==1) {
                month = "0" + month;
            }
            end = year + month + day;

        }else{
            end=converDate(end);
        }

        return end;
    }

    /**
     *
     * @return query
     */
    public String querySearch(){
        String query=getIntent().getStringExtra("query");
        return query;
    }

    /**
     *
     * @return list of newsDesk checked by user in the format String
     */
    public String newsDesk(){
        String newsDesk = getIntent().getStringExtra("newsDesk");
        return newsDesk;
    }
}
