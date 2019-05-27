package com.example.sulekhasurbhi.swasthya;

import android.app.Application;
import android.content.Intent;

import com.example.sulekhasurbhi.swasthya.Activity.HomeScreenActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Session extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        FirebaseUser mUser = mAuth.getCurrentUser();

        if(mUser !=null){
            startActivity(new Intent(Session.this, HomeScreenActivity.class));
        }
    }
}
