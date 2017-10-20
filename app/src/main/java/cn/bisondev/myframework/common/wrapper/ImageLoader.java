package cn.bisondev.myframework.common.wrapper;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import cn.bisondev.myframework.R;

/**
 * 封装图片加载方法（便于替换）
 * Author: Bison
 * Date: 2017/6/11
 * Gmail: bisonqin@gmail.com
 */
public class ImageLoader {

    public static void load(Context context, Object source, ImageView imageView, int defaultImageRes) {
        //Glide v4后需要注意的地方
        RequestOptions options = new RequestOptions()
                .centerCrop()
                .placeholder(defaultImageRes);
        Glide.with(context)
                .load(source)
                .apply(options)
                .into(imageView);
    }

    public static void load(Context context, Object source, ImageView imageView) {
        load(context, source, imageView, R.mipmap.ic_launcher);
    }

    public static void load(Object source, ImageView imageView) {
        load(imageView.getContext(), source, imageView);
    }

    public static void loadWithCircle(Context context, Object source, ImageView imageView, int defaultImageRes) {
        RequestOptions options = new RequestOptions()
                .circleCrop()
                .placeholder(defaultImageRes);
        Glide.with(context)
                .load(source)
                .apply(options)
                .into(imageView);
    }

    public static void loadWithCircle(Object source, ImageView imageView) {
        loadWithCircle(imageView.getContext(), source, imageView, R.mipmap.ic_launcher);
    }

    public static void loadWithCircle(Object source, ImageView imageView, int defaultImageRes) {
        loadWithCircle(imageView.getContext(), source, imageView, defaultImageRes);
    }
}
