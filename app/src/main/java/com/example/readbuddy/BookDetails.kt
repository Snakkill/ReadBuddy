package com.example.readbuddy

import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide

class BookDetails : AppCompatActivity() {
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
        startActivity(intent)
    }
}