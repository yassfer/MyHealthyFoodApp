package tn.esprit.myhealthyfoodapp.controller;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.Calendar;

import tn.esprit.myhealthyfoodapp.R;

public class ExtraActivity extends AppCompatActivity {

    private BottomNavigationView bottomNav;
    Button btNotificationOn;
    Button btNotificationOff;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_extra);

        bottomNav = findViewById(R.id.navigation);
        btNotificationOn = findViewById(R.id.bt_notification_on);
        btNotificationOff = findViewById(R.id.bt_notification_off);

        createNofificationChannel();

        btNotificationOn.setOnClickListener(view -> {
            Calendar calendar = Calendar.getInstance();
            Toast.makeText(this, "Reminder set!", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(ExtraActivity.this, ReminderBroadcast.class);
            PendingIntent pendingIntent = PendingIntent.getBroadcast(ExtraActivity.this, 0, intent, 0);
            AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
            alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), 1000 * 40, pendingIntent);

        });

        btNotificationOff.setOnClickListener(view -> {
            Toast.makeText(this, "Reminder off!", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(ExtraActivity.this, ReminderBroadcast.class);
            PendingIntent pendingIntent = PendingIntent.getBroadcast(ExtraActivity.this, 0, intent, 0);
            AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
            if(alarmManager != null)
                alarmManager.cancel(pendingIntent);
        });

        this.configureBottomView();
    }

    private void createNofificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "waterReminderChannel";
            String description = "Channel for Water Reminder";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;

            NotificationChannel channel = new NotificationChannel("water_notification", name, importance);
            channel.setDescription(description);

            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    private void configureBottomView() {
        bottomNav.setOnNavigationItemSelectedListener(item -> updateActivity(item.getItemId()));
    }

    private Boolean updateActivity(Integer integer) {
        Activity activity = null;
        switch (integer) {
            case R.id.navigation_home:
                activity = new HomeActivity();
                break;

            case R.id.navigation_profile:
                activity = new ProfilActivity();
                break;
            case R.id.navigation_notifications:
                activity = new ExtraActivity();
                break;
        }
        startActivity(new Intent(this, activity.getClass()));
        return true;
    }
}