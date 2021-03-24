package com.example.gameHelper.uview.Methods;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.widget.ImageView;

import androidx.core.graphics.drawable.RoundedBitmapDrawable;
import androidx.core.graphics.drawable.RoundedBitmapDrawableFactory;

/**
 * 工具类 绘制圆角*/
public class RoundView {
    /**
     * 绘制圆角图片
     * @param context 上下文this
     * @param Rid 图片资源R.id*/
    public ImageView image(Context context, int Rid){
        ImageView img =  new ImageView(context);
        RoundedBitmapDrawable circularBitmapDrawable = RoundedBitmapDrawableFactory.create(context.getResources(), BitmapFactory.decodeResource(context.getResources(), Rid));
        circularBitmapDrawable.setCornerRadius(30);
        img.setImageDrawable(circularBitmapDrawable);
        return img;
    }

}
