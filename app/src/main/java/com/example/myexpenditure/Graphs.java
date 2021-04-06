package com.example.myexpenditure;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;

import java.util.ArrayList;
import java.util.List;

public class Graphs extends AppCompatActivity {


    List<MainData> mainDataList = new ArrayList<>();
    ArrayList<PieEntry> entries = new ArrayList<>();
    ArrayList<Integer> colorslist = new ArrayList<>();

    String tag;
    int food=0, travel=0, home=0, other=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graphs);

        PieChart pieChart = findViewById(R.id.piechart);
        CardView back = findViewById(R.id.back);

//        colorslist.add(Color.RED);
//        colorslist.add(Color.CYAN);
//        colorslist.add(Color.MAGENTA);
//        colorslist.add(Color.YELLOW);

        colorslist.add(R.color.p1_blue);
        colorslist.add(R.color.p1_pink1);
        colorslist.add(Color.YELLOW);
        colorslist.add(R.color.p1_pink2);


        mainDataList = RoomDB.getInstance(this).mainDao().getAll();

        for (int i = 0; i < mainDataList.size(); i++) {
            tag = mainDataList.get(i).getTag();
            if ("Food".matches(tag)) {
                food+= Integer.parseInt(mainDataList.get(i).getAmount());
            }
            else if("Travel".matches(tag)) {
                travel+= Integer.parseInt(mainDataList.get(i).getAmount());

            }else if("Home".matches(tag)) {
                home+= Integer.parseInt(mainDataList.get(i).getAmount());
            }
            else
                other+= Integer.parseInt(mainDataList.get(i).getAmount());
        }

        entries.add(new PieEntry(food,"Food"));
        entries.add(new PieEntry(travel,"Travel"));
        entries.add(new PieEntry(home,"Home"));
        entries.add(new PieEntry(other,"Others"));

        PieDataSet pieDataSet = new PieDataSet(entries,"Entries");
        pieDataSet.setColors(colorslist);
        pieDataSet.setValueTextColor(R.color.p2_darkest);
        pieDataSet.setValueTextColor(R.color.p2_darkest);
        pieDataSet.setValueTextSize(16f);

        PieData pieData = new PieData(pieDataSet);
        pieChart.setData(pieData);
        pieChart.getDescription().setEnabled(false);
        pieChart.setCenterText("Expenses in INR");
        pieChart.animate();



        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

    }
}