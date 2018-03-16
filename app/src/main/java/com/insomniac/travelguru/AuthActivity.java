package com.insomniac.travelguru;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;

import com.google.firebase.auth.FirebaseAuth;

/**
 * Created by Sanjeev on 3/16/2018.
 */

public class AuthActivity extends SingleFragmentActivity{

    public static Intent newIntent(Context c){
        Intent intent = new Intent(c,AuthActivity.class);
        return intent;
    }

    @Override
    protected Fragment createFragment() {
        return AuthFragment.newInstance(this);
    }

    @Override
    protected boolean isAuthRequired() {
        return false;
    }

    @Override
    protected FirebaseAuth.AuthStateListener createAuthStateListener() {
        return new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                ((AuthFragment) mActivityFragment).onAuthStateChanged(firebaseAuth);
            }
        };
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        mActivityFragment.onActivityResult(requestCode,resultCode,data);
    }
}
