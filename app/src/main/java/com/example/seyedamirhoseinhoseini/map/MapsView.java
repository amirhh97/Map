package com.example.seyedamirhoseinhoseini.map;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.pm.PackageManager;
import android.graphics.BitmapFactory;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.ViewGroup;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;


public class MapsView extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    boolean LocationPermission = false;
    ViewGroup parent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.maps_view);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        parent = findViewById(R.id.parent);
        mapFragment.getMapAsync(this);
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED && ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED)
            LocationPermission = true;
        else
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 0);


    }

    @SuppressLint("MissingPermission")
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.setInfoWindowAdapter(new MyInfoWindowAdapter(this, parent));
        mMap.setMyLocationEnabled(true);
        mMap.getUiSettings().setMyLocationButtonEnabled(true);
        //  getLocation();
        for (int i = 0; i < 9; i++)
            addMarker(Math.random() * 100, Math.random() * 100, "Tehran");
        addMarker(35.7, 51.4, "tehran");


    }

    public void addMarker(double lat, double lng, String title) {
        LatLng marker = new LatLng(lat, lng);
        mMap.addMarker(new MarkerOptions().snippet("My city").title(title).position(marker)).setIcon(BitmapDescriptorFactory.fromResource(R.mipmap.ic_marker));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(marker, 10.5f));
    }

    public void getLocation() {
        if (LocationPermission) {
            @SuppressLint("MissingPermission") Task<Location> location = LocationServices.getFusedLocationProviderClient(this).getLastLocation();
            location.addOnCompleteListener(new OnCompleteListener<Location>() {
                @Override
                public void onComplete(@NonNull Task<Location> task) {
                    if (task.isSuccessful()) {
                        Location l = task.getResult();
                        addMarker(l.getAltitude(), l.getLongitude(), "Home");

                    } else {
                        Log.d("msg", "Current location is null. Using defaults.");
                        Log.e("msg", "Exception: %s", task.getException());
                    }
                }
            });

        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED)
            LocationPermission = true;

    }


}
