package com.example.gameHelper.Components.MyBannerView;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewpager.widget.ViewPager;

import com.example.gameHelper.Activity.fun.banner.BannerSetAdapter;
import com.example.gameHelper.Activity.fun.normal.FristPageActivity;
import com.example.gameHelper.R;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class MyBannerView  extends LinearLayout {

    private Context mContext;
    final float BANNER_HEIGHT = 160;//设置轮播图的高度,单位dp
    private ImageView indicator;//圆点指示器
    private ImageView[] indicators;//圆点指示器数组
    public AtomicInteger index = new AtomicInteger();
    private Boolean inContinue = true;
    private ViewPager bannerView;
    private ViewGroup indicatorsView;
    private BannerSetAdapter bannerSetAdapter;
    private Handler bannerHandler = new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            bannerView.setCurrentItem(msg.what);
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


    /**
     * 通用式自定义swiper组件
     * @param viewList 图片集合
     * @param heightModel 0=banner模式，1=整屏轮播模式
     * @param showPoint 是否显示圆点
     * @param pointPostion left,right,center设置轮播圆点的位置：靠左，靠右，居中
     * @param Tag 行为标签，输入指定的标签用于执行先前写好的交互，默认传空。预设：frist_start
     *
     */
    public void initBanner( List<View> viewList,int heightModel,Boolean showPoint,String pointPostion,String Tag){

        LayoutInflater.from(mContext).inflate(R.layout.my_banner_view,this);
        bannerView  = findViewById(R.id.bannerview_wrap);
//        根据参数模式初始化轮播图组件的高度
        float height_dp = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, BANNER_HEIGHT,getResources().getDisplayMetrics());
        switch (heightModel){
            case 1:
//              整屏轮播
                bannerView.setLayoutParams(new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,RelativeLayout.LayoutParams.MATCH_PARENT));
                break;
            default:
                bannerView.setLayoutParams(new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,(int)height_dp));
                break;
        }

//        创建圆点

        indicatorsView = findViewById(R.id.bannerview_indicators);
        RelativeLayout.LayoutParams param = new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT);
        switch (pointPostion){
            case "left":
                param.addRule(RelativeLayout.ALIGN_LEFT);
                param.leftMargin = 0;
                param.rightMargin = toDip(-16);
                param.bottomMargin = 0;
                break;
            case "right":
                param.addRule(RelativeLayout.BELOW,R.id.bannerview_wrap);
                param.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
                param.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
                param.rightMargin = toDip(12);
                param.bottomMargin = toDip(-20);
                break;
            default:
                param.addRule(RelativeLayout.BELOW,R.id.bannerview_wrap);
                param.addRule(RelativeLayout.CENTER_HORIZONTAL);
                param.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
                param.bottomMargin = toDip(-25);
                break;
        }
        indicatorsView.setLayoutParams(param);
        if(!showPoint) indicatorsView.setVisibility(View.INVISIBLE);


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
            indicatorsView.addView(indicators[i]);

        }



        bannerSetAdapter = new BannerSetAdapter(viewList);
        bannerView.setAdapter(bannerSetAdapter);
        bannerView.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
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

        bannerView.setOnTouchListener(new View.OnTouchListener(){
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
    private int toDip(float val){
        return (int)TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, val,getResources().getDisplayMetrics());
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
