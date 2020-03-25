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

public class ViewActivityLabel extends AppCompatActivity {

    private Button backtomain;
    ListView listView;
    FirebaseDatabase database;
    DatabaseReference ref;
    ArrayList<String> list;
    ArrayAdapter<String> adapter;
    ViewActivityLabelClass viewActivityLabelClass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_label);

        // Letting the Buttons Move Between Screens
        backtomain = findViewById(R.id.backtomain);
        backtomain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ViewActivityLabel.this, MainActivity.class);
                startActivity(i);
            }
        });

        viewActivityLabelClass = new ViewActivityLabelClass();
        listView = findViewById(R.id.activitylabelslistview);


        database = FirebaseDatabase.getInstance();
        ref = database.getReference("LabelDataShow");
        list = new ArrayList<>();


        adapter = new ArrayAdapter<String>(this, R.layout.hrba_list_item, R.id.hrbadateView, list);

        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    viewActivityLabelClass = ds.getValue(ViewActivityLabelClass.class);


                    list.add(viewActivityLabelClass.getDate() + "    " + viewActivityLabelClass.getTime() + "   " + viewActivityLabelClass.getLabel());

                }
                listView.setAdapter(adapter);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
}
