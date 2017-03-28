package edu.pitt.cs.cs1635.anp147.sleepycommuters;

import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private Marker alumniHallMarker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
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

        LatLng cathy = new LatLng(40.444328, -79.953155);
        mMap.addMarker(new MarkerOptions().position(cathy).title("Cathy"));

        //Add our test bus stop
        LatLng alumniStop = new LatLng(40.445679, -79.953223);
        alumniHallMarker = mMap.addMarker(new MarkerOptions().position(alumniStop).title("Alumni Hall Bus Stop"));
        alumniHallMarker.setTag(alumniStop);

        mMap.moveCamera(CameraUpdateFactory.newLatLng(cathy));
        mMap.animateCamera(CameraUpdateFactory.zoomTo(mMap.getCameraPosition().zoom + 14f));

        mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
                /*
                    int position = (int)(marker.getTag());
                    //Using position get Value from arraylist
                */

                if (marker.equals(alumniHallMarker))
                {
                    Intent myIntent = new Intent(MapsActivity.this, LineSelectionActivity.class);
                    myIntent.putExtra("stopName", alumniHallMarker.getTitle()); //Optional parameters
                    MapsActivity.this.startActivity(myIntent);
                }

                return false;
            }
        });
    }
}
