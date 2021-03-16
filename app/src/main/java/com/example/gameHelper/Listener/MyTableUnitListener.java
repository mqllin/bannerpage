package com.example.gameHelper.Listener;

import android.view.View;

import com.example.gameHelper.Interface.OnTableUnitClick;

public class MyTableUnitListener implements View.OnClickListener {

    int row;
    int col;
    String value;
    OnTableUnitClick onTableUnitClick1;
    public MyTableUnitListener(int row,int col,String value,OnTableUnitClick onTableUnitClick1){
        this.row = row;
        this.col = col;
        this.value = value;
        this.onTableUnitClick1 = onTableUnitClick1;
    }

    @Override
    public void onClick(View v) {
        onTableUnitClick1.onTableUnitClickListener(row,col,value);
    }
}
