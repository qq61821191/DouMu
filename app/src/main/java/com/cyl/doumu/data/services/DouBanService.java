package com.cyl.doumu.data.services;

import com.cyl.doumu.bean.MovieListBean;

import io.reactivex.Flowable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/*
 * @Description: 豆瓣接口
 * @Author: Cyl
 * @Version: V1.0
 * @Create: 2018/3/23 0023$ 15:46$
 */
public interface DouBanService {

    @GET("/v2/movie/in_theaters")
    Flowable<MovieListBean> getHotData(@Query("count")int count,@Query("start")int start,@Query("city")String city);
}
