package com.insomniac.travelguru;

import android.support.v4.app.FragmentActivity;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.GoogleApiClient;

/**
 * Created by Sanjeev on 3/16/2018.
 */

public class GoogleUtils {

    public static GoogleApiClient getGoogleApiClient(FragmentActivity context,GoogleApiClient.OnConnectionFailedListener listener){
        GoogleSignInOptions googleSignInOptions = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(context.getString(R.string.default_web_client_id))
                .requestEmail()
                .build();
        return new GoogleApiClient.Builder(context).enableAutoManage(context,listener).addApi(Auth.GOOGLE_SIGN_IN_API,googleSignInOptions).build();
    }
}
