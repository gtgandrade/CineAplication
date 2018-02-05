package com.example.gtg.cineaplication.activity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.location.Location;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.gtg.cineaplication.DAO.CinemaDAO;
import com.example.gtg.cineaplication.R;
import com.example.gtg.cineaplication.modelo.Cinema;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.maps.android.SphericalUtil;
import com.google.maps.android.ui.IconGenerator;

import java.util.ArrayList;
import java.util.List;

public class CinemasProximosActivity extends FragmentActivity implements OnMapReadyCallback, GoogleMap.OnInfoWindowClickListener {

    private LatLng currLocation;
    private GoogleMap mMap;
    private FusedLocationProviderClient mFusedLocationClient;
    private LatLngBounds.Builder builder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this);

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
        builder = new LatLngBounds.Builder();

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            mMap.setMyLocationEnabled(true);

            mFusedLocationClient.getLastLocation()
                    .addOnSuccessListener(this, new OnSuccessListener<Location>() {
                        @Override
                        public void onSuccess(Location location) {
                            // Got last known location. In some rare situations this can be null.
                            if (location != null) {
                                currLocation = new LatLng( location.getLatitude(),location.getLongitude() );
                                loadMarkersByCurrLocation();
                            }
                        }
                    });
        } else {
            Toast.makeText(this, "Permissão não concedida!", Toast.LENGTH_SHORT).show();
        }

    }

    private void loadMarkersByCurrLocation()
    {
        Marker mCurr = mMap.addMarker(new MarkerOptions().position(currLocation).title("Meu Local"));
        mCurr.showInfoWindow();
        mCurr.setTag(0);
        builder.include(currLocation);

        CinemaDAO cinemas = new CinemaDAO(this);
        List<Cinema> lista = cinemas.carregarcinemas();

        for (Cinema cinema : lista)
        {
            LatLng local = new LatLng(cinema.getLatitude(),cinema.getLongitude());
            double dist = SphericalUtil.computeDistanceBetween(currLocation,local);
            if (dist < 10000)
            {
                IconGenerator ig = new IconGenerator(this);
                ig.setStyle(IconGenerator.STYLE_BLUE);
                Bitmap bm = ig.makeIcon(cinema.getNome());
                Marker m = mMap.addMarker(new MarkerOptions()
                        .position(local)
                        .title(cinema.getEndereco())
                        .snippet("Clique para ver filmes")
                        .icon(BitmapDescriptorFactory.fromBitmap(bm)));
                m.setTag(cinema.getId());
                builder.include(m.getPosition());
            }
        }

        mMap.moveCamera(CameraUpdateFactory.newLatLngBounds(builder.build(),150));
        mMap.setOnInfoWindowClickListener(this);

    }

    @Override
    public void onInfoWindowClick(Marker marker) {

        Intent intentFilme = new Intent(this, FilmeActivity.class);
        Bundle params = new Bundle();
        params.putInt("idcinema", (Integer) marker.getTag());
        intentFilme.putExtras(params);
        startActivity(intentFilme);

    }
}
