package com.insomniac.travelguru;

import android.content.Intent;
import android.os.PersistableBundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends DrawerActivity{


    @Override
    protected GoogleApiClient createGoogleApiClient() {
        return GoogleUtils.getGoogleApiClient(this, new GoogleApiClient.OnConnectionFailedListener() {
            @Override
            public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
                //
            }
        });
    }


    @Override
    protected Fragment createFragment() {
        return MainActivityFragment.newInstance(this);
    }

    @Override
    protected FirebaseAuth.AuthStateListener createAuthStateListener() {
        return new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                updateNavigationView();
            }
        };
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);

        FloatingActionButton floatingActionButton = (FloatingActionButton) findViewById(R.id.fab);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view,"cool",Snackbar.LENGTH_SHORT).setAction("Action",null).show();
            }
        });
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {

        Intent intent;
        int id = item.getItemId();

        switch (id) {
            case R.id.nav_explore:
                break;
            case R.id.nav_places:
                intent = PlacesActivity.newIntent(this);
                startActivity(intent);
                break;
            case R.id.nav_trips:
                intent = TripsActivity.newIntent(this);
                startActivity(intent);
                break;
            case R.id.nav_timeline:
                intent = TimelineActivity.newIntent(this);
                startActivity(intent);
                break;
            case R.id.nav_media:
                intent = MediaActivity.newIntent(this);
                startActivity(intent);
                break;
            case R.id.nav_files:
                intent = FilesActivity.newIntent(this);
                startActivity(intent);
                break;
            case R.id.nav_companions:
                intent = CompanionsActivity.newIntent(this);
                startActivity(intent);
                break;
            case R.id.nav_settings:
                intent = SettingsActvity.newIntent(this);
                startActivity(intent);
                break;
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
