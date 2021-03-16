package com.example.gameHelper.Components.MyBannerView;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewpager.widget.ViewPager;

import com.example.gameHelper.Activity.fun.banner.BannerSetAdapter;
import com.example.gameHelper.R;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class MyBannerView  extends LinearLayout {

    private Context mContext;
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


    public MyBannerView(Context context) {
        super(context);
        this.mContext = context;
    }

    public MyBannerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.mContext = context;

    }

    public void initBanner( List<View> viewList ){
        LayoutInflater.from(mContext).inflate(R.layout.my_banner_view,this);

        viewPager  = findViewById(R.id.bannerview_wrap);
        viewGroup = findViewById(R.id.bannerview_indicators);

//        设置4个原点的属性
        indicators = new ImageView[viewList.size()];
        for (int i=0;i<indicators.length;i++){
//          定义新的布局容器的一些参数
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(25, 25);
            params.setMargins(5, 5, 5, 5);

            indicator = new ImageView(mContext);
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
