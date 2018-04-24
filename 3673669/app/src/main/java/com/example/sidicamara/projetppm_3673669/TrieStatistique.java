package com.example.sidicamara.projetppm_3673669;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

public class TrieStatistique extends AppCompatActivity implements View.OnClickListener{

    Button datetri;
    Button scoretri;
    Button partitri;
    Button defaulttri;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trie_statistique);

        datetri=(Button)findViewById(R.id.datetrie);
        scoretri=(Button)findViewById(R.id.triescore);
        partitri=(Button)findViewById(R.id.triepartie);
        defaulttri=(Button)findViewById(R.id.defaulttrie);

        scoretri.setOnClickListener(this);
        datetri.setOnClickListener(this);
        partitri.setOnClickListener(this);
        defaulttri.setOnClickListener(this);


    }
//ON selection la maniere a trier les tatistiques
    @Override
    public void onClick(View v) {
        if(v.getId()==datetri.getId()){
            Statistics.trieChose=Statistics.trieSelect[0];
        }else  if(v.getId()==scoretri.getId()){
            Statistics.trieChose=Statistics.trieSelect[2];
        }else  if(v.getId()==partitri.getId()){
            Statistics.trieChose=Statistics.trieSelect[1];
        }else  if(v.getId()==defaulttri.getId()){
            Statistics.trieChose=Statistics.trieSelect[3];
        }

        Intent intent= new Intent(this,Statistics.class);
        startActivity(intent);
    }


}

















