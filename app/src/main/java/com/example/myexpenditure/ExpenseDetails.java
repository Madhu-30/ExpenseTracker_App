package com.example.myexpenditure;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class ExpenseDetails extends AppCompatActivity {

    RecyclerView recyclerView;
    String tag;
    CardView back;

    List<MainData> mainDataList = new ArrayList<>();
    List<MainData> displaylist = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expense_details);

        recyclerView = findViewById(R.id.recyclerview);
        back = findViewById(R.id.back);

        Intent intent = getIntent();
        tag = intent.getStringExtra("intent_tag");

        mainDataList = RoomDB.getInstance(this).mainDao().getAll();

        if(tag != null) {
            for (int i = 0; i < mainDataList.size(); i++) {
                if (mainDataList.get(i).getTag().matches(tag)) {
                    displaylist.add(mainDataList.get(i));
                }
            }
        }

        buildRecyclerView();

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    private void buildRecyclerView() {

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(ExpenseDetails.this);
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);

        MyAdapter adapter = new MyAdapter(getApplicationContext(),displaylist,"tagdetails");
        recyclerView.setAdapter(adapter);

    }
}