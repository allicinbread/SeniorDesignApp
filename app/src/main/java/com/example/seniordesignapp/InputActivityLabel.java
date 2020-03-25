package com.example.seniordesignapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

public class InputActivityLabel extends AppCompatActivity {

    // Initializing the Scroll Buttons
    public Button button1;
    public Button button2;
    public Button button3;
    public Button button4;
    public Button button5;
    public Button button6;
    public Button button7;
    public Button button8;
    public Button button9;
    public String text;

    public Button writelabel;
    public Button addlabel;
    public EditText enternewlabel;
    public LinearLayout linLayout;

    DatabaseReference ref;
    DatabaseReference refread;
    ViewActivityLabelClass newdatapoint;

    public View.OnClickListener listener1() {
        return new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                text = button1.getText().toString();
                TextView tv = findViewById(R.id.selectedlabelhere);
                tv.setText(text);
                System.out.println("text = " + text);
            }
        };
    }
    public View.OnClickListener listener2() {
        return new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                text = button2.getText().toString();
                TextView tv = findViewById(R.id.selectedlabelhere);
                tv.setText(text);
            }
        };
    }
    public View.OnClickListener listener3() {
        return new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                text = button3.getText().toString();
                TextView tv = findViewById(R.id.selectedlabelhere);
                tv.setText(text);
            }
        };
    }
    public View.OnClickListener listener4() {
        return new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                text = button4.getText().toString();
                TextView tv = findViewById(R.id.selectedlabelhere);
                tv.setText(text);
            }
        };
    }
    public View.OnClickListener listener5() {
        return new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                text = button5.getText().toString();
                TextView tv = findViewById(R.id.selectedlabelhere);
                tv.setText(text);
            }
        };
    }
    public View.OnClickListener listener6() {
        return new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                text = button6.getText().toString();
                TextView tv = findViewById(R.id.selectedlabelhere);
                tv.setText(text);
            }
        };
    }
    public View.OnClickListener listener7() {
        return new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                text = button7.getText().toString();
                TextView tv = findViewById(R.id.selectedlabelhere);
                tv.setText(text);
            }
        };
    }
    public View.OnClickListener listener8() {
        return new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                text = button8.getText().toString();
                TextView tv = findViewById(R.id.selectedlabelhere);
                tv.setText(text);
            }
        };
    }
    public View.OnClickListener listener9() {
        return new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                text = button9.getText().toString();
                TextView tv = findViewById(R.id.selectedlabelhere);
                tv.setText(text);
            }
        };
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_label);
        linLayout = findViewById(R.id.scrollviewlayout);
        addlabel = findViewById(R.id.addlabel);
        enternewlabel = findViewById(R.id.enternewlabel);
        writelabel = findViewById(R.id.writelabel);


        addlabel.setOnClickListener(onClick());
        TextView textView = new TextView(this);
        textView.setText("New text");


        // Setting on click listeners for the scrolling buttons
        button1 = findViewById(R.id.labelbutton1);
        button1.setOnClickListener(listener1());
        button2 = findViewById(R.id.labelbutton2);
        button2.setOnClickListener(listener2());
        button3 = findViewById(R.id.labelbutton3);
        button3.setOnClickListener(listener3());
        button4 = findViewById(R.id.labelbutton4);
        button4.setOnClickListener(listener4());
        button5 = findViewById(R.id.labelbutton5);
        button5.setOnClickListener(listener5());
        button6 = findViewById(R.id.labelbutton6);
        button6.setOnClickListener(listener6());
        button7 = findViewById(R.id.labelbutton7);
        button7.setOnClickListener(listener7());
        button8 = findViewById(R.id.labelbutton8);
        button8.setOnClickListener(listener8());
        button9 = findViewById(R.id.labelbutton9);
        button9.setOnClickListener(listener9());

        //Reading the Data when screen is open
        refread = FirebaseDatabase.getInstance().getReference().child("LabelDataRead").child("LDR01");
        refread.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String Checked = dataSnapshot.child("Check").getValue().toString();
                final Boolean Check = Boolean.parseBoolean(Checked); // getting bool check
                final String Date = dataSnapshot.child("Date").getValue().toString(); // getting date
                final String Time = dataSnapshot.child("Time").getValue().toString(); // getting time
                String Confidence = dataSnapshot.child("Confidence").getValue().toString();
                final Float Con = Float.parseFloat(Confidence); // getting confidence

                if(Con <= 7.5) {

                    //referencing the db so you can write to it
                    newdatapoint = new ViewActivityLabelClass();
                    ref = FirebaseDatabase.getInstance().getReference().child("LabelDataShow");
                    // Clicking on Label button to write to database
                    writelabel.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            newdatapoint.setCheck(Check);
                            newdatapoint.setConfidence(Con);
                            newdatapoint.setDate(Date);
                            newdatapoint.setTime(Time);
                            newdatapoint.setLabel(text);
                            ref.push().setValue(newdatapoint);
                        }
                    });
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }



    public View.OnClickListener onClick() {
        return new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                linLayout.addView(createNewTextView(enternewlabel.getText().toString()));
            }
        };
    }

    public TextView createNewTextView(String text) {
        final LinearLayout.LayoutParams lparams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.MATCH_PARENT);
        lparams.gravity = Gravity.CENTER;
        final TextView textView = new TextView(this);
        textView.setLayoutParams(lparams);
        textView.setText(text);
//        textView.setBackground(getResources().getDrawable(R.drawable.longbuttonbg));
        return textView;
    }

}
