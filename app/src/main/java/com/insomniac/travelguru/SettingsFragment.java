package com.insomniac.travelguru;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.preference.PreferenceFragmentCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Sanjeev on 3/16/2018.
 */

public class SettingsFragment extends PreferenceFragmentCompat{

    public static Fragment createFragment(Context context){
        return new SettingsFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_files,container,false);
        return view;
    }


    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {

    }
}
