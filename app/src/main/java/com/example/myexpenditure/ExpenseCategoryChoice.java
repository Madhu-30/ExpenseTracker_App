package com.example.myexpenditure;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

public class ExpenseCategoryChoice extends AppCompatActivity {

    LinearLayout food, travel, home, others;

    CardView back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_see_expense);

        food = findViewById(R.id.food);
        travel = findViewById(R.id.travel);
        home = findViewById(R.id.home);
        others = findViewById(R.id.other);

        back = findViewById(R.id.back);

        food.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ExpenseCategoryChoice.this,ExpenseDetails.class);
                intent.putExtra("intent_tag","Food");
                startActivity(intent);
            }
        });

        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ExpenseCategoryChoice.this,ExpenseDetails.class);
                intent.putExtra("intent_tag","Home");
                startActivity(intent);
            }
        });

        travel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ExpenseCategoryChoice.this,ExpenseDetails.class);
                intent.putExtra("intent_tag","Travel");
                startActivity(intent);
            }
        });

        others.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ExpenseCategoryChoice.this,ExpenseDetails.class);
                intent.putExtra("intent_tag","Other");
                startActivity(intent);
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

    }
}