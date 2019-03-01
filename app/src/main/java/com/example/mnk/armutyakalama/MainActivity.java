package com.example.mnk.armutyakalama;



import android.content.DialogInterface;
import android.content.Intent;
import android.opengl.Visibility;
import android.os.CountDownTimer;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.*;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    int t,d;
    TextView score,sure;
    ImageView bir,iki,uc,dort,bes,alti,yedi,sekiz,dokuz;
    Handler handler;
    ImageView[] image;
    Runnable runnable;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        score = findViewById(R.id.scoreText);
        sure= findViewById(R.id.kalanSureText);
        bir = findViewById(R.id.imageView1);
        iki = findViewById(R.id.imageView2);
        uc = findViewById(R.id.imageView3);
        dort = findViewById(R.id.imageView4);
        bes = findViewById(R.id.imageView5);
        alti = findViewById(R.id.imageView6);
        yedi = findViewById(R.id.imageView7);
        sekiz = findViewById(R.id.imageView8);
        dokuz = findViewById(R.id.imageView);
        image =new ImageView[]{bir,iki,uc,dort,bes,alti,yedi,sekiz,dokuz};

        resimsakla();

        new CountDownTimer(20000,1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                sure.setText("Kalan Süre=" + millisUntilFinished/1000);
            }
            @Override
            public void onFinish() {
                sure.setText("SÜRE BİTTİ!");
                handler.removeCallbacks(runnable);
                for (ImageView image : image) {
                    image.setVisibility(View.INVISIBLE);
                }

                AlertDialog.Builder dialog = new AlertDialog.Builder(MainActivity.this);
                dialog.setTitle("Bitti");
                dialog.setMessage("Yeniden Oynamak İster misin?");
                dialog.setPositiveButton("evet", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent i = getIntent();
                        finish();
                        startActivity(i);
                    }
                });
                dialog.setNegativeButton("hayır", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(MainActivity.this, "Oyun Bitti", Toast.LENGTH_SHORT).show();
                    }
                });dialog.show();
            }
        }.start();



    }


    public void tiklandi(View v)
    {
        t++;
        score.setText("Score =" + t);
    }
    public void resimsakla(){
        handler = new Handler();
        runnable = new Runnable() {
            @Override
            public void run() {
                for (ImageView image : image)
                {
                    image.setVisibility(View.INVISIBLE);
                }
                Random rnd = new Random();
                int i = rnd.nextInt(9);
                image[i].setVisibility(View.VISIBLE);
                handler.postDelayed(this,500);
            }
        };handler.post(runnable);
    }

}
