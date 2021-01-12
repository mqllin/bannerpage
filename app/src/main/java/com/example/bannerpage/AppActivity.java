package com.example.bannerpage;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.media.Image;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.TextView;

public class AppActivity extends AppCompatActivity {
    public final int CAMERARES  = 10;
    public ImageView Camera_image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app);
        initViewText();

        Camera_image = findViewById(R.id.img_camera);

    }

    @Override
    protected void onStop() {
        super.onStop();

    }

    public  void initViewText(){
        Intent intent = getIntent();
        String data = intent.getStringExtra("data");
        TextView text = findViewById(R.id.textView);
        text.setText(data);

        Button button_callback = findViewById(R.id.button_callback);
        button_callback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callBackData();
            }
        });

        ProgressBar progressBar = findViewById(R.id.progressBar);
        TextView proText = findViewById(R.id.proText);
        SeekBar seekBar = findViewById(R.id.seekBar);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                progressBar.setProgress(progress);
                proText.setText("现在的进度是"+progress+"%");
                if(progressBar.getProgress()>=progressBar.getMax()){
                    progressBar.setVisibility(View.INVISIBLE);
                }else {
                    progressBar.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode){
            case CAMERARES:
                if(resultCode==RESULT_OK){
                    Bitmap bitmap = (Bitmap)data.getExtras().get("data");
                    Camera_image.setImageBitmap(bitmap);
                }
                break;

        }
    }

    public void callBackData(){
        Intent intent = new Intent();
        intent.putExtra("callBackData","这是返回的数据");
        setResult(RESULT_OK,intent);
        finish();
    }


    public void openCamera(View view){
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//        启用拍照程序并将返回的图片显示在imageView中
        startActivityForResult(intent,CAMERARES);

    }
}