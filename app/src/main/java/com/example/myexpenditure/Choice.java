package com.example.myexpenditure;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

public class Choice extends AppCompatActivity {

    CardView seeExpense, seeCalendar, seeGraph, organiseExpense;
    CardView share;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choice);

        seeCalendar = findViewById(R.id.seecalendar);
        seeExpense = findViewById(R.id.seeexpenses);
        seeGraph = findViewById(R.id.seegraph);
        organiseExpense = findViewById(R.id.organise);

        share = findViewById(R.id.share);


        seeCalendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Choice.this,MainActivity.class));
            }
        });

        seeExpense.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Choice.this, ExpenseCategoryChoice.class));
            }
        });

        seeGraph.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Choice.this,Graphs.class));
            }
        });

        organiseExpense.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"Feature has not yet been developed",Toast.LENGTH_LONG).show();
            }
        });

        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "This is my expense Tracker App. Here is the github link. \n"+"https://github.com/Madhu-30/ExpenseTracker_App";

                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_TEXT, url);
                sendIntent.setType("text/plain");

                Intent shareIntent = Intent.createChooser(sendIntent, null);
                startActivity(shareIntent);
            }
        });
    }
}