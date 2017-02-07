package com.example.lzc.workframedemo.utils;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.WindowManager;

import com.example.lzc.workframedemo.FrameApplication;

/**
 * 类描述：像素转换
 * 创建人：zz
 * 创建时间：2017/2/7 17:06
 */
public class DensityUtils {

    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     */
    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    /**
     * 根据手机的分辨率从 px(像素) 的单位 转成为 dp
     */
    public static int px2dip(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

    public static int sp2px(Context context, float spValue) {
        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (spValue * fontScale + 0.5f);
    }

    /**
     * 屏幕的宽度
     * @return
     */
    public static int getScreenWidth() {
        WindowManager wm = (WindowManager) FrameApplication.application.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics metric = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(metric);
        return wm.getDefaultDisplay().getWidth();
    }


    /**
     * 屏幕的高度
     * @return
     */
    public static int getScreenHeight() {
        WindowManager wm = (WindowManager) FrameApplication.application.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics metric = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(metric);
        return wm.getDefaultDisplay().getHeight();
    }


}
