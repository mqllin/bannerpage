package com.example.gameHelper.uview.Components.MyGridView;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.GridView;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;
import androidx.viewpager.widget.ViewPager;

import com.example.gameHelper.uview.Adapter.DefaultGridAdapter;
import com.example.gameHelper.R;
import com.example.gameHelper.uview.Adapter.SwiperGridAdapter;
import com.example.gameHelper.uview.Struct.GridViewItem;
import com.example.gameHelper.uview.Struct.GridViewList;

import java.util.List;

/**带轮播功能的九宫格组件*/
public class MySwiperGrid extends LinearLayout {
    private Context mContext;
    private GridView gridView;
    private List<GridView> gridViewList;
    private ViewPager swiper;

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
     * @param rows 显示几行数据，推荐2
     * @param padding 设置item之间的间距，推荐10*/
    public void init(List<GridViewItem> list, int columns,int rows, int padding){
        LayoutInflater.from(mContext).inflate(R.layout.my_grid_view,this);
        List<List<GridViewItem>> itemGroup = null; //总数据
        List<GridViewItem> itemList = null;//临时存储每一页的数据
        int pageSize = columns * rows; // 一页显示多少个item
        int current_index=0;//当前页数


//        分组
        for(int i=0,size=list.size();i<size;i++){
            if(itemGroup.size()<pageSize){
                itemList.add(list.get(i));
            }
            if(itemList.size()==pageSize){
                itemGroup.add(itemList);
                current_index=current_index+1;
                itemList = null;
            }
        }


        for(int i=0,size = itemGroup.size();i<size;i++){
            DefaultGridAdapter gridAdapter = new DefaultGridAdapter(mContext, R.layout.grid_view_item,itemGroup.get(i));
            gridView = findViewById(R.id.c_grid_view);
            gridView.setNumColumns(columns);
            gridView.setVerticalSpacing(padding);
            gridView.setAdapter(gridAdapter);
            gridViewList.add(gridView);

        }



//
//        LayoutInflater.from(mContext).inflate(R.layout.my_swiper_grid_view,this);
//        SwiperGridAdapter swiperGridAdapter = new SwiperGridAdapter(mContext, R.layout.my_swiper_grid_view,list);
//        swiper = findViewById(R.id.c_swiper_grid_view);
//        swiper.setAdapter(swiperGridAdapter);





    }

}
