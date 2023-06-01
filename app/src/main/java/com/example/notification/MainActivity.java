package com.example.notification;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.NotificationView;

public class MainActivity extends AppCompatActivity {
    Button button;
    private final String CHANNEL_ID = "Personal_Notification";
    private final  int NOTIFICATION_ID = 001;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String message = "This is a Notification";
                createNotificationChannel();
                NotificationCompat.Builder builder = new NotificationCompat.Builder(MainActivity.this,CHANNEL_ID);
                builder.setSmallIcon(R.drawable.ic_5g); //set icon for notification
                builder.setContentTitle("Notifications Example"); //set title of notification
                builder.setContentText(message);//this is notification message
                builder.setPriority(NotificationCompat.PRIORITY_DEFAULT);
                builder.setAutoCancel(true);

                Intent notificationIntent = new Intent(MainActivity.this, NotificationView.class);
                notificationIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                notificationIntent.putExtra("message", message);
                PendingIntent pendingIntent = PendingIntent.getActivity(MainActivity.this, 0, notificationIntent,
                        PendingIntent.FLAG_UPDATE_CURRENT);
                builder.setContentIntent(pendingIntent);
                NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(MainActivity.this);
                notificationManagerCompat.notify(NOTIFICATION_ID,builder.build());
            }
        });
    }
    public void  createNotificationChannel()
    {
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.O){
            CharSequence name = "Notification";
            String description = "Include all the personal Notifications";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;

            NotificationChannel notificationChannel = new NotificationChannel(CHANNEL_ID,name,importance);
            notificationChannel.setDescription(description);
            NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
            notificationManager.createNotificationChannel(notificationChannel);
        }
    }
}