package com.cyl.doumu.base;



        import android.content.Context;
        import android.os.Bundle;
        import android.support.annotation.Nullable;
        import android.support.v4.app.Fragment;
        import android.util.Log;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;

/**
 * @description: Fragment基类
 * @author: Cyl
 * @date: 2018-03-20  15:33
 * @version: V1.0
 */
public abstract class BaseFragment extends Fragment {

    protected View mRootView;
    public Context mContext;
    protected boolean isVisible;
    private boolean isPrepared;
    private boolean isFirst = true;
    public BaseFragment() {
        // Required empty_notdata public constructor
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
//        Log.d("TAG", "fragment->setUserVisibleHint");
        if (getUserVisibleHint()) {
            isVisible = true;
            lazyLoad();
        } else {
            isVisible = false;
            onInvisible();
        }
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = getActivity();
        setHasOptionsMenu(true);
//        Log.d("TAG", "fragment->onCreate");
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if(mRootView == null) {
            mRootView = inflateView(inflater, container, savedInstanceState);
        }
        ViewGroup viewGroup = (ViewGroup) mRootView.getParent();
        if(viewGroup != null) {
            viewGroup.removeView(mRootView);
        }
        return mRootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
//        Log.d("TAG", "fragment->onActivityCreated");
        isPrepared = true;
        lazyLoad();
    }

    protected void lazyLoad() {
        if (!isPrepared || !isVisible || !isFirst) {
            return;
        }
        Log.d("TAG", getClass().getName() + "->initData()");
        initData();
        isFirst = false;
    }

    //do something
    protected void onInvisible() {


    }

    public abstract View inflateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState);

    public abstract void initData();


}
