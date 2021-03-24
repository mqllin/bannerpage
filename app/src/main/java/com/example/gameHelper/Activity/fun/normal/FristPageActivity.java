package com.example.gameHelper.Activity.fun.normal;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;


import com.example.gameHelper.uview.Components.MyBannerView.MyBannerView;
import com.example.gameHelper.MainActivity;
import com.example.gameHelper.R;

import java.util.ArrayList;
import java.util.List;


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
        img4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FristPageActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
        viewList.add(img4);
        swiper  = findViewById(R.id.banner_box);
        swiper.initBanner(viewList,1,false,"center",false);

    }


}