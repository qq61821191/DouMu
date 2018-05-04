package com.cyl.doumu.data.services;

import com.cyl.doumu.bean.MovieListBean;

import io.reactivex.Flowable;
import io.reactivex.Observable;
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
    Flowable<MovieListBean> getHotData(@Query("start")int start, @Query("count")int count, @Query("city")String city);

    @GET("/v2/movie/coming_soon")
    Flowable<MovieListBean> getUpComing(@Query("start") int start,@Query("count") int count);

    @GET("/v2/movie/top250")
    Flowable<MovieListBean> getTop250(@Query("start") int start,@Query("count") int count);

    @GET("/v2/movie/us_box")
    Flowable<MovieListBean> getUsBox(@Query("start") int start,@Query("count") int count);

    @GET("/v2/movie/weekly")
    Flowable<MovieListBean> getWeekly(@Query("start") int start,@Query("count") int count);

    @GET("/v2/movie/new_movies")
    Flowable<MovieListBean> getBestNew(@Query("start") int start,@Query("count") int count);
}
