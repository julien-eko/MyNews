package com.darcos.julie.mynews.Activities;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.darcos.julie.mynews.R;

import java.util.ArrayList;
import java.util.List;

public class NotificationsActivity extends AppCompatActivity {



    private List<String> listCheckedNotification;
    private  Toolbar toolbarNotifications;
    private EditText editText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notifications);

        this.editText = (EditText) findViewById(R.id.search_query_term_notification);

        this.listCheckedNotification=new ArrayList<>();
        for(int i=0;i<6;i++){
            this.listCheckedNotification.add(null);
        }

        this.configureToolBar();

        ToggleButton toggle = (ToggleButton) findViewById(R.id.toggle_button_notifications);
        toggle.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    // The toggle is enabled
                    Toast.makeText(NotificationsActivity.this, "ok", Toast.LENGTH_LONG).show();
                } else {
                    // The toggle is disabled
                    Toast.makeText(NotificationsActivity.this, editText.getText().toString(), Toast.LENGTH_LONG).show();
                }
            }
        });


    }


    private void configureToolBar() {
        this.toolbarNotifications = (Toolbar) findViewById(R.id.activity_notification_toolbar);
        setSupportActionBar(toolbarNotifications);

        final ActionBar actionBar = getSupportActionBar();
        actionBar.setHomeAsUpIndicator(R.drawable.baseline_arrow_back_white_24);
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle("Notifications");
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        Intent intent = new Intent(NotificationsActivity.this, MainActivity.class);
        startActivity(intent);

        return super.onOptionsItemSelected(item);
    }
    public void onCheckboxClicked(View view){
        boolean checked = ((CheckBox)view).isChecked();

        switch (view.getId()){
            case R.id.checkbox_arts:
                if (checked)
                    this.listCheckedNotification.set(0,"arts");
                else
                    this.listCheckedNotification.set(0,null);
                break;

            case R.id.checkbox_politics:
                if (checked)
                    this.listCheckedNotification.set(1,"politics");
                else
                    this.listCheckedNotification.set(1,null);
                break;

            case R.id.checkbox_business:
                if (checked)
                    this.listCheckedNotification.set(2,"business");
                else
                    this.listCheckedNotification.set(2,null);
                break;
            case R.id.checkbox_sports:
                if (checked)
                    this.listCheckedNotification.set(3,"sports");
                else
                    this.listCheckedNotification.set(3,null);
                break;
            case R.id.checkbox_entrepreneurs:
                if (checked)
                    this.listCheckedNotification.set(4,"entrepreneurs");
                else
                    this.listCheckedNotification.set(4,null);
                break;
            case R.id.checkbox_travel:
                if (checked)
                    this.listCheckedNotification.set(5,"travel");
                else
                    this.listCheckedNotification.set(5,null);
                break;
        }

    }




}
