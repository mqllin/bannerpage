package com.example.gameHelper.Activity.fun.normal;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import com.example.gameHelper.R;
import com.example.gameHelper.Activity.fun.banner.BannerSetAdapter;

public class BannerActivity extends AppCompatActivity {

    private ImageView indicator;//圆点指示器
    private ImageView[] indicators;//圆点指示器数组
    private AtomicInteger index = new AtomicInteger();
    private Boolean inContinue = true;
    private ViewPager viewPager;
    private ViewGroup viewGroup;
    private BannerSetAdapter bannerSetAdapter;
    private Handler bannerHandler = new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            viewPager.setCurrentItem(msg.what);
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_banner);
        initView();
    }

    public void initView(){
        viewPager  = findViewById(R.id.index_banner);
        viewGroup = findViewById(R.id.index_banner_indicators);


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

//        设置4个原点的属性
        indicators = new ImageView[viewList.size()];
        for (int i=0;i<indicators.length;i++){
//          定义新的布局容器的一些参数
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(25, 25);
            params.setMargins(5, 5, 5, 5);

            indicator = new ImageView(this);
            //给动态创建的元素赋予布局参数
            indicator.setLayoutParams(params);
            indicators[i] = indicator;
            if(i == 0){
                indicators[i].setBackgroundResource(R.drawable.indicator_select);
            }else {
                indicators[i].setBackgroundResource(R.drawable.indicator);
            }
            viewGroup.addView(indicators[i]);

        }

        bannerSetAdapter = new BannerSetAdapter(viewList);
        viewPager.setAdapter(bannerSetAdapter);
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                index.getAndSet(position);
                for (int i =0; i<indicators.length;i++){
                    if(i == position){
                        indicators[i].setBackgroundResource(R.drawable.indicator_select);
                    }else {
                        indicators[i].setBackgroundResource(R.drawable.indicator);
                    }
                }

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        viewPager.setOnTouchListener(new View.OnTouchListener(){
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()){
                    case MotionEvent.ACTION_DOWN:
                        inContinue = false;
                        break;
                    case MotionEvent.ACTION_UP:
                        inContinue = true;
                        break;
                }
                return false;
            }
        });

        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true){
                    if(inContinue){
                        bannerHandler.sendEmptyMessage(index.get());
                        whatOption();
                    }
                }
            }
        }).start();

    }

    private void whatOption(){
        index.incrementAndGet();
        if(index.get()>indicators.length){
            index.getAndAdd(-indicators.length-1);
        }
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}