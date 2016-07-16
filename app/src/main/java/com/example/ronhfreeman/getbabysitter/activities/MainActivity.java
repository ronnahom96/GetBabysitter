package com.example.ronhfreeman.getbabysitter.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.ronhfreeman.getbabysitter.R;
import com.example.ronhfreeman.getbabysitter.fragments.LoginFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportFragmentManager().beginTransaction().add(R.id.main , new LoginFragment(), LoginFragment.class.getName());
    }
}
