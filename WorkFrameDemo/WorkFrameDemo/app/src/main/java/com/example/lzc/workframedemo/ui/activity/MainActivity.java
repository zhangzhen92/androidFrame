package com.example.lzc.workframedemo.ui.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.lzc.workframedemo.R;
import com.example.lzc.workframedemo.httputils.NetWorks;
import com.example.lzc.workframedemo.httputils.RxSchedulers;
import com.example.lzc.workframedemo.utils.LogUtils;
import com.example.lzc.workframedemo.utils.ToastUtils;
import com.example.lzc.workframedemo.view.dialog.ActionSheetDialog;
import com.example.lzc.workframedemo.view.dialog.AlertDialog;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import rx.Subscriber;

public class MainActivity extends BaseActivity {
    private Button buttonAlertDialog;
    private Button buttonSheetDialog;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        loadData();

    }

    /**
     * 获取网络数据
     */
    private void loadData() {

        NetWorks.getInstance().getApi().getData().enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    LogUtils.LogI(response.body().string());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });

        NetWorks.getInstance().getApi().getRxData().compose(RxSchedulers.applyIOToMainThreadSchedulers()).subscribe(new Subscriber<ResponseBody>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(ResponseBody responseBody) {
                try {
                    LogUtils.LogI(responseBody.string());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

    }

    private void initView() {
        buttonAlertDialog = ((Button) findViewById(R.id.button_alertdialog));
        buttonSheetDialog = ((Button) findViewById(R.id.button_sheetdialog));
        buttonAlertDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog dialog = new AlertDialog(MainActivity.this).builder().setMsg("确定是否退出？")
                        .setPositiveButton("确定", Color.BLUE, new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                ToastUtils.showShortToast(getApplicationContext(),"确定");
                            }
                        }).setNegativeButton("取消", Color.BLUE, new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                ToastUtils.showShortToast(getApplicationContext(),"取消");
                            }
                        });
                   dialog.show();
            }
        });

        buttonSheetDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActionSheetDialog dialog = new ActionSheetDialog(MainActivity.this).addSheetItem("增加", ActionSheetDialog.SheetItemColor.Blue, new ActionSheetDialog.OnSheetItemClickListener() {
                    @Override
                    public void onClick(int which) {
                       Toast.makeText(getApplicationContext(),"点击了增加",Toast.LENGTH_SHORT).show();
                    }
                })
                        .addSheetItem("增加", ActionSheetDialog.SheetItemColor.Blue,null)
                        .addSheetItem("增加", ActionSheetDialog.SheetItemColor.Blue,null)
                        .addSheetItem("增加", ActionSheetDialog.SheetItemColor.Blue,null)

                 .builder();
                dialog.show();
            }
        });

    }

    @Override
    protected boolean isSlideBack() {
        return false;
    }
}
