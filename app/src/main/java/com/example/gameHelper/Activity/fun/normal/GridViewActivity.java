package com.example.gameHelper.Activity.fun.normal;

import androidx.appcompat.app.AppCompatActivity;
import com.example.gameHelper.Struct.GridViewItem;
import com.example.gameHelper.Adapter.DefaultGridAdapter;

import android.os.Bundle;
import android.widget.GridView;

import com.example.gameHelper.R;

import java.util.ArrayList;
import java.util.List;

public class GridViewActivity extends AppCompatActivity {
    private GridView gridView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grid_view);

        gridView = findViewById(R.id.demo_grid_view);
        List<GridViewItem> list = new ArrayList<>();
        GridViewItem item;
        item = new GridViewItem("汉堡",R.drawable.hanbao,"");
        list.add(item);
        item = new GridViewItem("曲奇",R.drawable.quqi,"");
        list.add(item);
        item = new GridViewItem("汉堡",R.drawable.hanbao,"");
        list.add(item);
        item = new GridViewItem("曲奇",R.drawable.quqi,"");
        list.add(item);
        item = new GridViewItem("汉堡",R.drawable.hanbao,"");
        list.add(item);
        item = new GridViewItem("曲奇",R.drawable.quqi,"");
        list.add(item);
        item = new GridViewItem("汉堡",R.drawable.hanbao,"");
        list.add(item);
        item = new GridViewItem("曲奇",R.drawable.quqi,"");
        list.add(item);
        item = new GridViewItem("汉堡",R.drawable.hanbao,"");
        list.add(item);
        item = new GridViewItem("曲奇",R.drawable.quqi,"");
        list.add(item);
        item = new GridViewItem("汉堡",R.drawable.hanbao,"");
        list.add(item);
        item = new GridViewItem("曲奇",R.drawable.quqi,"");
        list.add(item);
        item = new GridViewItem("汉堡",R.drawable.hanbao,"");
        list.add(item);
        item = new GridViewItem("曲奇",R.drawable.quqi,"");
        list.add(item);



        DefaultGridAdapter gridAdapter = new DefaultGridAdapter(this,R.layout.grid_view_item,list);

        gridView.setAdapter(gridAdapter);


    }
}