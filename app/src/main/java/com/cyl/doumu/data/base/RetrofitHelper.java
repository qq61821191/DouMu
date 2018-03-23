package com.cyl.doumu.data.base;


import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class RetrofitHelper {

    private static RetrofitHelper mInstance;
    private Retrofit mRetrofit;


    public RetrofitHelper() {
        mRetrofit = new Retrofit.Builder()
                .client(OKHttpHelper.getInstance().getOkHttpClient())
                .baseUrl(HttpConfigs.BASE_URL)
                .addConverterFactory(StringConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

    public static RetrofitHelper getInstance() {
        if(mInstance == null) {
            synchronized (RetrofitHelper.class) {
                if(mInstance == null)
                    mInstance = new RetrofitHelper();
            }
        }
        return mInstance ;
    }

    public Retrofit getRetrofit() {
        return mRetrofit;
    }
}
