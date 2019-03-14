package com.darcos.julie.mynews.Activities;

import android.content.Intent;
import android.support.constraint.solver.widgets.ChainHead;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

import com.darcos.julie.mynews.R;

import java.util.List;

public class SearchActivity extends AppCompatActivity {
    private Toolbar toolbarSearch;

    private List<String> listChecked;
    private Button searchButton;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        this.configureToolBar();

        this.searchButton = (Button) findViewById(R.id.search_button);
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              
            }
        });


    }




    private void configureToolBar() {
        this.toolbarSearch = (Toolbar) findViewById(R.id.activity_webview);
        setSupportActionBar(toolbarSearch);

        final ActionBar actionBar = getSupportActionBar();
        actionBar.setHomeAsUpIndicator(R.drawable.baseline_arrow_back_white_24);
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle(getIntent().getStringExtra("title"));
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



}
