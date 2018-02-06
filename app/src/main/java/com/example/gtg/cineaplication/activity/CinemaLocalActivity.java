package com.example.gtg.cineaplication.activity;

import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.example.gtg.cineaplication.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class CinemaLocalActivity extends FragmentActivity implements OnMapReadyCallback, GoogleMap.OnMapLongClickListener, GoogleMap.OnInfoWindowClickListener {

    private GoogleMap mMap;
    private Marker currMarker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cinema_local);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
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
        mMap.setOnMapLongClickListener(this);
        mMap.setOnInfoWindowClickListener(this);
        // Add a marker in Sydney and move the camera
        LatLng ifma = new LatLng(-2.598936, -44.207454);
        mMap.moveCamera(CameraUpdateFactory.newLatLng(ifma));
        mMap.moveCamera(CameraUpdateFactory.zoomTo(10));
        Toast.makeText(this, "Toque e segure para selecionar", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onMapLongClick(LatLng latLng) {
        if ( currMarker != null )
            currMarker.remove();

        currMarker = mMap.addMarker(new MarkerOptions().position(latLng).title("Confirmar"));
        currMarker.showInfoWindow();
    }

    @Override
    public void onInfoWindowClick(Marker marker) {
        Intent it = new Intent();
        Bundle params = new Bundle();
        params.putString("lat",String.valueOf(marker.getPosition().latitude));
        params.putString("lng",String.valueOf(marker.getPosition().longitude));
        it.putExtras(params);
        setResult(3,it);
        finish();
    }
}
