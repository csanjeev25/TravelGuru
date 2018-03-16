package com.insomniac.travelguru;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ProgressBar;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

/**
 * Created by Sanjeev on 3/16/2018.
 */

public abstract class BaseActivity extends AppCompatActivity{

    public ProgressBar mProgressBar;
    private FirebaseAuth mFirebaseAuth;
    protected FirebaseUser mFirebaseUser;
    private FirebaseAuth.AuthStateListener mAuthStateListener;
    protected  abstract  boolean isAuthRequired();
    protected abstract FirebaseAuth.AuthStateListener createAuthStateListener();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);

        mFirebaseAuth=FirebaseAuth.getInstance();
        mAuthStateListener = createAuthStateListener();
        if(isAuthRequired()){
            mFirebaseUser=mFirebaseAuth.getCurrentUser();
            if(mFirebaseUser==null){
                Intent intent = SignInActivity.newIntent(this);
                startActivity(intent);
            }
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        if(mAuthStateListener!=null)
            mFirebaseAuth.addAuthStateListener(mAuthStateListener);
    }

    @Override
    protected void onStop() {
        super.onStop();
        if(mAuthStateListener!=null)
            mFirebaseAuth.removeAuthStateListener(mAuthStateListener);
    }
}
