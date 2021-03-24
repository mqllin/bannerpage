package com.example.gameHelper.Activity.fun.normal;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

import com.example.gameHelper.uview.Interface.OnTableUnitClick;
import com.example.gameHelper.R;
import com.example.gameHelper.uview.Components.tablelayout.MyTableView;

public class TableActivity extends AppCompatActivity {

    private MyTableView myTableView1;
    private String[] headTitles = {"类型","金额","单价","日期"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_table);

        myTableView1 = findViewById(R.id.table_view);
        myTableView1.setTable(30, 10, new OnTableUnitClick() {
            @Override
            public void onTableUnitClickListener(int row, int col,String value) {
                Toast.makeText(TableActivity.this,"row:"+row+"col:"+col+" "+value,Toast.LENGTH_SHORT).show();
            }
        });
        myTableView1.setTableHead(headTitles);
        myTableView1.setTableContent();



    }
}