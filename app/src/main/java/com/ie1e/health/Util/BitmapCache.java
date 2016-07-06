package com.ie1e.health.Util;

import android.graphics.Bitmap;
import android.util.LruCache;

import com.android.volley.toolbox.ImageLoader;

/**
 * 作者：zf on 2016/6/29 15:34
 * 邮箱：752323877@qq.com
 */
public class BitmapCache implements ImageLoader.ImageCache {

    private LruCache<String,Bitmap> mMemoryCache;

    private static BitmapCache mBitmapCache;

    //设置最大的 尺寸值
    public BitmapCache() {
        //构造方法 实现 LruCache 缓存 图片
        int maxMemory = (int) Runtime.getRuntime().maxMemory();
        int cacheSize = maxMemory / 8;
        mMemoryCache = new LruCache<String,Bitmap>(cacheSize){
            @Override
            protected int sizeOf(String key, Bitmap value) {
                return value.getRowBytes() * value.getHeight();
            }
        };

    }

    public static BitmapCache instance(){
        if(mBitmapCache == null){
            mBitmapCache = new BitmapCache();
        }
        return mBitmapCache;
    }

    @Override
    public Bitmap getBitmap(String url) {
        // 得到
        return mMemoryCache.get(url);
    }

    @Override
    public void putBitmap(String url, Bitmap bitmap) {
        // 设置
        if(getBitmap(url) == null) {
            mMemoryCache.put(url, bitmap);
        }
    }

}