package com.insomniac.travelguru;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.Toolbar;

/**
 * Created by Sanjeev on 3/16/2018.
 */

public abstract class SingleFragmentActivity extends BaseActivity{

    protected abstract Fragment createFragment();

    public Fragment mActivityFragment;
    public Toolbar mToolbar;

    @LayoutRes
    protected int getLayoutResId(){
        return R.layout.activity_fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        setContentView(getLayoutResId());
        bind();
        setSupportActionBar(mToolbar);

        FragmentManager fragmentManager = getSupportFragmentManager();
        mActivityFragment = (Fragment) fragmentManager.findFragmentById(R.id.fragment_container);

        if(mActivityFragment == null){
            fragmentManager.beginTransaction().add(R.id.fragment_container,mActivityFragment).commit();
        }
    }

    protected void bind(){
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
    }
}
