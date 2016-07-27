package com.example.ronhfreeman.getbabysitter.activities;

import android.content.Context;
import android.content.SharedPreferences;
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

        final String CURRENT_USER_ID = "currentUserId";

        // Set the facebook
        FacebookSdk.sdkInitialize(getApplicationContext());

        // Create the shared preferences
        SharedPreferences sharedPref = getPreferences(Context.MODE_PRIVATE);

        // Get the value of the user that login from the shared preferences
        // And set it zero if it does not a logged user
        int currUserId = sharedPref.getInt(CURRENT_USER_ID, 0);

        // Check if the user is logged
        if (currUserId == 0) {
            // Create and add a login fragment
            LoginFragment lgnFragment = new LoginFragment();
            getFragmentManager().beginTransaction().
                    add(R.id.main, lgnFragment, lgnFragment.getClass().getName()).commit();
        }
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
