package com.example.seniordesignapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    private Button inputactivitylabel;
    private Button viewactivitylabel;
    private Button viewbodytemp;
    private Button viewheartrate;
    DatabaseReference ref;
    DatabaseReference refwrite;
    ViewActivityLabelClass newdatapoint;

    // Notification Instances
    private NotificationCompat.Builder notification_builder;
    private NotificationManagerCompat notification_manager;
    private Notification notification;
    private NotificationManager manager;

    // creating the notification
    private void setupNotificiationManager() {
        NotificationManager notification_manager = (NotificationManager) this
                .getSystemService(this.NOTIFICATION_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            String channel_id = "3000";
            CharSequence name = "Channel Name";
            String description = "Channel Description";
            int importance = NotificationManager.IMPORTANCE_LOW;
            NotificationChannel mChannel = new NotificationChannel(channel_id, name, importance);
            mChannel.setDescription(description);
            mChannel.enableLights(true);
            mChannel.setLightColor(Color.BLUE);
            notification_manager.createNotificationChannel(mChannel);
            notification_builder = new NotificationCompat.Builder(this, channel_id);
        } else {
            notification_builder = new NotificationCompat.Builder(this); // this handles older APIs
        }

        //Build a notification with the same
        notification_builder.setSmallIcon(R.drawable.ic_sms_notification)
                .setContentTitle("Manual Activity Label Requested")
                .setContentText("Please go and label your activity...")
                .setAutoCancel(true);

        notification = notification_builder.build();
        manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
    }

    void printNotification(){ manager.notify(1,notification);}


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Letting the Buttons Move Between Screens
        inputactivitylabel = findViewById(R.id.input_activity_label);
        inputactivitylabel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, InputActivityLabel.class);
                startActivity(i);
            }
        });

        viewactivitylabel = findViewById(R.id.view_activity_label);
        viewactivitylabel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, ViewActivityLabel.class);
                startActivity(i);
            }
        });

        viewbodytemp = findViewById(R.id.body_temp);
        viewbodytemp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, ViewBodyTemp.class);
                startActivity(i);
            }
        });

        viewheartrate = findViewById(R.id.heart_rate);
        viewheartrate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, HeartRateMenu.class);
                startActivity(i);
            }
        });

        //Reading the Data when screen is open
        ref = FirebaseDatabase.getInstance().getReference().child("LabelDataRead").child("LDR01");
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String Checked = dataSnapshot.child("Check").getValue().toString();
                Boolean Check = Boolean.parseBoolean(Checked); // getting bool check
                String Date = dataSnapshot.child("Date").getValue().toString(); // getting date
                String Time = dataSnapshot.child("Time").getValue().toString(); // getting time
                String Label = dataSnapshot.child("Label").getValue().toString(); // getting label
                String Confidence = dataSnapshot.child("Confidence").getValue().toString();
                Float Con = Float.parseFloat(Confidence); // getting confidence

                setupNotificiationManager();
                if(Con <= 7.5 && Check == false) {
                    printNotification();
                        Intent intent =  new Intent(MainActivity.this,InputActivityLabel.class);
                        startActivity(intent);
                }

                if(Con > 7.5 && Check == false) {
                    // write to DBShow
                    //referencing the db so you can write to it
                    newdatapoint = new ViewActivityLabelClass();
                    ref = FirebaseDatabase.getInstance().getReference().child("LabelDataShow");
                    newdatapoint.setCheck(Check);
                    newdatapoint.setConfidence(Con);
                    newdatapoint.setDate(Date);
                    newdatapoint.setTime(Time);
                    newdatapoint.setLabel(Label);
                    ref.push().setValue(newdatapoint);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
