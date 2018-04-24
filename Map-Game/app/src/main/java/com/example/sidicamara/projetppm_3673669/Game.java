package com.example.sidicamara.projetppm_3673669;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.location.Address;
import android.location.Geocoder;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;

import java.net.URI;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class Game extends AppCompatActivity {
MapsActivity mapsActivity;
    LatLng mapPosition;
    MapStreatV street;
    Polyline line;
    public static LatLng streatPosition=null;
    private  boolean isGameOver=false;
    String level;
    Integer  averageScore=0;
    //une constante utili lors de clacul des resultats
    private Integer QUOTIENTSCORE=1000098760;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        FragmentTransaction fragmentTransactiont = getSupportFragmentManager().beginTransaction();

        mapsActivity=new MapsActivity();
        mapsActivity.game =this;
        fragmentTransactiont.add(R.id.mapmap,mapsActivity);

        level=getIntent().getStringExtra("level");
        this.street=new MapStreatV();
        this.street.level=level;
        streatPosition=street.getCurrentPosition();
        street.game =this;
        fragmentTransactiont.add(R.id.ville, street);
      fragmentTransactiont.commit();


    }

    public  void getMapPosition(LatLng position){
        this.mapPosition=position;

        //traçage entre la position sur streatview et le map
        if(line!=null)
            line.remove();
         line =mapsActivity.googleMap.addPolyline(new PolylineOptions()
                .add(streatPosition, position)
                .width(5)
                .color(Color.RED));

         //tant que le jeu n'est pas fini on accumule
        // le score intermedaire
        averageScore+=computeScore(distance());

        new AlertDialog.Builder(this)
                .setMessage("You are "+distance()+" KM away")
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        street.changePosition();
                        streatPosition=street.getCurrentPosition();
                    }
                }).show();

        if(isGameOver){
            isGameOver=false;
            AlertDialog al=
            new AlertDialog.Builder(this)
                    .setTitle("Congretulation")
                    .setMessage("You finished the game Score final! (score-"+gameOver()+")")
                    .setNegativeButton("share", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
       //Partage du score
                            Intent share = new Intent(Intent.ACTION_SEND);
                            share.setType("text/plain");
                            share.putExtra(Intent.EXTRA_TEXT, "My score: "+gameOver());
                            startActivity(Intent.createChooser(share,
                                    "En partage du score"));

                        }
                    })
                    .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            startUserActivity();

                        }
                    }).show();

        }

    }

    public  void startUserActivity(){
        Intent in =new Intent(getApplicationContext(),UserActivity.class);
        startActivity(in);
    }

    //Calcul du distance entre le deux points
    private static double deg2rad(double deg) {
        return (deg * Math.PI / 180.0);
    }
    private static double rad2deg(double rad) {
        return (rad * 180 / Math.PI);
    }
    public  double distance() {

        String unit="K";
        double latMap=mapPosition.latitude;
        double longMap=mapPosition.longitude;

        double latStr=streatPosition.latitude;
        double longStr=streatPosition.longitude;

        double theta = longMap - longStr;
        double dist =
                Math.sin(deg2rad(latMap)) * Math.sin(deg2rad(latStr)) + Math.cos(deg2rad(latMap))
                        * Math.cos(deg2rad(latStr)) * Math.cos(deg2rad(theta));
        dist = Math.acos(dist);
        dist = rad2deg(dist);
        dist = dist * 60 * 1.1515;
        if (unit.equals("K")) {
            dist = dist * 1.609344;
        } else if (unit == "N") {
            dist = dist * 0.8684;
        }

        return (dist);
    }



    public  int computeScore(double distance){
        if(distance<500)
            return 50000+((Double)(QUOTIENTSCORE/distance)).intValue();
        else if(distance>50 && distance <500)
            return 5000+((Double)(QUOTIENTSCORE/distance)).intValue();
            else
                return 500+((Double)(QUOTIENTSCORE/distance)).intValue();
    }

    // On stockera a la volé le score ainsi que
    // ses informations dans la base (sans oublier ID du user
    public  double gameOver(){
        MetierDAO bdd=new MetierDAO(this);
        bdd.open();
        averageScore=averageScore/5;
        String timeStamp = new SimpleDateFormat("dd/MM/yyyy-HH:mm").format(Calendar.getInstance().getTime());
        MetierScore scr=new MetierScore(averageScore.toString(),level,timeStamp);
        scr.setUserId(MainActivity.user.getId());
        Log.i("d","USER="+MainActivity.user.getNom()+"ID+"+MainActivity.user.getId());
        Log.i("d","AJOUTER="+bdd.ajouter(scr));
        bdd.close();
        return averageScore;
    }

    public  void gameIsOver(){
         this.isGameOver=true;
    }



}










