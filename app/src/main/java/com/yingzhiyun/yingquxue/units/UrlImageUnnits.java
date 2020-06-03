package com.yingzhiyun.yingquxue.units;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LevelListDrawable;
import android.text.Html;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;

import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;

public class UrlImageUnnits implements Html.ImageGetter {

    Context c;
    TextView container;
    private final int width;
    private int width1;
    private int eight;

    public UrlImageUnnits(TextView text, Context c) {
        this.c = c;
        this.container = text;
        WindowManager wm = (WindowManager) c.getSystemService(Context.WINDOW_SERVICE);
        width = wm.getDefaultDisplay().getWidth();
    }

    public Drawable getDrawable(String source) {
        final LevelListDrawable drawable = new LevelListDrawable();
        Glide.with(c).asBitmap().load(source).into(new SimpleTarget<Bitmap>() {
            @Override
            public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
                if (resource != null) {
                    DisplayMetrics dm2 = c.getResources().getDisplayMetrics();
                    BitmapDrawable bitmapDrawable = new BitmapDrawable(resource);
                    drawable.addLevel(1, 1, bitmapDrawable);
                    if(resource.getWidth()>=width/2){
                        width1 = (int) (resource.getWidth()*1.3 );
                        eight = (int) (resource.getHeight()*1.3 );
                    }else{

                        width1 = (int) (resource.getWidth() * 2.5);
                        eight = (int) (resource.getHeight() * 2.5);
                        if(width1>width-100){
                            width1 = (int) (resource.getWidth() * 2);
                            eight = (int) (resource.getHeight() * 2);
                        }
                    }

                    drawable.setBounds(0, 0, width1, eight);
                    drawable.setLevel(1);
                    container.invalidate();
                    container.setText(container.getText());



                }
            }


        });
        return drawable;
    }

    /**
     * Bitmap放大的方法
     */

    private static Bitmap big(Bitmap bitmap) {
        Matrix matrix = new Matrix();
        matrix.postScale(1.3f, 2f);//长和宽放大缩小的比例
        Bitmap resizeBmp = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
        return resizeBmp;
    }


}


