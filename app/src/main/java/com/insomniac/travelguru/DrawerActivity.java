package com.insomniac.travelguru;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserInfo;

/**
 * Created by Sanjeev on 3/16/2018.
 */

public abstract class DrawerActivity extends SingleFragmentActivity implements NavigationView.OnNavigationItemSelectedListener{

    private FirebaseAuth mFirebaseAuth;
    private GoogleApiClient mGoogleApiClient;
    private TextView mEmailTextView;
    private TextView mDisplayNameTextView;
    private FirebaseUser mFirebaseUser;
    private ImageView mProfileImageView;
    private Button mSignInButton;
    private Button mSignOutButton;
    private NavigationView mNavigationView;

    @Override
    protected boolean isAuthRequired() {
        return true;
    }

    protected abstract GoogleApiClient createGoogleApiClient();

    @LayoutRes
    protected int getLayoutResId(){
        return R.layout.activity_drawer;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        mGoogleApiClient = createGoogleApiClient();
        mFirebaseAuth = FirebaseAuth.getInstance();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar, R.string.navigation_drawer_open,R.string.navigation_drawer_close);

        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();

        mNavigationView = (NavigationView) findViewById(R.id.nav_view);
        mNavigationView.setNavigationItemSelectedListener(this);
        updateNavigationView();
    }

    public void updateNavigationView(){
        mFirebaseAuth = FirebaseAuth.getInstance();
        mFirebaseUser = mFirebaseAuth.getCurrentUser();
        View header = mNavigationView.getHeaderView(0);
        mEmailTextView = (TextView) header.findViewById(R.id.email);
        mDisplayNameTextView = (TextView) header.findViewById(R.id.userDisplayName);
        mProfileImageView = (ImageView) header.findViewById(R.id.profileImageView);
        mSignInButton = (Button) findViewById(R.id.google_sign_in_button);
        mSignOutButton = (Button) findViewById(R.id.sign_out_button);

        mSignOutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signOut();
            }
        });

        mSignInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = SignInActivity.newIntent(DrawerActivity.this);
                startActivity(intent);
            }
        });

        if(mFirebaseUser != null){
            String name = "";
            Uri photoUri = null;
            for(UserInfo profile : mFirebaseUser.getProviderData()){
                name=profile.getDisplayName();
                photoUri=profile.getPhotoUrl();
            }

            if(photoUri != null){
                Glide.with(this).load(photoUri).into(mProfileImageView);
            }
            mEmailTextView.setText(mFirebaseUser.getEmail());
            mDisplayNameTextView.setText(name);
            mSignInButton.setVisibility(View.GONE);
            mSignOutButton.setVisibility(View.VISIBLE);
        }else{
            mSignOutButton.setVisibility(View.GONE);
            mSignInButton.setVisibility(View.VISIBLE);
            mEmailTextView.setText("");
            mDisplayNameTextView.setText("");

            mProfileImageView.setImageDrawable(getResources().getDrawable(R.mipmap.ic_launcher));

        }
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        if(drawerLayout.isDrawerOpen(GravityCompat.START))
            drawerLayout.closeDrawer(GravityCompat.START);
        else
            super.onBackPressed();
    }

    public void signOut(){
        mFirebaseAuth.signOut();
        Auth.GoogleSignInApi.signOut(mGoogleApiClient);
    }
}
