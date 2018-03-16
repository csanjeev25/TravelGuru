package com.insomniac.travelguru;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;

import com.google.firebase.auth.FirebaseAuth;

/**
 * Created by Sanjeev on 3/16/2018.
 */

public class CompanionsActivity extends SingleFragmentActivity{

    public static Intent newIntent(Context context){
        Intent intent = new Intent(context,CompanionsActivity.class);
        return intent;
    }



    @Override
    protected boolean isAuthRequired() {
        return true;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        final ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowHomeEnabled(true);
    }

    @Override
    protected FirebaseAuth.AuthStateListener createAuthStateListener() {
        return null;
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

    @Override
    protected Fragment createFragment() {
        return CompanionsFragment.createFragment();
    }
}
