package com.ie1e.health;

import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private ImageView iv_zhzx,iv_yyjy,iv_jksj,iv_tsfw,iv_ylzx;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            //透明状态栏
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            //透明导航栏
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        }
        initView();






//        MANService manService = MANServiceProvider.getService();
//        // 注册用户埋点
//        manService.getMANAnalytics().userRegister("usernick");
//
//        // 用户登录埋点
//        manService.getMANAnalytics().updateUserAccount("usernick", "102123354742kjhhjf");
    }

    private void initView() {
        iv_zhzx = (ImageView) findViewById(R.id.iv_zhzx);
        iv_yyjy = (ImageView) findViewById(R.id.iv_yyjy);
        iv_jksj = (ImageView) findViewById(R.id.iv_jksj);
        iv_tsfw = (ImageView) findViewById(R.id.iv_tsfw);
        iv_ylzx = (ImageView) findViewById(R.id.iv_ylzx);

        iv_zhzx.setOnClickListener(this);


    }


    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            //账户中心
            case R.id.iv_zhzx:

                break;
            //健康数据
            case R.id.iv_jksj:

                break;
            //特色服务
            case R.id.iv_tsfw:

                break;
            //预约就医
            case R.id.iv_yyjy:

                break;
            //医疗咨询
            case R.id.iv_ylzx:

                break;
            default:
                break;
        }
    }
}
