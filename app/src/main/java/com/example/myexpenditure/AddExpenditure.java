package com.example.myexpenditure;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.material.chip.ChipGroup;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class AddExpenditure extends AppCompatActivity {

    TextView tdate;
    ExtendedFloatingActionButton donebtn;
    TextView addbtn;
    EditText etamount, etreason;

    CardView back;

    RoomDB mainDatabase;
    List<MainData> mainDataList = new ArrayList<>();
    List<MainData> displaylist = new ArrayList<>();

    String date, tag;
//    LinearLayout layout_none;
    ChipGroup chipGroup;
    TextView textViewDetails;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_expenditure);

        tdate = findViewById(R.id.date);
        addbtn = findViewById(R.id.button_add);
        etamount = findViewById(R.id.amount);
        etreason = findViewById(R.id.reason);

        back = findViewById(R.id.back);

//        layout_none = findViewById(R.id.none);
        donebtn = findViewById(R.id.button_done);
        chipGroup = findViewById(R.id.chip_group);
        textViewDetails = findViewById(R.id.details);

        Intent intent = getIntent();
        date = intent.getStringExtra("date");

        mainDatabase = RoomDB.getInstance(this);
        mainDataList = mainDatabase.mainDao().getAll();

        tdate.setText("Date : "+date);

        mainDataList = mainDatabase.mainDao().getAll();

        for (int i =0;i<mainDataList.size();i++){
            if(mainDataList.get(i).getDate().matches(date)){
                displaylist.add(mainDataList.get(i));
            }
        }

        buildRecyclerView();

        addbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String samount = etamount.getText().toString().trim();
                String sreason = etreason.getText().toString().trim();

                if(!samount.isEmpty()&& !sreason.isEmpty()){

                    MainData mainData = new MainData();

                        mainData.setDate(date);
                        mainData.setAmount(samount);
                        mainData.setID((int) System.currentTimeMillis());
                        mainData.setReason(sreason);

                        if(tag!= null && !tag.isEmpty())
                            mainData.setTag(tag);
                        else
                            mainData.setTag("Not Assigned");

                        mainDatabase.mainDao().insert(mainData);
                        displaylist.add(mainData);

                        buildRecyclerView();

                }
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });


        donebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AddExpenditure.this, MainActivity.class));
                finish();
            }
        });


        chipGroup.setOnCheckedChangeListener(new ChipGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(ChipGroup group, int checkedId) {

                int x = group.getCheckedChipId();

                if(x == 1)
                    tag = "Food";
                else if(x ==2)
                    tag = "Travel";
                else if(x == 3)
                    tag = "Home";
                else
                    tag = "Other";

            }
        });
    }


    private void buildRecyclerView() {

//        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(AddExpenditure.this);
//        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
//        recyclerView.setLayoutManager(linearLayoutManager);
//
//        MyAdapter adapter = new MyAdapter(AddExpenditure.this,displaylist,"add");
//        recyclerView.setAdapter(adapter);
        etamount.setText("");
        etreason.setText("");

        if(displaylist.size() == 0){
            textViewDetails.setText("No Details Added Yet");
        }
        else{
            textViewDetails.setText("Show Details of Expenses on This Date");
        }

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

    }
}