package com.insomniac.travelguru;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.google.firebase.auth.FirebaseAuth;

/**
 * Created by Sanjeev on 3/16/2018.
 */

public abstract class TabsFragmentActivity extends BaseActivity{

    private Toolbar mToolbar;
    private TabLayout mTabLayout;
    private ViewPager mViewPager;

    public abstract ViewPagerAdapter createViewPagerAdapter();

    @LayoutRes
    protected int getLayoutResId(){
        return R.layout.activity_drawer;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        setContentView(getLayoutResId());

        bind();
        setSupportActionBar(mToolbar);

    }

    public void bind(){
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mViewPager = (ViewPager) findViewById(R.id.viewPager);
        mTabLayout.setupWithViewPager(mViewPager);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mViewPager.setAdapter(createViewPagerAdapter());
    }

    @Override
    protected void onDestroy() {
        mViewPager.setAdapter(null);
        super.onDestroy();
    }

}
