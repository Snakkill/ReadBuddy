package com.example.readbuddy

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.bumptech.glide.Glide
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices

class BookDetails : AppCompatActivity() {
    //aymans
    private lateinit var fusedLocationClient: FusedLocationProviderClient

    // variables for xml
    var title: String? = null
    var subtitle: String? = null
    var publishedDate: String? = null
    var description: String? = null
    var thumbnail: String? = null
    var previewLink: String? = null
    var infoLink: String? = null
    var pageCount = 0
    private val authors: ArrayList<String>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_book_details)

        // initializing views
        val titleTV = findViewById<TextView>(R.id.idTVTitle)
        val subtitleTV = findViewById<TextView>(R.id.idTVSubTitle)
        val descTV = findViewById<TextView>(R.id.idTVDescription)
        val pageTV = findViewById<TextView>(R.id.idTVNoOfPages)
        val publishDateTV = findViewById<TextView>(R.id.idTVPublishDate)
        //val previewBtn = findViewById(R.id.idBtnPreview)
        val bookIV = findViewById<ImageView>(R.id.idIVbook)

        // data passed from our adapter class.
        title = intent.getStringExtra("title")
        subtitle = intent.getStringExtra("subtitle")
        publishedDate = intent.getStringExtra("publishedDate")
        description = intent.getStringExtra("description")
        pageCount = intent.getIntExtra("pageCount", 0)
        thumbnail = intent.getStringExtra("thumbnail")
        previewLink = intent.getStringExtra("previewLink")
        infoLink = intent.getStringExtra("infoLink")

        // bind data to views
        titleTV.setText(title)
        subtitleTV.setText(subtitle)
        publishDateTV.setText("Published On : $publishedDate")
        descTV.setText(description)
        pageTV.setText("No Of Pages : $pageCount")

        // implement image with Glide
        var imageUrl = thumbnail!!.drop(4)
        imageUrl = "https$imageUrl"
        Log.d("API DEBUG", imageUrl)
        Glide.with(this)
            .load(imageUrl)
            .placeholder(R.drawable.ic_baseline_error_24)
            .error(R.drawable.ic_baseline_error_24)
            .into(bookIV)
    }

    fun LaunchMaps(view: View) {
        val intent = Intent(this,MapsActivity::class.java)

        fusedLocationClient= LocationServices.getFusedLocationProviderClient(this)
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
                Toast.makeText(applicationContext,"${it.latitude}${it.longitude}",Toast.LENGTH_SHORT).show()
                intent.putExtra("lat",it.latitude)
                intent.putExtra("lon",it.longitude)
                startActivity(intent)
            }
        }










    }

    fun LookUpAmazon(view: View) {
        val url :String = "https://www.amazon.com/s?k="+title
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        startActivity(intent)
    }
}