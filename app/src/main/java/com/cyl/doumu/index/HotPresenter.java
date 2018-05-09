package com.cyl.doumu.index;

import android.support.annotation.NonNull;

import com.cyl.doumu.bean.MovieListBean;
import com.cyl.doumu.data.DouBanImpl;
import com.cyl.doumu.data.base.HttpConfigs;

import org.reactivestreams.Subscription;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/*
 * @Description: 正在热映的presenter实现类
 * @Author: Cyl
 * @Version: V1.0
 * @Create: 2018/3/23 0023$ 11:55$
 */
public class HotPresenter implements HotContract.Presenter {

    @NonNull
    private HotContract.View mView;

    @NonNull
    private DouBanImpl mDoubanImpl;

    @NonNull
    private CompositeDisposable mCompositeDisposable;


    public HotPresenter(@NonNull HotContract.View topView) {
        mView = topView;
        mView.setPresenter(this);
        mDoubanImpl=DouBanImpl.getInstance();
    }

    @Override
    public void getHotData(int page) {
        addSubscription(
       mDoubanImpl.getHotData((page-1)* HttpConfigs.PAGE_SIZE)
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
                            mView.showHotListData(movieListBean);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        mView.showError(throwable.getMessage());
                    }
                }));


    }

    protected void addSubscription(Disposable s) {
        if (mCompositeDisposable == null) {
            mCompositeDisposable = new CompositeDisposable();
        }
        mCompositeDisposable.add(s);
    }



    @Override
    public void subscribe() {
        getHotData(1);
    }

    @Override
    public void unSubscribe() {
        if(mCompositeDisposable!=null){
            mCompositeDisposable.clear();
        }
    }
}
