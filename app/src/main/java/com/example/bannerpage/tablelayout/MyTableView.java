package com.example.bannerpage.tablelayout;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.example.bannerpage.R;

public class MyTableView extends LinearLayout {
    private int width,height;
    private int rows,columns;
    private Context mContext;
    private TableLayout mTableHead,mTableContent;
    private final int MP = ViewGroup.LayoutParams.MATCH_PARENT;
    private final int WC = ViewGroup.LayoutParams.WRAP_CONTENT;




    public MyTableView(Context context) {
        super(context);
        DisplayMetrics metrics = context.getResources().getDisplayMetrics();
        width = metrics.widthPixels;
        height = metrics.heightPixels;
        this.mContext = context;
    }

    public MyTableView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        DisplayMetrics metrics = context.getResources().getDisplayMetrics();
        width = metrics.widthPixels;
        height = metrics.heightPixels;
        this.mContext = context;
    }

    public void setTable(int rows,int columns){
        this.rows = rows;
        this.columns = columns;


    }

//    设置表头
    public void setTableHead(String[] title){
        initTableHead(title);

    }

//    初始化表头
    public void initTableHead(String[] title){
       LayoutInflater.from(mContext).inflate(R.layout.tableview,this);
       mTableHead = this.findViewById(R.id.table_head);
       mTableHead.setStretchAllColumns(true);//设置所有的单元格都可以伸缩
       TableRow rowHead = new TableRow(mContext);
       rowHead.setBackgroundColor(Color.rgb(255,255,255));
       for(int i=0;i<rows;i++){
           TextView tvHeadUnit = new TextView(mContext);
           tvHeadUnit.setWidth(width/rows);
           tvHeadUnit.setHeight(100);
           tvHeadUnit.setTextSize(18);
           tvHeadUnit.setGravity(Gravity.CENTER);

           tvHeadUnit.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
           tvHeadUnit.setTextColor(Color.rgb(255,255,255));
           tvHeadUnit.setBackgroundResource(R.drawable.shapee_head);
           if(i<title.length){
               tvHeadUnit.setText(title[i]);
           }
           rowHead.addView(tvHeadUnit,i);
       }
       mTableHead.addView(rowHead,MP,WC);

    }

    public void setTableContent(){
        InitTableContent();
    }

    public  void InitTableContent(){
        LayoutInflater.from(mContext).inflate(R.layout.tableview,this);
        mTableContent = this.findViewById(R.id.table_content);
        mTableContent.setStretchAllColumns(true);
        for(int row = 0 ;row < rows; row++){
            TableRow rowContent = new TableRow(mContext);
            rowContent.setBackgroundColor(Color.rgb(255,255,255));
            for(int col = 0; col < columns; col++){
                TextView tvContentUnit = new TextView(mContext);
                tvContentUnit.setWidth(width/columns);
                tvContentUnit.setHeight(100);
                tvContentUnit.setText("AAA");
                tvContentUnit.setGravity(Gravity.CENTER);
                if(col < columns - 1){
                    tvContentUnit.setBackgroundResource(R.drawable.shapee_left);
                }else {
                    tvContentUnit.setBackgroundResource(R.drawable.shapee_right);
                }
                rowContent.addView(tvContentUnit,col);
            }
            mTableContent.addView(rowContent,MP,WC);

        }

    }

}
