package com.insomniac.travelguru;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import com.google.firebase.auth.FirebaseAuth;

/**
 * Created by Sanjeev on 3/16/2018.
 */

public class FilesActivity extends SingleFragmentActivity{

    public static Intent newIntent(Context context){
        Intent intent = new Intent(context,FilesActivity.class);
        return intent;
    }

    @Override
    protected Fragment createFragment() {
        return new FilesFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
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
