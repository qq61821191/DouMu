package com.cyl.doumu.index;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.cyl.doumu.R;
import com.cyl.doumu.base.BaseActivity;
import com.cyl.doumu.top.TopFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @description: 主页面
 * @author: Cyl
 * @date: 2018-03-20  17:02
 * @version: V1.0
 */
public class MainActivity extends BaseActivity {

    @BindView(R.id.tl_main_bottom) TabLayout mTabLayout;
    @BindView(R.id.viewpager) ViewPager mViewPager;
    //Tab 文字
    private final int[] TAB_TITLES = new int[]{R.string.main_menu_home,R.string.main_menu_top};
    //Tab 图片
    private final int[] TAB_IMGS = new int[]{R.drawable.sl_menu_home,R.drawable.sl_menu_top};
    //Fragment 数组
    private final Fragment[] TAB_FRAGMENTS = new Fragment[] {new HomeFragment(),new TopFragment()};
    //Tab 数目
    private final int COUNT = TAB_TITLES.length;
    private MyViewPagerAdapter mAdapter;

    private long exitTime = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initViews();
    }

    //初始化界面
    private void initViews() {
        setTabs(mTabLayout,this.getLayoutInflater(),TAB_TITLES,TAB_IMGS);
        mAdapter = new MyViewPagerAdapter(getSupportFragmentManager());
        mViewPager.setAdapter(mAdapter);
        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(mTabLayout));
        mTabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(mViewPager));
    }

    /**
     * @description: 设置Tab填充图标和文字
     */
    private void setTabs(TabLayout tabLayout, LayoutInflater inflater, int[] tabTitlees, int[] tabImgs){
        for (int i = 0; i < tabImgs.length; i++) {
            TabLayout.Tab tab = tabLayout.newTab();
            View view = inflater.inflate(R.layout.item_main_bottom,null);
            tab.setCustomView(view);

            TextView tvTitle = (TextView)view.findViewById(R.id.tv_menu_home);
            tvTitle.setText(tabTitlees[i]);
            ImageView imgTab = (ImageView) view.findViewById(R.id.iv_menu_home);
            imgTab.setImageResource(tabImgs[i]);
            tabLayout.addTab(tab);

        }
    }

    /**
     * @description: ViewPager 适配器
     */
    private class MyViewPagerAdapter extends FragmentPagerAdapter {
        public MyViewPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return TAB_FRAGMENTS[position];
        }

        @Override
        public int getCount() {
            return COUNT;
        }
    }


    //按两次返回退出程序
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {
            if ((System.currentTimeMillis() - exitTime) > 2000) {
                Toast.makeText(this, "再按一次退出程序", Toast.LENGTH_SHORT).show();
                exitTime = System.currentTimeMillis();
            } else {
                finish();
                System.exit(0);
            }
        }

        return true;

    }
}
