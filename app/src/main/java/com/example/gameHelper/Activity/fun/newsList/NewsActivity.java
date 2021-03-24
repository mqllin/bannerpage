package com.example.gameHelper.Activity.fun.newsList;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.gameHelper.uview.Components.MyBannerView.MyBannerView;
import com.example.gameHelper.R;
import com.example.gameHelper.uview.Methods.RoundView;

import java.util.ArrayList;
import java.util.List;

public class NewsActivity extends AppCompatActivity {
//    本页面主要练习常见的新闻资讯列表布局，轮播图+新闻列表
    public MyBannerView myBannerView1;
    public LinearLayout newsList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);

        List<View> viewList = new ArrayList<>();
        ImageView img1 = new RoundView().image(this,R.drawable.banner1);
        viewList.add(img1);
        ImageView img2 = new RoundView().image(this,R.drawable.banner2);
        viewList.add(img2);
        ImageView img3 = new RoundView().image(this,R.drawable.banner3);
        viewList.add(img3);
        ImageView img4 = new RoundView().image(this,R.drawable.banner4);
        viewList.add(img4);
        myBannerView1  = findViewById(R.id.banner_box);
        myBannerView1.initBanner(viewList,0,true,"right",true);
        this.initListView();
    }

    public void initListView(){
        List<View> viewList = new ArrayList<>(); //元素集合

        newsList = findViewById(R.id.news_list);


        for(int i=0;i<20;i++){
            LinearLayout row = new LinearLayout(this);
            row.setPadding(25,25,25,25);
            row.setOrientation(LinearLayout.HORIZONTAL);

//        设置左边图片
            ImageView img1 = new ImageView(this);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(250,
                    200);//两个400分别为添加图片的大小
            params.rightMargin = 15;
            img1.setLayoutParams(params);


            img1.setBackgroundResource(R.drawable.banner1);
            row.addView(img1);
//        设置右边容器
            LinearLayout row_right = new LinearLayout(this);
            row_right.setOrientation(LinearLayout.VERTICAL);

            TextView title = new TextView(this);
            title.setText("这是标题😄"+i);
            title.setTextColor(Color.parseColor("#212121"));
            title.setTextSize(18);
            TextView info = new TextView(this);
            info.setText("这是新闻的简要内容这是新闻的简要内容这是新闻的简要内容这是新闻的简要内容这是新闻的简要内容");
            info.setTextColor(Color.parseColor("#787878"));
            info.setLines(2);
            info.setEllipsize(TextUtils.TruncateAt.END);
            info.setTextSize(14);
            row_right.addView(title);
            row_right.addView(info);
            row.addView(row_right);
            newsList.addView(row);
        }








    }




}