package com.example.gameHelper;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
//import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.gameHelper.Activity.fun.newsList.NewsActivity;
import com.example.gameHelper.Activity.fun.normal.AppActivity;
import com.example.gameHelper.Activity.fun.normal.BannerActivity;
import com.example.gameHelper.Activity.fun.normal.DrawActivity;
import com.example.gameHelper.Activity.fun.normal.FristPageActivity;
import com.example.gameHelper.Activity.fun.normal.GridViewActivity;
import com.example.gameHelper.Activity.fun.normal.TableActivity;
import com.google.android.material.textfield.TextInputEditText;

//import android.content.DialogInterface;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Toast;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    private Button button1,button2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initButton();
    }

//    页面接收到Intent的回传参数事件
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        switch (requestCode){
            case 1:
//                基础弹窗
                if(resultCode==RESULT_OK){
                    String msg = data.getStringExtra("callBackData");
//                    Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
                    new AlertDialog.Builder(this)
                            .setIcon(R.drawable.quqi)
                            .setTitle("提示")
                            .setMessage(msg)
                            .setCancelable(false)
                            .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    Toast.makeText(MainActivity.this,"确定",Toast.LENGTH_SHORT).show();
                                }
                            })
                            .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    Toast.makeText(MainActivity.this,"取消了操作",Toast.LENGTH_SHORT).show();
                                }
                            })
                            .show();


//                    AlertDialog alertDialog2 = new AlertDialog.Builder(this)
//                            .setTitle("这是标题")
//                            .setMessage("有多个按钮")
//                            .setIcon(R.mipmap.ic_launcher)
//                            .setPositiveButton("确定", new DialogInterface.OnClickListener() {//添加"Yes"按钮
//                                @Override
//                                public void onClick(DialogInterface dialogInterface, int i) {
//                                    Toast.makeText(MainActivity.this, "这是确定按钮", Toast.LENGTH_SHORT).show();
//                                }
//                            })
//
//                            .setNegativeButton("取消", new DialogInterface.OnClickListener() {//添加取消
//                                @Override
//                                public void onClick(DialogInterface dialogInterface, int i) {
//                                    Toast.makeText(MainActivity.this, "这是取消按钮", Toast.LENGTH_SHORT).show();
//                                }
//                            })
//                            .setNeutralButton("普通按钮", new DialogInterface.OnClickListener() {//添加普通按钮
//                                @Override
//                                public void onClick(DialogInterface dialogInterface, int i) {
//                                    Toast.makeText(MainActivity.this, "这是普通按钮按钮", Toast.LENGTH_SHORT).show();
//                                }
//                            })
//                            .create();
//                    alertDialog2.show();

                    break;
                }
        }
        super.onActivityResult(requestCode,resultCode,data);
    }

//    设置右上角菜单

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
//         Toast.makeText(MainActivity.this,"onCreateOptionsMenu",Toast.LENGTH_SHORT).show();

        getMenuInflater().inflate(R.menu.menu,menu);
        return true;
    }

//    设置右上角菜单项目的按钮点击事件
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.menu_item1:
                Intent intent = new Intent("com.example.gameHelper.APP_ACTIVITY");
                intent.putExtra("data","隐式intent传递数据😄");
                startActivity(intent);
                Toast.makeText(MainActivity.this,"隐式intent😄",Toast.LENGTH_SHORT).show();

            break;
            case R.id.menu_item2:
               Intent intent1 = new Intent("com.example.gameHelper.APP_ACTIVITY");
               intent1.putExtra("data","返回后需要传回数据");
               startActivityForResult(intent1,1);
        }
        return super.onOptionsItemSelected(item);
    }
    public int index = 0;
//    跳转页面
    public void initButton(){
        button1 = findViewById(R.id.button1);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Toast.makeText(MainActivity.this,"hello",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(MainActivity.this, AppActivity.class);
                startActivity(intent);

            }
        });

//        打开带单选框的对话框
        button2 = findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String[] colors = {"黄色","绿色","蓝色"};
                new AlertDialog.Builder(MainActivity.this)
                        .setTitle("请选择一个颜色")
                        .setSingleChoiceItems(colors, 0, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                index = which;
                            }
                        })
                        .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Toast.makeText(MainActivity.this,"选择的结果是"+colors[index],Toast.LENGTH_SHORT).show();
                            }
                        })
                        .setCancelable(false)
                        .show();



            }
        });
    }

//打开自定义布局对话框
    public void openForm(View view){
        View v  = View.inflate(this,R.layout.activity_form,null);
        final TextInputEditText user =v.findViewById(R.id.input_user_i);
        final TextInputEditText pass =v.findViewById(R.id.input_pass_i);

        new AlertDialog.Builder(MainActivity.this)
                .setView(v)
                .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String username = user.getText().toString().trim();
                        String password = pass.getText().toString().trim();

                        Toast.makeText(MainActivity.this,"账号："+username +"密码："+password,Toast.LENGTH_SHORT).show();
                    }
                })
                .setCancelable(false)
                .show();
    }

//    打开进度条对话框
    public  void openLoading(View view){
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("加载进度");
        progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        progressDialog.setCancelable(false);
        progressDialog.show();

        new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i=0;i<=20;i++){
                    try {
                        Thread.sleep(200);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    progressDialog.setProgress(progressDialog.getProgress()+5);
                }
                progressDialog.dismiss();
            }
        }).start();


    }

//    打开日历选择器
    public void openCalendar (View view){
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        new DatePickerDialog(this,R.style.MyDatePickerDialogTheme, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                Toast.makeText(MainActivity.this,"选中了"+year+"年"+(month+1)+"月"+dayOfMonth+"日",Toast.LENGTH_SHORT).show();
            }
        },year,month,day).show();
    }


    public void openBannerPage (View view){
        Intent intent = new Intent(MainActivity.this, BannerActivity.class);
        startActivity(intent);
    }

    public void openTablePage (View view){
        Intent intent = new Intent(MainActivity.this, TableActivity.class);
        startActivity(intent);
    }

    public  void openNewsListPage(View view){
        Intent intent = new Intent(MainActivity.this, NewsActivity.class);
        startActivity(intent);
    }
    public  void openFristPage(View view){
        Intent intent = new Intent(MainActivity.this, FristPageActivity.class);
        startActivity(intent);
    }
    public  void openDrawTogglePage(View view){
        Intent intent = new Intent(MainActivity.this, DrawActivity.class);
        startActivity(intent);
    }
    public  void openGridViewPage(View view){
        Intent intent = new Intent(MainActivity.this, GridViewActivity.class);
        startActivity(intent);
    }


}