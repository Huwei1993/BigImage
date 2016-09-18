package com.shayne.bigimage;

import android.annotation.TargetApi;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Point;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    private ImageView iv;
    private  int screenWidth;
    private int screebHeight;
    Bitmap bitmap;
    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        iv =  (ImageView) findViewById(R.id.iv);
        WindowManager wm = (WindowManager)getSystemService(WINDOW_SERVICE);
        Point outSize = new Point();
        wm.getDefaultDisplay().getSize(outSize);
        screenWidth = outSize.x;
        screebHeight = outSize.y;
        System.out.println("手机屏幕的宽："+screenWidth+"手机屏幕的高："+screebHeight);
    }

    public void loadImage(View view){
        //为了避免内存溢出，根据屏幕尺寸对图片进行缩放

        BitmapFactory.Options opns = new BitmapFactory.Options();
        opns.inJustDecodeBounds = true;

        int width = opns.outWidth;
        int height = opns.outHeight;
        System.out.println("图片的高度是："+height+"图片的宽度是："+width);
        /**
         * 图片的与手机屏幕比例
         */
        int scale =1;
        int scaleX = width/screenWidth;
        int scaleY = height/screebHeight;

        if(scaleX >= scaleY&&scaleY>1){
            scale = scaleX;
        }else if(scaleY >= scaleX &&scaleX>1){
            scale = scaleY;
        }
        System.out.println("缩放比例为："+scale);
        opns.inSampleSize = scale;
        opns.inJustDecodeBounds =false;   //真实的解析这个位图，返回Bitmap
        bitmap = BitmapFactory.decodeResource(getResources(),R.mipmap.image2,opns);
        iv.setImageBitmap(bitmap);


//
//        Bitmap  bitmap = BitmapFactory.decodeResource(getResources(),R.mipmap.image2);
//        iv.setImageBitmap(bitmap);
//        bitmap.getWidth()
    }
}
