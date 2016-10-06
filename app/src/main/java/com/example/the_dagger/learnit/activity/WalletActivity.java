package com.example.the_dagger.learnit.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.example.the_dagger.learnit.R;
import com.example.the_dagger.learnit.paytm.Paytm;
import com.example.the_dagger.learnit.utility.SuperPrefs;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class WalletActivity extends AppCompatActivity {

    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wallet);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        textView = (TextView) findViewById(R.id.currentBalance);

        textView.setText(SuperPrefs.newInstance(WalletActivity.this).getInt("balance", 0) + " ₹");
        String access_token = SuperPrefs.newInstance(WalletActivity.this).getString("child_access_token");

        Map<String, String> header = new HashMap<>();
        header.put("ssotoken", access_token);

        Paytm paytm = Paytm.getInstance();
        paytm.checkBalance(header, new JSONObject(), new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONObject res = response.getJSONObject("response");
                    int balance = res.getInt("amount");
                    SuperPrefs.newInstance(WalletActivity.this).setInt("balance", balance);
                    textView.setText(balance + " ₹");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(WalletActivity.this, "Error getting wallet amount", Toast.LENGTH_LONG).show();
            }
        });


    }
}
