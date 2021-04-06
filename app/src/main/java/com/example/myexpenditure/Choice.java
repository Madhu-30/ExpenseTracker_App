package com.example.myexpenditure;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

public class Choice extends AppCompatActivity {

    LinearLayout seeExpense, seeCalendar, seeGraph;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choice);

        seeCalendar = findViewById(R.id.seecalendar);
        seeExpense = findViewById(R.id.seeexpenses);
        seeGraph = findViewById(R.id.seegraph);

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

    }
}