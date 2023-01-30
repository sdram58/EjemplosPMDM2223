package com.example.mapsexample

import android.content.pm.PackageManager

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Looper
import android.util.Log
import android.widget.Toast
import androidx.core.app.ActivityCompat

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.example.mapsexample.databinding.ActivityMapsBinding
import com.google.android.gms.location.*
import com.google.android.gms.location.Priority.PRIORITY_BALANCED_POWER_ACCURACY
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.Marker


const val REQUEST_PERMISSION_CODE=110
class MapsActivity : AppCompatActivity(), OnMapReadyCallback, GoogleMap.OnInfoWindowClickListener {

    private lateinit var mMap: GoogleMap
    private lateinit var binding: ActivityMapsBinding

    private lateinit var mFusedLocationClient: FusedLocationProviderClient
    private var mLatitude = 0.0
    private var mLongitude = 0.0
    private lateinit var locationRequest: LocationRequest
    private lateinit var locationCallback: LocationCallback

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMapsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)


        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this)

        //This new Google location services only works with API 31 and greater
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.S_V2) {

            val locationRequestBuilder = LocationRequest.Builder(16000)
            locationRequestBuilder.setDurationMillis(Long.MAX_VALUE)
            locationRequestBuilder.setPriority(PRIORITY_BALANCED_POWER_ACCURACY)
            locationRequestBuilder.setMinUpdateDistanceMeters(0f)
            locationRequest = locationRequestBuilder.build()
        }else{
            Toast.makeText(this, "Old device", Toast.LENGTH_SHORT).show()
            locationRequest = LocationRequest()
        }

        locationCallback = object: LocationCallback(){
            override fun onLocationResult(locationResult: LocationResult){
                super.onLocationResult(locationResult)
               for(location in locationResult.locations){
                    mLatitude = location.latitude
                    mLongitude = location.longitude
                    /*Toast.makeText(this@MapsActivity, "Lat:" + mLatitude + "Lon:" +
                            mLongitude, Toast.LENGTH_LONG) .show();*/

                   Log.d("LOCATION_MSG", "($mLatitude, $mLongitude)")
                }
            }
        }

        requestLocations()
    }

    private fun requestLocations() {
        if (ActivityCompat.checkSelfPermission(this@MapsActivity,
                android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                this@MapsActivity,
                android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(this@MapsActivity,
                arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION, android.Manifest.permission.ACCESS_COARSE_LOCATION),
                REQUEST_PERMISSION_CODE)
        } else {
            mFusedLocationClient.requestLocationUpdates(
                locationRequest, locationCallback, Looper.getMainLooper())
        }
    }

    private fun removeLocations() {
        mFusedLocationClient.removeLocationUpdates(locationCallback)
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
    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        // Add a marker in Sydney and move the camera
        val sydney = LatLng(-34.0, 151.0)

        val marker = mMap.addMarker(MarkerOptions().position(sydney).title("Marker in Sydney").snippet("Inhabitants 18000"))
        marker?.let{
            it.setIcon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE)) // Blue color
            //it.icon(BitmapDescriptorFactory.fromResource(R.mipmap.busmarker)) // Image from resource

        }

        mMap.setOnMarkerClickListener {
            Toast.makeText(this, "Click on Marker", Toast.LENGTH_SHORT) .show()
            return@setOnMarkerClickListener true
        }
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney))

        mMap.setOnInfoWindowClickListener(this)

    }

    override fun onInfoWindowClick(marker: Marker) {
        //Toast.makeText(this, "Marker pressed on: \n $ {marker?.Tag}($ {marker?.Position?.Latitude}" +
        //        "$ {marker? .position? .longitude})", Toast.LENGTH_SHORT).show();
    }
}