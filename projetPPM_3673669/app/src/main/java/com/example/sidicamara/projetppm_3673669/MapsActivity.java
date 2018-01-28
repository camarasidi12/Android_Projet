package com.example.sidicamara.projetppm_3673669;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends Fragment implements OnMapReadyCallback {

    MapView mapView;
    GoogleMap googleMap;
    MapView mapview;
    boolean ok=false;
    private SupportMapFragment mapFragment;
    private Marker marker;
    public  Game game;


    @Override
    public View onCreateView(LayoutInflater inf, ViewGroup vg , Bundle s){
        super.onCreateView(inf,vg, s);


        View view= inf.inflate(R.layout.activity_maps,vg,false);

        if (mapFragment == null) {
            mapFragment = SupportMapFragment.newInstance();
            mapFragment.getMapAsync(this);
        }

        // R.id.map is a FrameLayout, not a Fragment

        getChildFragmentManager().beginTransaction().replace(R.id.map, mapFragment).commit();


        return view;
    }


    public  void changePlace(){

        googleMap.moveCamera(CameraUpdateFactory.newLatLng(new LatLng(-39, 251)));
       // googleMap.moveCamera(CameraUpdateFactory.newLatLng(position));

    }
    public void addMarker(LatLng mrk){
        if(marker!=null)
        marker.remove();
        marker=googleMap.addMarker(new MarkerOptions().position(mrk).title(""));
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(mrk));
    }
    /**


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        this.googleMap = googleMap;
        googleMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {
                Log.i("sidi","clecked");
            }
        });
        // Add a marker in Sydney and move the camera
     /*   LatLng sydney = new LatLng(-34, 151);
        googleMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
        ok=true;*/
        googleMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {
               addMarker(latLng);
                game.getMapPosition(latLng);

            }
        });

    }
}
