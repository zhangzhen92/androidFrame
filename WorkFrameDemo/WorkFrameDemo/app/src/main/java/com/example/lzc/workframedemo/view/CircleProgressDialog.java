package com.example.lzc.workframedemo.view;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.lzc.workframedemo.R;
import com.example.lzc.workframedemo.utils.DensityUtils;


/**
 * 类描述：圆圈加载框
 * 创建人：zz
 * 创建时间：2017/2/7 17:10
 */
public class CircleProgressDialog extends AlertDialog {


    public static final int CIRCLE = 0;
    public static final int FLOWER = 1;
    private ImageView imgCircle;
    private Animation animation;
    private TextView tvMsg;
    private String msg;
    private boolean cancelable = true;
    private int type = 0;
    private Context context;

    public CircleProgressDialog(Context context) {
        super(context, R.style.CircleProgressDialog);
        this.context = context;
    }

    public CircleProgressDialog(Context context, int type){
        this(context);
        this.type = type;
    }

    public CircleProgressDialog(Context context, String msg){
        this(context);
        this.msg = msg;
    }

    public CircleProgressDialog(Context context, String msg, int type){
        this(context);
        this.msg = msg;
        this.type = type;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.circle_progress_dialog);
        setCanceledOnTouchOutside(false);

        animation = AnimationUtils.loadAnimation(getContext(), R.anim.progress_dialog_rotate);
        animation.setInterpolator(new LinearInterpolator());
        animation.setFillAfter(true);

        imgCircle = (ImageView) findViewById(R.id.img_circle);
        switch (type){
            default:
            case CIRCLE:
                animation.setDuration(700);
                break;
            case FLOWER:
                imgCircle.setImageResource(R.drawable.aliwx_img_loading);
                ViewGroup.LayoutParams params = imgCircle.getLayoutParams();
                int width = DensityUtils.dip2px(getContext(),25);
                params.width = width;
                params.height = width;
                animation.setDuration(1600);
        }

        if(!TextUtils.isEmpty(msg)) {
            tvMsg = (TextView) findViewById(R.id.tv_msg);
            tvMsg.setVisibility(View.VISIBLE);
            tvMsg.setText(msg);
        }else {
            View llWraper = findViewById(R.id.ll_wraper);
            ViewGroup.LayoutParams params = llWraper.getLayoutParams();
            params.width = params.height;
        }
    }

    /**
     * 在AlertDialog的 onStart() 生命周期里面执行开始动画
     */
    @Override
    protected void onStart() {
        super.onStart();
        if (animation != null) {
            imgCircle.startAnimation(animation);
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        imgCircle.clearAnimation();
    }

}
