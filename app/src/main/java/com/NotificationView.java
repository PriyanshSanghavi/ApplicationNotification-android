package com;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.example.notification.R;

public class NotificationView extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification_view);
        TextView textView = findViewById(R.id.textView2);
        String message = getIntent().getStringExtra("message");
        textView.setText(message);
    }
}