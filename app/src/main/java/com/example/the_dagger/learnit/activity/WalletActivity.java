package com.example.the_dagger.learnit.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.the_dagger.learnit.R;

public class WalletActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wallet);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
}
