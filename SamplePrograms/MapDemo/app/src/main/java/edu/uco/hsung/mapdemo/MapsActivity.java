package edu.uco.hsung.mapdemo;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends Activity implements OnMapReadyCallback {

    private GoogleMap map;
    private Button csButton;
    private Button libButton;
    private LatLng csPosition = new LatLng(35.653988, -97.472842);
    private LatLng libPosition = new LatLng(35.658354, -97.473751);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        csButton = (Button) findViewById(R.id.csButton);
        libButton = (Button) findViewById(R.id.libButton);

        csButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (map != null) {
                    CameraPosition camera = new CameraPosition.Builder()
                            .target(csPosition).zoom(18).build();

                    map.animateCamera(CameraUpdateFactory.newCameraPosition(camera));
                    map.clear(); // clear all markers if any
                    map.addMarker(
                            new MarkerOptions().position(csPosition).title("CS, UCO")
                    );
                }
            }
        });

        libButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (map != null) {
                    CameraPosition camera = new CameraPosition.Builder()
                            .target(libPosition).zoom(18).build();

                    map.animateCamera(CameraUpdateFactory.newCameraPosition(camera));
                    map.clear();
                    map.addMarker(
                            new MarkerOptions().position(libPosition).title("Library")
                    );
                }
            }
        });


        MapFragment mapFragment = (MapFragment) getFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap map) {

        this.map = map;
        // move the camera to CS building
        CameraPosition camera = new CameraPosition.Builder()
                .target(csPosition).zoom(16).build();

        map.getUiSettings().setZoomControlsEnabled(true); // (+) (-) zoom control bar
        map.animateCamera(CameraUpdateFactory.newCameraPosition(camera));


    }
}