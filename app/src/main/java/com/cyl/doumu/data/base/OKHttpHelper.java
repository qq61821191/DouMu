package com.cyl.doumu.data.base;

import android.content.Context;
import android.util.Log;

import com.cyl.doumu.MyApplication;
import com.cyl.doumu.utils.NetUtil;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;

/*
 * @Description: 封装的okhttp请求
 * @Author: Cyl
 * @Version: V1.0
 * @Create: 2018/5/8
 */
public class OKHttpHelper {

    private volatile static OKHttpHelper mInstance;
    private OkHttpClient mOkHttpClient;
    private Context mContext;

    private OKHttpHelper(OkHttpClient okHttpClient) {
        mContext=MyApplication.getInstance().getContext();
        if (okHttpClient == null) {
            //设置缓存
            Cache cache = new Cache(new File(mContext.getCacheDir().getAbsolutePath(), "DouMuCache"), 1024 * 1024 * 50);
            //设置debug日志信息
            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
                @Override
                public void log(String message) {
                    Log.i("DouMu", message);
                }
            });
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

            mOkHttpClient = new OkHttpClient.Builder()
                    .connectTimeout(8, TimeUnit.SECONDS)
                    .writeTimeout(20, TimeUnit.SECONDS)
                    .readTimeout(20, TimeUnit.SECONDS)
                    .retryOnConnectionFailure(true)
                    .addInterceptor(interceptor)
    //                .addInterceptor(retryInterceptor)
                    .addInterceptor(new BasicParamsInterceptor.Builder().build())
                    .addInterceptor(cacheInterceptor)
                    .cache(cache)
                    .build();
        } else {
            mOkHttpClient = okHttpClient;
        }
    }

    private static OKHttpHelper initClient(OkHttpClient okHttpClient) {
        if (mInstance == null) {
            synchronized (OKHttpHelper.class) {
                if (mInstance == null) {
                    mInstance = new OKHttpHelper(okHttpClient);
                }
            }
        }
        return mInstance;
    }

    public static OKHttpHelper getInstance() {
        return initClient(null);
    }

    protected OkHttpClient getOkHttpClient() {
        return mOkHttpClient;
    }

    private Interceptor retryInterceptor = new Interceptor() {

        int maxCount = 3;

        @Override
        public Response intercept(Chain chain) throws IOException {
            Request request = chain.request();
            Response response;
            int retryNum = 0;
            for(; retryNum < maxCount - 1; retryNum ++) {
                try {
                    response = chain.proceed(request);
                    if(response.isSuccessful()) {
                        return response;
                    }
                } catch (Exception e) {
               //     throw e;
                }
            }
            response = chain.proceed(request);
            return response;
        }
    };

    private Interceptor cacheInterceptor = new Interceptor() {
        @Override
        public Response intercept(Chain chain) throws IOException {
            Request request = chain.request();
            if (!NetUtil.isNetworkConnected(mContext)) {
                request = request.newBuilder()
                        .cacheControl(CacheControl.FORCE_CACHE)
                        .build();
            }
            Response response = chain.proceed(request);
            if (NetUtil.isNetworkConnected(mContext)) {
                int maxAge = 0;
                // 有网络时 设置缓存超时时间0个小时
                response.newBuilder()
                        .header("Cache-Control", "public, max-age=" + maxAge)
                        .removeHeader("Pragma")// 清除头信息，因为服务器如果不支持，会返回一些干扰信息，不清除下面无法生效
                        .build();
            } else {
                // 无网络时，设置超时为1天
                int maxStale = 60 * 60 * 24;
                response.newBuilder()
                        .header("Cache-Control", "public, only-if-cached, max-stale=" + maxStale)
                        .removeHeader("Pragma")
                        .build();
            }
            return response;
        }
    };
}
