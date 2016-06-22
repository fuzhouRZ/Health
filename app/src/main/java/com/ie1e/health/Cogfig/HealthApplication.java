package com.ie1e.health.Cogfig;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import com.alibaba.sdk.android.man.MANService;
import com.alibaba.sdk.android.man.MANServiceProvider;
import com.alibaba.sdk.android.push.CloudPushService;
import com.alibaba.sdk.android.push.CommonCallback;
import com.alibaba.sdk.android.push.noonesdk.PushServiceFactory;
import com.ie1e.health.R;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;

/**
 * 智诊断 Android开发全局配置
 * Created by zhengxizhen on 16/6/22.
 */
public class HealthApplication extends Application {

    private String TAG = "[智诊断 Android] > ";

    @Override
    public void onCreate() {
        super.onCreate();
        //配置程序加载图片全局配置
        DisplayImageOptions options = new DisplayImageOptions.Builder()
                .showStubImage(R.mipmap.ic_launcher)
                .showImageForEmptyUri(R.mipmap.ic_launcher)
                .showImageOnFail(R.mipmap.ic_launcher)
                .cacheInMemory(true)
                .cacheOnDisc(false)
                .displayer(new RoundedBitmapDisplayer(20))
                .build();
        // This configuration tuning is custom. You can tune every option, you may tune some of them,
        // or you can create default configuration by
        //  ImageLoaderConfiguration.createDefault(this);
        // method.
        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(getApplicationContext())
                .threadPriority(Thread.NORM_PRIORITY - 2)
                .denyCacheImageMultipleSizesInMemory()
                .discCacheFileNameGenerator(new Md5FileNameGenerator())
                .tasksProcessingOrder(QueueProcessingType.LIFO)
                .defaultDisplayImageOptions(options)
                .build();
        //Initialize ImageLoader with configuration
        ImageLoader.getInstance().init(config);

        initCloudChannel(this);
        initCloudAnalytics(this);
    }

    private void initCloudAnalytics(Application application) {
        /* 【注意】建议您在Application中初始化MAN，以保证正常获取MANService*/
        // 获取MAN服务
        MANService manService = MANServiceProvider.getService();
        // 打开调试日志，线上版本建议关闭
//        manService.getMANAnalytics().turnOnDebug();
        // MAN初始化方法之一，从AndroidManifest.xml中获取appKey和appSecret初始化
        manService.getMANAnalytics().init(application, getApplicationContext());
        // MAN另一初始化方法，手动指定appKey和appSecret
//        String appKey = "23393816";
//        String appSecret = "a5869515c65bb4ce9a816dc63ba437b9";
//        manService.getMANAnalytics().init(this, getApplicationContext(), appKey, appSecret);
        // 若需要关闭 SDK 的自动异常捕获功能可进行如下操作,详见文档5.4
//        manService.getMANAnalytics().turnOffCrashHandler();
        // 通过此接口关闭页面自动打点功能，详见文档4.2
//        manService.getMANAnalytics().turnOffAutoPageTrack();
        // 设置渠道（用以标记该app的分发渠道名称），如果不关心可以不设置即不调用该接口，渠道设置将影响控制台【渠道分析】栏目的报表展现。如果文档3.3章节更能满足您渠道配置的需求，就不要调用此方法，按照3.3进行配置即可；
//        manService.getMANAnalytics().setChannel("kaifa");//某渠道
        // 若AndroidManifest.xml 中的 android:versionName 不能满足需求，可在此指定
        // 若在上述两个地方均没有设置appversion，上报的字段默认为null
//        manService.getMANAnalytics().setAppVersion("0.0.1");
    }

    /**
     * 初始化云推送通道
     *
     * @param applicationContext
     */
    private void initCloudChannel(Context applicationContext) {
        PushServiceFactory.init(applicationContext);
        CloudPushService pushService = PushServiceFactory.getCloudPushService();
        Log.e(TAG, pushService.getDeviceId());
        pushService.register(applicationContext, new CommonCallback() {
            @Override
            public void onSuccess(String response) {
                Log.d(TAG, "z cloudchannel success");
            }

            @Override
            public void onFailed(String errorCode, String errorMessage) {
                Log.d(TAG, "init cloudchannel failed -- errorcode:" + errorCode + " -- errorMessage:" + errorMessage);
            }
        });
    }
}
