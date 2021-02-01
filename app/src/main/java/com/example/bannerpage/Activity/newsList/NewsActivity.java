package com.example.bannerpage.Activity.newsList;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.bannerpage.Components.MyBannerView.MyBannerView;
import com.example.bannerpage.R;

import java.util.ArrayList;
import java.util.List;

public class NewsActivity extends AppCompatActivity {
//    本页面主要练习常见的新闻资讯列表布局，轮播图+新闻列表
    public MyBannerView myBannerView1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);

        List<View> viewList = new ArrayList<>();

        ImageView img1 = new ImageView(this);
        img1.setBackgroundResource(R.drawable.banner1);
        viewList.add(img1);
        ImageView img2 = new ImageView(this);
        img2.setBackgroundResource(R.drawable.banner2);
        viewList.add(img2);
        ImageView img3 = new ImageView(this);
        img3.setBackgroundResource(R.drawable.banner3);
        viewList.add(img3);
        ImageView img4 = new ImageView(this);
        img4.setBackgroundResource(R.drawable.banner4);
        viewList.add(img4);

        myBannerView1  = findViewById(R.id.banner_box);
        myBannerView1.initBanner(viewList);
    }




}