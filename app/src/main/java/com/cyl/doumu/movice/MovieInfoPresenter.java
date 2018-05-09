package com.cyl.doumu.movice;

import android.support.annotation.NonNull;

import com.cyl.doumu.bean.MovieEntry;
import com.cyl.doumu.data.DouBanImpl;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/*
 * @Description: 电影详情 presenter的实现类
 * @Author: Cyl
 * @Version: V1.0
 * @Create: 2018/5/8
 */

public class MovieInfoPresenter implements MoviceInfoContract.Presenter {
    @NonNull
    private MoviceInfoContract.View mView;

    @NonNull
    private DouBanImpl mDouBan;

    @NonNull
    private CompositeDisposable mCompositeDisposable;

    public MovieInfoPresenter(@NonNull MoviceInfoContract.View mView) {
        this.mView = mView;
        this.mView.setPresenter(this);
        mDouBan=DouBanImpl.getInstance();
    }

    @Override
    public void getMovieInfo(String id) {
        addSubscription(mDouBan.getMovieInfo(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<MovieEntry>() {
                    @Override
                    public void accept(MovieEntry entry) throws Exception {
                        mView.showMovieInfo(entry);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        mView.showError(throwable.getMessage());
                    }
                }));
    }

    @Override
    public void subscribe() {
        mView.initData();
    }

    @Override
    public void unSubscribe() {
        if(mCompositeDisposable!=null){
            mCompositeDisposable.clear();
        }
    }

    protected void addSubscription(Disposable s) {
        if (mCompositeDisposable == null) {
            mCompositeDisposable = new CompositeDisposable();
        }
        mCompositeDisposable.add(s);
    }
}
