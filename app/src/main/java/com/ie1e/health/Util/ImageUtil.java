package com.ie1e.health.Util;

import android.widget.ImageView;

import com.nostra13.universalimageloader.core.ImageLoader;

/**
 * 图片调用工具
 * Created by zhengxizhen on 16/6/22.
 */
public class ImageUtil {
    /**
     * 使用异步加载图片，并绑定到指定控件
     */
    public static void loader(String uri, ImageView imageView) {
        ImageLoader.getInstance().displayImage(uri, imageView);
    }

}
