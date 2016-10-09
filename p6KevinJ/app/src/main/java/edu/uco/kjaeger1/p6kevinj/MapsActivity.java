package edu.uco.kjaeger1.p6kevinj;

import android.app.Activity;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.HashMap;

import static edu.uco.kjaeger1.p6kevinj.R.id.map;

public class MapsActivity extends Activity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private HashMap<String,String> cityInfo = new HashMap<>();
    double cityLatitude;
    double cityLongitude;
    String cityName;
    String cityTemperature;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        MapFragment mapFragment = (MapFragment) getFragmentManager()
                .findFragmentById(map);
        mapFragment.getMapAsync(this);

        cityInfo = (HashMap<String, String>)getIntent().getSerializableExtra("CityWeatherHashMap");
        cityLatitude = Double.parseDouble(cityInfo.get("Latitude"));
        cityLongitude = Double.parseDouble(cityInfo.get("Longitude"));
        cityName = cityInfo.get("City");
        cityTemperature = cityInfo.get("Temp");


    }


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
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng selectedCity = new LatLng(cityLatitude, cityLongitude);
        mMap.addMarker(new MarkerOptions()
                .position(selectedCity)
                .title(cityName)
                .snippet("Current Temp: " + cityTemperature + (char) 0x00B0 + "F"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(selectedCity));
        mMap.setMinZoomPreference(5.0F);
    }
}
