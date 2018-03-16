package com.insomniac.travelguru;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;

import com.google.firebase.auth.FirebaseAuth;

/**
 * Created by Sanjeev on 3/16/2018.
 */

public class PlacesActivity extends TabsFragmentActivity{

    @Override
    public ViewPagerAdapter createViewPagerAdapter() {
        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        viewPagerAdapter.addFragment(BucketListFragment.createFragment(PlacesActivity.this),getResources().getString(R.string.tab_places_bucketlist));
        viewPagerAdapter.addFragment(LikedFragment.createFragment(PlacesActivity.this),getResources().getString(R.string.tab_places_liked));
        viewPagerAdapter.addFragment(RecommendedFragment.createFragment(PlacesActivity.this),getResources().getString(R.string.tab_places_recommended));
        viewPagerAdapter.addFragment(VisitedFragment.createFragment(PlacesActivity.this),getResources().getString(R.string.tab_places_visited));
        return viewPagerAdapter;
    }

    public static Intent newIntent(Context context){
        Intent intent = new Intent(context,PlacesActivity.class);
        return intent;
    }

    @Override
    protected boolean isAuthRequired() {
        return true;
    }

    @Override
    protected FirebaseAuth.AuthStateListener createAuthStateListener() {
        return null;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }

    @Override
    protected void onResume() {
        super.onResume();
        this.overridePendingTransition(R.anim.slide_in_right,R.anim.slide_in_left);
    }

    @Override
    protected void onPause() {
        super.onPause();
        this.overridePendingTransition(R.anim.slide_in_left,R.anim.slide_in_right);
    }

}
