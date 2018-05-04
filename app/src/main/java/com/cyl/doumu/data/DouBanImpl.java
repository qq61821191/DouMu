package com.cyl.doumu.data;

import android.database.Observable;

import com.cyl.doumu.bean.MovieListBean;
import com.cyl.doumu.data.base.HttpConfigs;
import com.cyl.doumu.data.base.RetrofitHelper;
import com.cyl.doumu.data.services.DouBanService;

import io.reactivex.Flowable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;

/*
 * @Description: 
 * @Author: Cyl
 * @Version: V1.0
 * @Create: 2018/3/23 0023$ 17:47$
 */
public class DouBanImpl {
    private static DouBanImpl mDouBanImpl;
    private DouBanService mDouBanService;

    private DouBanImpl() {
        super();
    }

    public static DouBanImpl getInstance() {
        if (mDouBanImpl == null) {
            synchronized (DouBanImpl.class) {
                if (mDouBanImpl == null) {
                    mDouBanImpl = new DouBanImpl();
                }
            }
        }
        return mDouBanImpl;
    }

    private synchronized DouBanService getService() {
        if (mDouBanService == null) {
            Retrofit retrofit = RetrofitHelper.getInstance().getRetrofit();
            mDouBanService = retrofit.create(DouBanService.class);
        }
        return mDouBanService;
    }


    public Flowable<MovieListBean> getHotData(int start){

        return getService().getHotData(start, HttpConfigs.PAGE_SIZE,"福州");
    }

    public Flowable<MovieListBean> getUpComing(int start){

        return getService().getUpComing(start,HttpConfigs.PAGE_SIZE);
    }

    public Flowable<MovieListBean> getTop250(int start){

        return getService().getTop250(start,HttpConfigs.PAGE_SIZE);
    }

    public Flowable<MovieListBean> getUsBox(int start){

        return getService().getUsBox(start,HttpConfigs.PAGE_SIZE);
    }

    public Flowable<MovieListBean> getWeekly(int start){

        return getService().getWeekly(start,HttpConfigs.PAGE_SIZE);
    }

    public Flowable<MovieListBean> getBestNew(int start){

        return getService().getBestNew(start,HttpConfigs.PAGE_SIZE);
    }


}
