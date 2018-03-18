package com.sayyidpratama.sayyidpratamashiddiq_1202150066_modul4;

import android.content.Intent;

import android.support.v7.app.AppCompatActivity;

import android.os.Bundle;

import android.view.View;

import android.widget.Button;



public class MainActivity extends AppCompatActivity {

    Button LM, IMG; Intent move;

    @Override

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);



        LM = (Button)findViewById(R.id.listNamaBtn);

        IMG = (Button)findViewById(R.id.GambarBtn);



        LM.setOnClickListener(new View.OnClickListener() {

            @Override

            public void onClick(View view) {

                move = new Intent(MainActivity.this, ListNamaMahasiswa.class);

                startActivity(move);

            }

        });



        IMG.setOnClickListener(new View.OnClickListener() {

            @Override

            public void onClick(View view) {

                move = new Intent(MainActivity.this, CariGambar.class);

                startActivity(move);

            }

        });



    }

}