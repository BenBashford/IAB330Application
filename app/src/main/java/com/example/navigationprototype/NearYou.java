package com.example.navigationprototype;

import static android.location.Location.distanceBetween;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.navigationprototype.DB.Service;
import com.example.navigationprototype.DB.ServiceDAO;
import com.example.navigationprototype.Utils.MyApp;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MapStyleOptions;
import com.google.android.gms.maps.model.MarkerOptions;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.libraries.places.api.Places;
import com.google.android.libraries.places.api.net.PlacesClient;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class NearYou extends AppCompatActivity implements OnMapReadyCallback {

    private static final String TAG = NearYou.class.getSimpleName();
    private GoogleMap map;
    private CameraPosition cameraPosition;

    // The entry point to the Places API.
    private PlacesClient placesClient;

    // The entry point to the Fused Location Provider.
    private FusedLocationProviderClient fusedLocationProviderClient;

    // A default location and default zoom to use when location permission is
    // not granted.
    private final LatLng defaultLocation = new LatLng(-27.4773, 153.0270);
    private static final int DEFAULT_ZOOM = 14;
    private static final int PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION = 1;
    private boolean locationPermissionGranted;

    // The geographical location where the device is currently located. That is, the last-known
    // location retrieved by the Fused Location Provider.
    private Location lastKnownLocation;

    // Keys for storing activity state.
    private static final String KEY_CAMERA_POSITION = "camera_position";
    private static final String KEY_LOCATION = "location";


    private ImageView homeButton;
    private ImageView criticalButton;
    private ImageView nearbyButton;
    private ImageView settingsButton;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_near_you);
        setViewIds();

        // Retrieve location and camera position from saved instance state.
        if (savedInstanceState != null) {
            lastKnownLocation = savedInstanceState.getParcelable(KEY_LOCATION);
            cameraPosition = savedInstanceState.getParcelable(KEY_CAMERA_POSITION);
        }

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        // Construct a PlacesClient
        Places.initialize(getApplicationContext(), getString(R.string.maps_api_key));
        placesClient = Places.createClient(this);

        // Construct a FusedLocationProviderClient.
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);




        homeButton.setOnClickListener(view -> {
            Intent intent = new Intent(this,
                    Landing.class);
            startActivity(intent);
        });
        criticalButton.setOnClickListener(view -> {
            Intent intent = new Intent(this,
                    CriticalServices.class);
            startActivity(intent);
        });
        nearbyButton.setOnClickListener(view -> {
            Intent intent = new Intent(this,
                    NearYou.class);
            startActivity(intent);
        });
        settingsButton.setOnClickListener(view -> {
            Intent intent = new Intent(this,
                    Settings.class);
            startActivity(intent);
        });

    }



    private void getLocationPermission() {
        /*
         * Request location permission, so that we can get the location of the
         * device. The result of the permission request is handled by a callback,
         * onRequestPermissionsResult.
         */
        if (ContextCompat.checkSelfPermission(this.getApplicationContext(),
                android.Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            locationPermissionGranted = true;
        } else {
            ActivityCompat.requestPermissions(this,
                    new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION},
                    PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        locationPermissionGranted = false;
        if (requestCode
                == PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION) {// If request is cancelled, the result arrays are empty.
            if (grantResults.length > 0
                    && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                locationPermissionGranted = true;
            }
        } else {
            super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
        updateLocationUI();
    }

    public class DistanceServicePair implements Comparable<DistanceServicePair> {
        private float distance;
        private Service service;

        public DistanceServicePair(float distance, Service service) {
            this.distance = distance;
            this.service = service;
        }

        public float getDistance() {
            return distance;
        }

        public Service getService() {
            return service;
        }

        @Override
        public int compareTo(DistanceServicePair other) {
            // Compare by distance
            return Float.compare(this.distance, other.distance);
        }
    }

    @Override
    public void onMapReady(GoogleMap map) {
        this.map = map;

        boolean success = map.setMapStyle(new MapStyleOptions(getResources()
                .getString(R.string.style_json)));

        if (!success) {
            Log.e(TAG, "Style parsing failed.");
        }

        ServiceDAO serviceDAO = MyApp.getAppDatabase().serviceDao();
        serviceDAO.getAllServices().observe(this, services -> {
            // Update the map with the new list of services

            List<DistanceServicePair> distanceServicePairs = new ArrayList<>();
            for (Service service : services) {

                    Location userLocation = new Location("UserLocation");
                    userLocation.setLatitude(-27.4773);
                    userLocation.setLongitude(153.0270);

                    Location serviceLocation = new Location("ServiceLocation");
                    serviceLocation.setLatitude(service.getLatitude());
                    serviceLocation.setLongitude(service.getLongitude());

                    float distance = userLocation.distanceTo(serviceLocation);
                    distanceServicePairs.add(new DistanceServicePair(distance, service));

            }

                // Sort the list by distance
                Collections.sort(distanceServicePairs);

                // Create a list to store the three closest services
                List<Service> closestServices = new ArrayList<>();

                // Get the three closest services
                for (int i = 0; i < Math.min(3, distanceServicePairs.size()); i++) {
                    closestServices.add(distanceServicePairs.get(i).getService());
                }

                // Add a marker for each service
                for (Service service : closestServices) {
                    LatLng serviceLocation = new LatLng(service.getLatitude(), service.getLongitude());
                    map.addMarker(new MarkerOptions()
                                    .position(serviceLocation)
                                    .title(service.getName())
                            // Add additional information as needed
                );
            }
            TextView ServiceName1 = findViewById(R.id.ServiceName1);
            TextView Distance1 = findViewById(R.id.Distance1);
            TextView Description1 = findViewById(R.id.Description1);
            TextView ServiceName2 = findViewById(R.id.ServiceName2);
            TextView Distance2 = findViewById(R.id.Distance2);
            TextView Description2 = findViewById(R.id.Description2);
            TextView ServiceName3 = findViewById(R.id.ServiceName3);
            TextView Distance3 = findViewById(R.id.Distance3);
            TextView Description3 = findViewById(R.id.Description3);

            for (int i = 0; i < closestServices.size(); i++) {
                String serviceName = closestServices.get(i).getName();
                String distance = String.valueOf(distanceServicePairs.get(i).getDistance());
                String description = closestServices.get(i).getDescription();

                if (i == 0) {
                    ServiceName1.setText(Html.fromHtml("<b>" + serviceName + "</b>"));
                    Distance1.setText(Html.fromHtml("<b>Distance:</b><br>" + distance + "m"));
                    Description1.setText(Html.fromHtml("<b>Description</b>:<br>" + description));
                } else if (i == 1) {
                    ServiceName2.setText(Html.fromHtml("<b>" + serviceName + "</b>"));
                    Distance2.setText(Html.fromHtml("<b>Distance:</b><br>" + distance + "m"));
                    Description2.setText(Html.fromHtml("<b>Description</b>:<br>" + description));
                } else if (i == 2) {
                    ServiceName3.setText(Html.fromHtml("<b>" + serviceName + "</b>"));
                    Distance3.setText(Html.fromHtml("<b>Distance:</b><br>" + distance + "m"));
                    Description3.setText(Html.fromHtml("<b>Description</b>:<br>" + description));
                }

                // Update the TextViews based on i (0, 1, 2)
            }

        });

        // ...

        // Turn on the My Location layer and the related control on the map.
        updateLocationUI();

        // Get the current location of the device and set the position of the map.
        getDeviceLocation();
    }



    private void updateLocationUI() {
        if (map == null) {
            return;
        }
        try {
            if (locationPermissionGranted) {
                map.setMyLocationEnabled(true);
                map.getUiSettings().setMyLocationButtonEnabled(true);
            } else {
                map.setMyLocationEnabled(false);
                map.getUiSettings().setMyLocationButtonEnabled(false);
                lastKnownLocation = null;
                getLocationPermission();
            }
        } catch (SecurityException e) {
            Log.e("Exception: %s", e.getMessage());
        }
    }
        private void getDeviceLocation () {
            /*
             * Get the best and most recent location of the device, which may be null in rare
             * cases when a location is not available.
             */
            try {
                if (locationPermissionGranted) {
                    Task<Location> locationResult = fusedLocationProviderClient.getLastLocation();
                    locationResult.addOnCompleteListener(this, new OnCompleteListener<Location>() {
                        @Override
                        public void onComplete(@NonNull Task<Location> task) {
                            if (task.isSuccessful()) {
                                // Set the map's camera position to the current location of the device.
                                lastKnownLocation = task.getResult();
                                if (lastKnownLocation != null) {
                                    map.moveCamera(CameraUpdateFactory.newLatLngZoom(
                                            // This is having trouble with the location, it thinks it's in America for some reason.
                                            // For MVP demonstration its just hard coded to QUT, that's around where all the test services are anyway.
//                                            new LatLng(lastKnownLocation.getLatitude(),
//                                                    lastKnownLocation.getLongitude()), DEFAULT_ZOOM));
                                            new LatLng(-27.4773,
                                                    153.0270), DEFAULT_ZOOM));
                                }
                            } else {
                                Log.d(TAG, "Current location is null. Using defaults.");
                                Log.e(TAG, "Exception: %s", task.getException());
                                map.moveCamera(CameraUpdateFactory
                                        .newLatLngZoom(defaultLocation, DEFAULT_ZOOM));
                                map.getUiSettings().setMyLocationButtonEnabled(false);
                            }
                        }
                    });
                }
            } catch (SecurityException e) {
                Log.e("Exception: %s", e.getMessage(), e);
            }
        }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        if (map != null) {
            outState.putParcelable(KEY_CAMERA_POSITION, map.getCameraPosition());
            outState.putParcelable(KEY_LOCATION, lastKnownLocation);
        }
        super.onSaveInstanceState(outState);
    }
    private void setViewIds () {
        homeButton = findViewById(R.id.home);
        criticalButton = findViewById(R.id.critical);
        nearbyButton = findViewById(R.id.nearby);
        settingsButton = findViewById(R.id.settings);
    }


}