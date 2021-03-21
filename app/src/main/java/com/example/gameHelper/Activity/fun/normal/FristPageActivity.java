package com.example.gameHelper.Activity.fun.normal;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;


import com.example.gameHelper.Components.MyBannerView.MyBannerView;
import com.example.gameHelper.R;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;


public class FristPageActivity extends AppCompatActivity {


    private MyBannerView swiper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_frist_page);
        initView();
    }

    public void initView(){

        List<View> viewList = new ArrayList<>();

        ImageView img1 = new ImageView(this);
        img1.setBackgroundResource(R.drawable.frist_banner_01);
        viewList.add(img1);
        ImageView img2 = new ImageView(this);
        img2.setBackgroundResource(R.drawable.frist_banner_02);
        viewList.add(img2);
        ImageView img3 = new ImageView(this);
        img3.setBackgroundResource(R.drawable.frist_banner_03);
        viewList.add(img3);
        ImageView img4 = new ImageView(this);
        img4.setBackgroundResource(R.drawable.frist_banner_04);
        viewList.add(img4);
        swiper  = findViewById(R.id.banner_box);



        swiper.initBanner(viewList,1,false,"center","frist_start");

        swiper.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                Log.e("外部Index--》", String.valueOf(swiper.index));
                return false;
            }
        });
        swiper.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("点击外部Index--》", String.valueOf(swiper.index));
            }
        });

    }


}