package com.example.ronhfreeman.getbabysitter.activities;

import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.ronhfreeman.getbabysitter.R;
import com.example.ronhfreeman.getbabysitter.fragments.LoginFragment;
import com.facebook.FacebookSdk;

public class MainActivity extends AppCompatActivity implements LoginFragment.OnFragmentInteractionListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Set the facebook
        FacebookSdk.sdkInitialize(getApplicationContext());

        // Create and add the new fragment
        LoginFragment lgnFragment = new LoginFragment();
        getFragmentManager().beginTransaction().
                add(R.id.main, lgnFragment, lgnFragment.getClass().getName()).commit();
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
