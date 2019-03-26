package com.darcos.julie.mynews.Activities;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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

import com.darcos.julie.mynews.Models.Search.Search;
import com.darcos.julie.mynews.R;
import com.darcos.julie.mynews.Utils.MyAlarmReceiver;
import com.darcos.julie.mynews.Utils.TimesStreams;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableObserver;

public class NotificationsActivity extends AppCompatActivity implements CompoundButton.OnCheckedChangeListener {


    private List<String> listCheckedNotification;
    // 1 - Creating an intent to execute our broadcast
    private PendingIntent pendingIntent;
    private String button;
    @BindView(R.id.checkbox_arts) CheckBox checkBoxArts;
    @BindView(R.id.checkbox_politics) CheckBox checkBoxPolitics;
    @BindView(R.id.checkbox_business) CheckBox checkBoxBusiness;
    @BindView(R.id.checkbox_sports) CheckBox checkBoxSports;
    @BindView(R.id.checkbox_entrepreneurs) CheckBox checkBoxEntrepreneurs;
    @BindView(R.id.checkbox_travel) CheckBox checkBoxTravels;
    @BindView(R.id.toggle_button_notifications) ToggleButton toggle;
    @BindView(R.id.activity_notification_toolbar) Toolbar toolbarNotifications;
    @BindView(R.id.search_query_term_notification) EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notifications);
        ButterKnife.bind(this);

        this.configureToolBar();
        this.configureCheckbox();
        this.configureAlarmManager();
        this.editText.setText(getPreferences(MODE_PRIVATE).getString("edit",null));



        this.toggle.setOnCheckedChangeListener(this);

        this.button = getPreferences(MODE_PRIVATE).getString("toggle",null);


        if(this.button.equals("checked")){
            toggle.setChecked(true);
        }else{
            toggle.setChecked(false);
        }




    }

    @Override
    public void onStop() {
        super.onStop();
        SharedPreferences preferences = getPreferences(MODE_PRIVATE);
        preferences.edit().putString("toggle", this.button).apply();
        preferences.edit().putString("edit", this.editText.getText().toString()).apply();
        for(int i=0;i<6;i++) {
            preferences.edit().putString("listCheckbox" + i, this.listCheckedNotification.get(i)).apply();
        }
    }



    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if (isChecked) {
            startAlarm();
            this.button="checked";
            // The toggle is enabled
            //Toast.makeText(NotificationsActivity.this, "ok", Toast.LENGTH_LONG).show();
        } else {
            stopAlarm();
            this.button="";
            // The toggle is disabled
            // Toast.makeText(NotificationsActivity.this, editText.getText().toString(), Toast.LENGTH_LONG).show();
        }
    }
    // 2 - Configuring the AlarmManager
    private void configureAlarmManager() {
        Intent alarmIntent = new Intent(NotificationsActivity.this, MyAlarmReceiver.class);
            alarmIntent.putExtra("queryShearch",editText.getText().toString());
            alarmIntent.putExtra("newsDesk",newsDesk());

        pendingIntent = PendingIntent.getBroadcast(NotificationsActivity.this, 0, alarmIntent, PendingIntent.FLAG_UPDATE_CURRENT);
    }
    // ---------------------------------------------
    // SCHEDULE TASK (AlarmManager & JobScheduler)
    // ---------------------------------------------

    // 3 - Start Alarm
    private void startAlarm() {
        AlarmManager manager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
       manager.setRepeating(AlarmManager.RTC_WAKEUP,times(19,00),AlarmManager.INTERVAL_DAY, pendingIntent);
        Toast.makeText(this, "Alarm set !", Toast.LENGTH_SHORT).show();

    }

    // 4 - Stop Alarm
    private void stopAlarm() {
        AlarmManager manager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        manager.cancel(pendingIntent);
        Toast.makeText(this, "Alarm Canceled !", Toast.LENGTH_SHORT).show();
    }


    private void configureToolBar() {
        setSupportActionBar(toolbarNotifications);

        final ActionBar actionBar = getSupportActionBar();
        actionBar.setHomeAsUpIndicator(R.drawable.baseline_arrow_back_white_24);
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle("Notifications");
    }

    private void configureCheckbox(){
        this.listCheckedNotification = new ArrayList<>();
        for(int i=0;i<6;i++) {
            this.listCheckedNotification.add(getPreferences(MODE_PRIVATE).getString("listCheckbox" + i, null));
        }


        if(this.listCheckedNotification.get(0) != null){
            this.checkBoxArts.setChecked(true);
        }
        if(this.listCheckedNotification.get(1) != null){
            this.checkBoxPolitics.setChecked(true);
        }
        if(this.listCheckedNotification.get(2) != null){
            this.checkBoxBusiness.setChecked(true);
        }
        if(this.listCheckedNotification.get(3) != null){
            this.checkBoxSports.setChecked(true);
        }
        if(this.listCheckedNotification.get(4) != null){
            this.checkBoxEntrepreneurs.setChecked(true);
        }
        if(this.listCheckedNotification.get(5) != null){
            this.checkBoxTravels.setChecked(true);
        }
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        Intent intent = new Intent(NotificationsActivity.this, MainActivity.class);
        startActivity(intent);

        return super.onOptionsItemSelected(item);
    }

    public void onCheckboxClicked(View view) {
        boolean checked = ((CheckBox) view).isChecked();

        switch (view.getId()) {
            case R.id.checkbox_arts:
                if (checked)
                    this.listCheckedNotification.set(0, "arts");
                else
                    this.listCheckedNotification.set(0, null);
                break;

            case R.id.checkbox_politics:
                if (checked)
                    this.listCheckedNotification.set(1, "politics");
                else
                    this.listCheckedNotification.set(1, null);
                break;

            case R.id.checkbox_business:
                if (checked)
                    this.listCheckedNotification.set(2, "business");
                else
                    this.listCheckedNotification.set(2, null);
                break;
            case R.id.checkbox_sports:
                if (checked)
                    this.listCheckedNotification.set(3, "sports");
                else
                    this.listCheckedNotification.set(3, null);
                break;
            case R.id.checkbox_entrepreneurs:
                if (checked)
                    this.listCheckedNotification.set(4, "entrepreneurs");
                else
                    this.listCheckedNotification.set(4, null);
                break;
            case R.id.checkbox_travel:
                if (checked)
                    this.listCheckedNotification.set(5, "travel");
                else
                    this.listCheckedNotification.set(5, null);
                break;
        }
        this.configureAlarmManager();
    }

    private long times(int hours, int minute) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, hours);
        calendar.set(Calendar.MINUTE, minute);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);

        return calendar.getTimeInMillis();
    }


    public String newsDesk() {
        String q;
        q = "news_desk:(";

        for (int i = 0; i < this.listCheckedNotification.size(); i++) {
            if (this.listCheckedNotification.get(i) == null) {

            } else {
                q = q + "\"" + this.listCheckedNotification.get(i) + "\" ";
            }
        }

        q=q + ")";
        return q;
    }


}
