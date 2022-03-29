package com.example.readbuddy

import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.app.ActivityCompat

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.example.readbuddy.databinding.ActivityMapsBinding
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices


class MapsActivity : AppCompatActivity(), OnMapReadyCallback {
    //variables needed for GoogleMaps
    private lateinit var mMap: GoogleMap
    private lateinit var binding: ActivityMapsBinding

    //variables needed for current location. Should be passed by intent later
    private lateinit var fusedLocationClient: FusedLocationProviderClient
    private var currentLat:Double = 0.0
    private var currentLong:Double = 0.0

    override fun onCreate(savedInstanceState: Bundle?) {

        currentLat=intent.getDoubleExtra("lat",0.0)
        currentLong=intent.getDoubleExtra("lon",0.0)

      fusedLocationClient=LocationServices.getFusedLocationProviderClient(this)
        checkLocation() //set variables to new current cords
    

            super.onCreate(savedInstanceState)
            binding = ActivityMapsBinding.inflate(layoutInflater)
            setContentView(binding.root)



        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
    val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
    mapFragment.getMapAsync(this)

    }
    //loads late, will fix with vm
    private fun checkLocation() {
        val task = fusedLocationClient.lastLocation

        if(ActivityCompat.checkSelfPermission(this,android.Manifest.permission.ACCESS_FINE_LOCATION)
            !=PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(this,android.Manifest.permission.ACCESS_COARSE_LOCATION)
                !=PackageManager.PERMISSION_GRANTED
        ){
            ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION),101)
            return
        }
       task.addOnSuccessListener {
           if(it!=null){
               currentLat=it.latitude
               currentLong=it.longitude
               Toast.makeText(applicationContext,"${currentLat}${currentLong}",Toast.LENGTH_SHORT).show()

           }
       }
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

        val currentPosition = LatLng(currentLat,currentLong)
        val example1= LatLng(currentLat+0.01,currentLong+0.01)
        val example2= LatLng(currentLat-0.02,currentLong-0.02)
        val example3= LatLng(currentLat+0.03,currentLong-0.03)

        //Fake markers to represent API
        mMap.addMarker(MarkerOptions().position(currentPosition).title("You are here"))
        mMap.addMarker(MarkerOptions().position(example1).title("Barns And Nobel"))
        mMap.addMarker(MarkerOptions().position(example2).title("Books ETC."))
        mMap.addMarker(MarkerOptions().position(example3).title("Target"))

        mMap.moveCamera(CameraUpdateFactory.newLatLng(currentPosition))
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(currentPosition, 15f))

    }



}







