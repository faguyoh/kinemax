package com.example.collo.kinemax;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class HomeActivity extends AppCompatActivity implements View.OnClickListener {
    private ImageView profileActivity;
    private ImageView Trending;
    private ImageView Trailer;
    private ImageView Book;
    private ImageView Payment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        getSupportActionBar().hide();//Hiding title bar


        profileActivity=(ImageView) findViewById(R.id.ProfileActivity);
        Trending=(ImageView)findViewById(R.id.Trending);
        Trailer=(ImageView)findViewById(R.id.trailer);
        Book=(ImageView)findViewById(R.id.imgbk);
        Payment=(ImageView)findViewById(R.id.PaymentDetails);



        profileActivity.setOnClickListener(this);
        Trending.setOnClickListener(this);
        Trailer.setOnClickListener(this);
        Book.setOnClickListener(this);
        Payment.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        if(v == profileActivity) {
            Intent prof = new Intent(getApplicationContext(), ProfileActivity.class);
            startActivity(prof);
        }
        if(v == Trending) {
            Intent ahha= new Intent(getApplicationContext(), TrendingActivity.class);
            startActivity(ahha);
        }

        if(v == Trailer) {
            Intent trai= new Intent(getApplicationContext(), VideoActivity.class);
            startActivity(trai);
        }

        if(v == Book) {
            Intent bk= new Intent(getApplicationContext(), BookingActivity.class);
            startActivity(bk);
        }
        if(v == Payment) {
            Intent jk= new Intent(getApplicationContext(), PaymentActivity.class);
            startActivity(jk);
        }



    }
}
