package com.ie1e.health.Util;

import android.graphics.Bitmap;
import android.widget.ImageView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.NetworkImageView;
import com.ie1e.health.Cogfig.HealthApplication;
import com.ie1e.health.R;



/**
 * 作者：zf on 2016/6/29 14:28
 * 邮箱：752323877@qq.com
 */
public class ImageLoaderUtil {

    /*
    * 通过ImageRequest来显示网络图片
    * */

    /**
     *
     * @param url
     * @param imageView
     * Config     图片的颜色属性，Bitmap.Config下的几个常量都可以在这里使用，其中ARGB_8888可以展示最好的颜色属性，
     *            每个图片像素占据4个字节的大小，而RGB_565则表示每个图片像素占据2个字节大小
     */
    public static void setImageRequest(String url, final ImageView imageView) {

        ImageRequest imageRequest = new ImageRequest(url, new Response.Listener<Bitmap>() {
            @Override
            public void onResponse(Bitmap bitmap) {
                imageView.setImageBitmap(bitmap);
            }
        }, 0, 0, Bitmap.Config.RGB_565, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                imageView.setBackgroundResource(R.mipmap.ic_launcher);

            }
        });
        HealthApplication.getRequestQueue().add(imageRequest);
    }

    /*
    * 通过ImageLoader来显示网络图片
    * */
    public static void setImageLoader(String url, ImageView imageView, int defaultImageResId, int errorImageResId) {
        ImageLoader loader = new ImageLoader(HealthApplication.getRequestQueue(),BitmapCache.instance());
        ImageLoader.ImageListener imageListener = ImageLoader.getImageListener(imageView, defaultImageResId, errorImageResId);
        loader.get(url, imageListener);
    }

    /*
    * 通过Volley的NetWorkImageView来显示网络图片
    * */
    /**
     *
     * @param url  加载图片的路径
     * @param netWorkImageView  加载图片的控件
     * @param defaultImageResId 加载图片
     * @param errorImageResId
     */
    public static void setNetWorkImageView(String url, NetworkImageView netWorkImageView, int defaultImageResId, int errorImageResId) {

        ImageLoader loader = new ImageLoader(HealthApplication.getRequestQueue(),BitmapCache.instance());

        netWorkImageView.setDefaultImageResId(defaultImageResId);
        netWorkImageView.setErrorImageResId(errorImageResId);
        netWorkImageView.setImageUrl(url, loader);

    }

}