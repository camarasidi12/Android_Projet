package com.example.sidicamara.projetppm_3673669;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.OnStreetViewPanoramaReadyCallback;
import com.google.android.gms.maps.StreetViewPanorama;
import com.google.android.gms.maps.StreetViewPanoramaFragment;
import com.google.android.gms.maps.SupportStreetViewPanoramaFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.StreetViewPanoramaCamera;
import com.google.android.gms.maps.model.StreetViewPanoramaLocation;
import com.google.android.gms.maps.model.StreetViewPanoramaOrientation;

/**
 * Created by sidicamara on 24/01/2018.
 */

public class MapStreatV extends Fragment implements OnStreetViewPanoramaReadyCallback {
    public StreetViewPanoramaFragment streetViewPanoramafragment;
    StreetViewPanorama streetViewPanorama;
    public Game game;
    public static final String NOVICE="novice";
    public static final String MEDIUM="medium";
    public static final String EXPERT="expert";

    public  static String level=NOVICE;

    // le changement de level ne depend que de la difficulté
    // à retrouver l'adresse dans le mapView
    static LatLng[] novice ={
            new LatLng(48.862725,2.287592),
            new LatLng(-33.87365, 151.20689),
            new LatLng(51.5073509,-0.12775829999998223),
            new LatLng(48.8464406,2.3551612999999634)} ;

    static LatLng[] medium ={
            new LatLng(29.7904488,31.209324400000014),
          //  new LatLng(-40.4319077, 116.57037489999993),
            new LatLng(43.1100855,6.297825999999986),
            new LatLng(33.96112399999999,-116.5016784),
            new LatLng(50.6481553,5.577350000000024)} ;


    static LatLng[] expert ={
            new LatLng(23.4162027,25.662829999999985),
            new LatLng(-23.8634189, -69.13284909999999),
            new LatLng(46.86249599999999,103.84665599999994),
            new LatLng(14.058324,108.277199)} ;

    static LatLng[] levelPositions=novice;

    private static final String MARKER_POSITION_KEY = "CurrentePosition";

    // George St, Sydney
    private static final LatLng SYDNEY = levelPositions[0];
    int currentPosition=0;
     LatLng markerPosition=SYDNEY;

    private Marker mMarker;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        if(level.equals(NOVICE))
            levelPositions=novice;
        else if(level.equals(MEDIUM))
            levelPositions=medium;
        else  levelPositions=expert;

        Log.i("ee","LEVEL"+level);

        if (savedInstanceState == null) {
            markerPosition = SYDNEY;
        } else {
            markerPosition = savedInstanceState.getParcelable(MARKER_POSITION_KEY);
        }

    }


    @Override
    public View onCreateView(LayoutInflater inf, ViewGroup vg , Bundle s){
        super.onCreateView(inf,vg, s);

        final View view = inf.inflate(R.layout.activity_streat, vg, false);

        SupportStreetViewPanoramaFragment sfrag = (SupportStreetViewPanoramaFragment)getChildFragmentManager()
                .findFragmentById(R.id.streatview);


        view.findViewById(R.id.streatview);
        if(sfrag != null) {
            sfrag.getStreetViewPanoramaAsync(this);
            Log.i("S","NOTNULLT");
        }


        return view;
    }



    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelable(MARKER_POSITION_KEY, novice[currentPosition]);
    }


public void  changePosition(){
    currentPosition=(currentPosition+1)% novice.length;

    if(currentPosition==0)
        game.gameIsOver();
    streetViewPanorama.setPosition(levelPositions[currentPosition]);
    markerPosition= levelPositions[currentPosition];
    Game.streatPosition=markerPosition;
}
    @Override
    public void onStreetViewPanoramaReady(StreetViewPanorama streetViewPanoram) {
        this.streetViewPanorama = streetViewPanoram;




        StreetViewPanoramaCamera.Builder builder = new StreetViewPanoramaCamera.Builder( streetViewPanorama.getPanoramaCamera() );
        builder.tilt( 0.0f );
        builder.zoom( 0.0f );
        builder.bearing( 0.0f );
        streetViewPanorama.animateTo( builder.build(), 0 );
        streetViewPanorama.setPosition( levelPositions[currentPosition]);
        streetViewPanorama.setStreetNamesEnabled( true );
        
        streetViewPanoram.setOnStreetViewPanoramaClickListener(new StreetViewPanorama.OnStreetViewPanoramaClickListener() {
            @Override
            public void onStreetViewPanoramaClick(StreetViewPanoramaOrientation streetViewPanoramaOrientation) {
                //changePosition();
            }
        });
    }

    public  LatLng getCurrentPosition(){
    return  markerPosition;
    }
}
