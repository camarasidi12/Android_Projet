package com.example.sidicamara.projetppm_3673669;

import android.app.ActionBar;
import android.app.ListActivity;
import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;

import static com.example.sidicamara.projetppm_3673669.R.layout.content_statistics;

public class Statistics extends  ListActivity {

SimpleAdapter adapter;
ArrayList<HashMap<String,String>> novice=new ArrayList<HashMap<String,String>>();
ArrayList<HashMap<String,String>> medium=new ArrayList<HashMap<String,String>>();
ArrayList<HashMap<String,String>> expert=new ArrayList<HashMap<String,String>>();
ArrayList<HashMap<String,String>> data=new ArrayList<HashMap<String,String>>();

;
public  static String[] trieSelect={"date","level","score","default"};
public  static  String trieChose = trieSelect[0];

ArrayList<MetierScore>scores;
Button trieDate;
Button triePartie;
Button trieScore;

    MetierDAO bdd;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.i("r","create");
        View view;
        LayoutInflater inflater = (LayoutInflater)  getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = inflater.inflate(R.layout.content_statistics, null);
        super.onCreate(savedInstanceState);
        bdd=new MetierDAO(this);
        bdd.open();
    scores=bdd.selectionnerUserScore(MainActivity.user.getId());
    Log.i("s","SCOREleng="+scores.size());
    bdd.close();
       loadData(trieChose);

        Log.i("r","createa");

        adapter=new SimpleAdapter(this,data, content_statistics,
                new String[]{"score","level","date"},new int[]{R.id.score,R.id.level,R.id.date});
        setListAdapter(adapter);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        // Inflate the menu; this adds items to the action        // bar if it is present.

        Log.i("h","MENUCREAT");
        getMenuInflater().inflate(R.menu.menu_statistics, menu);
        return true;

    }



    public  void loadData(String trieChoose){
        if(!trieChoose.equals(trieSelect[3]))
        trie(trieChoose);
         for(MetierScore m :scores)
            addItem(m.getScore(), m.getDate(), m.getLevel());
    }

    public  void addItem(String score,String date,String level){
        HashMap<String,String> item= new HashMap<String,String>();
        item.put("level",level);
        item.put("score",score);
        item.put("date",date);
        data.add(item);
    }

    public  void trie(final String trie){


        Collections.sort(scores, new Comparator<MetierScore>() {
            @Override
            public int compare(MetierScore o1, MetierScore o2) {
                if(trieChose.equals(trieSelect[2])) {

                    if(Integer.parseInt(o2.getScore())>Integer.parseInt(o1.getScore()))
                        return 1;
                        else return -1;
                }else if(trieChose.equals(trieSelect[1])) {
                    return o1.getLevel().compareTo(o2.getLevel());
                }else  {
                    return o2.getDate().compareTo(o1.getDate());
                }

            }
        });
    }


}
