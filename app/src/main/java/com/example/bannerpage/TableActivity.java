package com.example.bannerpage;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.bannerpage.tablelayout.MyTableView;

public class TableActivity extends AppCompatActivity {

    private MyTableView myTableView1;
    private String[] headTitles = {"类型","金额","单价","日期"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_table);

        myTableView1 = findViewById(R.id.table_view);
        myTableView1.setTable(5,5);
        myTableView1.setTableHead(headTitles);
        myTableView1.setTableContent();


    }
}