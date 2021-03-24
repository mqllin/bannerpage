package com.example.gameHelper.uview.Components.MyGridView;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.GridView;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;

import com.example.gameHelper.uview.Adapter.DefaultGridAdapter;
import com.example.gameHelper.R;
import com.example.gameHelper.uview.Struct.GridViewItem;

import java.util.List;

/**带轮播功能的九宫格组件*/
public class MySwiperGrid extends LinearLayout {
    private Context mContext;
    private GridView gridView;

    public MySwiperGrid(Context context) {
        super(context);
        this.mContext = context;

    }

    public MySwiperGrid(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.mContext = context;

    }


    /**初始化swiper九宫格
     * @param list 数据集合
     * @param columns 一行显示几列数据，推荐5
     * @param padding 设置item之间的间距，推荐10*/
    public void init(List<GridViewItem> list, int columns, int padding){
        LayoutInflater.from(mContext).inflate(R.layout.my_grid_view,this);
        DefaultGridAdapter gridAdapter = new DefaultGridAdapter(mContext, R.layout.grid_view_item,list);
        gridView = findViewById(R.id.c_grid_view);
        gridView.setNumColumns(columns);
        gridView.setVerticalSpacing(padding);
        gridView.setAdapter(gridAdapter);
    }

}
