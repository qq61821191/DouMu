package com.cyl.doumu.data;

import com.cyl.doumu.bean.MovieListBean;
import com.cyl.doumu.data.base.HttpConfigs;
import com.cyl.doumu.data.base.RetrofitHelper;
import com.cyl.doumu.data.services.DouBanService;

import io.reactivex.Flowable;
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
}
