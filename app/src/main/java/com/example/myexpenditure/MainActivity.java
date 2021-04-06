package com.example.myexpenditure;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    CalendarView calendarView;
    Button add;
    String sdate="";
    RecyclerView recyclerView;

    MainDao mainDao;
    RoomDB mainDatabase;
    List<MainData> mainDataList = new ArrayList<>();
    List<MainData> displaylist;

    CardView back;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        add = findViewById(R.id.button_add);
        calendarView = findViewById(R.id.calendar);
        recyclerView = findViewById(R.id.recyclerview);

        back = findViewById(R.id.back);

        mainDatabase = RoomDB.getInstance(this);
        mainDataList = mainDatabase.mainDao().getAll();

        Calendar today = Calendar.getInstance();
        long now = today.getTimeInMillis();
        calendarView.setMaxDate(now);

        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {

                if(month > 10) {
                    if(dayOfMonth >= 10)
                        sdate = dayOfMonth + "." + (month + 1) + "." + year;
                    else
                        sdate = "0"+dayOfMonth + "." + (month + 1) + "." + year;

                }
                else {
                    if(dayOfMonth >= 10)
                        sdate = dayOfMonth + "." + "0" + (month + 1) + "." + year;
                    else
                        sdate = "0"+dayOfMonth + "." + "0" + (month + 1) + "." + year;
                }

                displaylist = new ArrayList<>();
                buildRecyclerView();

            }
        });

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(sdate.isEmpty()) {
                    String date = new SimpleDateFormat("dd-MM-yyyy").format(new Date());
                    sdate = date.replace('-','.');
                }

                Intent intent = new Intent(MainActivity.this, AddExpenditure.class);
                intent.putExtra("date", sdate);
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

    private void buildRecyclerView() {

        for (int i =0;i<mainDataList.size();i++){
            if(mainDataList.get(i).getDate().matches(sdate)){
                displaylist.add(mainDataList.get(i));
            }
        }

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(MainActivity.this);
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);

        MyAdapter adapter = new MyAdapter(getApplicationContext(),displaylist,"main");
        recyclerView.setAdapter(adapter);

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        startActivity(new Intent(MainActivity.this,Choice.class));
    }
}