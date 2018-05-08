package com.cyl.doumu.top;

import android.support.annotation.NonNull;

import com.cyl.doumu.bean.MovieListBean;
import com.cyl.doumu.data.DouBanImpl;
import com.cyl.doumu.data.base.HttpConfigs;

import org.reactivestreams.Subscription;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Administrator on 2018/4/19.
 */

public class TopPresenter implements TopContract.Presenter {
    @NonNull
    private TopContract.View mView;

    @NonNull
    private DouBanImpl mDouBan;

    @NonNull
    private CompositeDisposable mCompositeDisposable;

    public TopPresenter(@NonNull TopContract.View mView) {
        this.mView = mView;
        this.mView.setPresenter(this);
        mDouBan=DouBanImpl.getInstance();
    }

    @Override
    public void getType() {
        List<String> typeList=new ArrayList<>();
        typeList.add("Top250");
        typeList.add("口碑榜");
        typeList.add("新片榜");
        typeList.add("北美票房榜");
        mView.showType(typeList);
    }

    @Override
    public void getTop250(int start) {
        addSubscription(
        mDouBan.getTop250((start-1)* HttpConfigs.PAGE_SIZE)
                .subscribeOn(Schedulers.io())
                .doOnSubscribe(new Consumer<Subscription>() {
                    @Override
                    public void accept(Subscription subscription) throws Exception {

                    }
                })
                .subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<MovieListBean>() {
                    @Override
                    public void accept(MovieListBean movieListBean) throws Exception {
                        mView.showData(movieListBean);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        mView.showError(throwable.getMessage());
                    }
                }));
    }

    @Override
    public void getUsBox(int start) {
        addSubscription(
        mDouBan.getUsBox((start-1)* HttpConfigs.PAGE_SIZE)
                .subscribeOn(Schedulers.io())
                .doOnSubscribe(new Consumer<Subscription>() {
                    @Override
                    public void accept(Subscription subscription) throws Exception {

                    }
                })
                .subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<MovieListBean>() {
                    @Override
                    public void accept(MovieListBean movieListBean) throws Exception {
                        mView.showData(movieListBean);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        mView.showError(throwable.getMessage());
                    }
                }));
    }

    @Override
    public void getWeekly(int start) {
        addSubscription(
        mDouBan.getWeekly((start-1)* HttpConfigs.PAGE_SIZE)
                .subscribeOn(Schedulers.io())
                .doOnSubscribe(new Consumer<Subscription>() {
                    @Override
                    public void accept(Subscription subscription) throws Exception {

                    }
                })
                .subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<MovieListBean>() {
                    @Override
                    public void accept(MovieListBean movieListBean) throws Exception {
                        mView.showData(movieListBean);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        mView.showError(throwable.getMessage());
                    }
                }));
    }

    @Override
    public void getBestNew(int start) {
        addSubscription(
        mDouBan.getBestNew((start-1)* HttpConfigs.PAGE_SIZE)
                .subscribeOn(Schedulers.io())
                .doOnSubscribe(new Consumer<Subscription>() {
                    @Override
                    public void accept(Subscription subscription) throws Exception {

                    }
                })
                .subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<MovieListBean>() {
                    @Override
                    public void accept(MovieListBean movieListBean) throws Exception {
                        mView.showData(movieListBean);
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
        getType();
        getTop250(1);
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
