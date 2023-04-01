package com.shashank.platform.schoolcollegeapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.shashank.platform.schoolcollegeapp.databinding.ActivityMapsBinding;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback

{

    private GoogleMap mMap;
    private ImageButton toggle;
    private ActivityMapsBinding binding;
    private FusedLocationProviderClient fusedLocationProviderClient;
    private static final int Request_code=101;
    private double lat, lng;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMapsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        ImageButton logout = findViewById(R.id.logout);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logoutUser();
            }
        });



        toggle = findViewById(R.id.toggle);
        fusedLocationProviderClient= LocationServices.getFusedLocationProviderClient(this.getApplicationContext());
        toggle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MapsActivity.this,MenuActivty.class);
                startActivity(intent);

            }
        });
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
        mMap.getUiSettings().setCompassEnabled(true);
        mMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);

        getCurrentLocation();


        LatLng EVstation1 = new LatLng(23.01400504137487, 72.4967319455628);
        mMap.addMarker(new MarkerOptions().position(EVstation1).title("Charge And Drive Charging Station"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(EVstation1));

        LatLng EVstation2 = new LatLng(23.08877247163549, 72.59169098326129);
        mMap.addMarker(new MarkerOptions().position(EVstation2).title("Capital EV Charging Station"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(EVstation2));

        LatLng EVstation3 = new LatLng(23.11151, 72.52440);
        mMap.addMarker(new MarkerOptions().position(EVstation3).title("GOEV"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(EVstation3));

        LatLng EVstation4 = new LatLng(23.05641, 72.51164);
        mMap.addMarker(new MarkerOptions().position(EVstation4).title("CHARGE+ZONE Charging Station"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(EVstation4));

        LatLng EVstation5 = new LatLng(23.026736643971816, 72.60537450233431);
        mMap.addMarker(new MarkerOptions().position(EVstation5).title("Electric Vehicle Charging Station"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(EVstation5));

        LatLng EVstation6 = new LatLng(23.071810111703915, 72.5228752569507);
        mMap.addMarker(new MarkerOptions().position(EVstation6).title("Indian Oil Petroleum EV station"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(EVstation6));

        LatLng EVstation7 = new LatLng(23.00153166714238, 72.62614741974);
        mMap.addMarker(new MarkerOptions().position(EVstation7).title("Laxmi Narayan Society Ev station"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(EVstation7));

        LatLng EVstation8 = new LatLng(23.179221817291026, 72.68426894125763);
        mMap.addMarker(new MarkerOptions().position(EVstation8).title("MobiLane Charging Station"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(EVstation8));

        LatLng EVstation9 = new LatLng(23.039571852261904, 72.47030679637287);
        mMap.addMarker(new MarkerOptions().position(EVstation9).title("Griden Power EV station"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(EVstation9));

        LatLng EVstation10 = new LatLng(23.056405883700144, 72.55025981187066);
        mMap.addMarker(new MarkerOptions().position(EVstation10).title("Petroleum House EV Charging Station"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(EVstation10));





    }




    private void getCurrentLocation(){
        if(ActivityCompat.checkSelfPermission(
                this, Manifest.permission.ACCESS_FINE_LOCATION)!= PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission
                (this, Manifest.permission.ACCESS_COARSE_LOCATION)!= PackageManager.PERMISSION_GRANTED){

            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, Request_code);
            return;
        }

        LocationRequest locationRequest = LocationRequest.create();
        locationRequest.setInterval(60000);
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        locationRequest.setFastestInterval(5000);

        LocationCallback locationCallback = new LocationCallback(){
            @Override
            public void onLocationResult(LocationResult locationResult) {
                super.onLocationResult(locationResult);
                if(locationResult== null){
                    Toast.makeText(getApplicationContext(),"Current Location is null", Toast.LENGTH_LONG).show();
                    return;
                }
                for (Location location:locationResult.getLocations()){
                    if(location!=null){
                        Toast.makeText(getApplicationContext(),"Current Location is "+location.getLongitude(), Toast.LENGTH_LONG).show();
                    }
                }
            }
        };

        fusedLocationProviderClient.requestLocationUpdates
                (locationRequest,locationCallback,null);

        Task<Location> task = fusedLocationProviderClient.getLastLocation();
        task.addOnSuccessListener(new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(Location location) {
                if(location!=null){
                    lat = location.getLatitude();
                    lng = location.getLongitude();

                    LatLng latLng = new LatLng(lat,lng);
                    mMap.addMarker(new MarkerOptions().position(latLng).title("Current Location"));
                    mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
                    mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng,12));
                }
            }
        });

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (Request_code){

            case Request_code:
                if(grantResults.length>0 && grantResults[0]==PackageManager.PERMISSION_GRANTED){
                    getCurrentLocation();
                }


        }
    }

    private void logoutUser(){
        FirebaseAuth.getInstance().signOut();
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
        finish();
    }
}