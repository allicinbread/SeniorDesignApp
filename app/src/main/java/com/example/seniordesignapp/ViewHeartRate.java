package com.example.seniordesignapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;


import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ViewHeartRate extends AppCompatActivity {

    private Button backtomenu;
    ListView listView;
    FirebaseDatabase database;
    DatabaseReference ref;
    ArrayList<String> list;
    ArrayAdapter<String> adapter;
    HRByActivity hrByActivity;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_heart_rate);

        // Letting the Buttons Move Between Screens
        backtomenu = findViewById(R.id.hrbacktomenu);
        backtomenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ViewHeartRate.this, HeartRateMenu.class);
                startActivity(i);
            }
        });


        hrByActivity = new HRByActivity();
        listView = findViewById(R.id.hrlistview);


        database = FirebaseDatabase.getInstance();
        ref = database.getReference("HRByActivity");
        list = new ArrayList<>();


        adapter = new ArrayAdapter<String>(this, R.layout.hrba_list_item, R.id.hrbadateView, list);

        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    hrByActivity = ds.getValue(HRByActivity.class);


                    list.add(hrByActivity.getDate() + "   " + hrByActivity.getActivity() + "            " + hrByActivity.getHR().toString());

                }
                listView.setAdapter(adapter);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
}
