package com.example.readbuddy

import androidx.appcompat.app.AppCompatActivity
import android.widget.TextView
import android.os.Bundle
import com.example.readbuddy.R
import android.widget.Toast
import android.content.Intent
import android.net.Uri
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageView
import com.bumptech.glide.Glide
import java.util.ArrayList

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
    var titleTV: TextView? = null
    var subtitleTV: TextView? = null
    var descTV: TextView? = null
    var pageTV: TextView? = null
    var publishDateTV: TextView? = null
    var previewBtn: Button? = null
    private var bookIV: ImageView? = null
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

        // fix with glide
        //Picasso.get().load(thumbnail).into(bookIV)

        var imageUrl = thumbnail!!.drop(4)
        imageUrl = "https$imageUrl"
        Log.d("API DEBUG", imageUrl)
        Glide.with(this)
            .load(imageUrl)
            .placeholder(R.drawable.ic_baseline_error_24)
            .error(R.drawable.ic_baseline_error_24)
            .into(bookIV)





        /*
        // adding on click listener for our preview button.
        previewBtn.setOnClickListener(View.OnClickListener {
            if (previewLink!!.isEmpty()) {
                // below toast message is displayed when preview link is not present.
                Toast.makeText(this@BookDetails, "No preview Link present", Toast.LENGTH_SHORT)
                    .show()
                return@OnClickListener
            }
            // if the link is present we are opening
            // that link via an intent.
            val uri = Uri.parse(previewLink)
            val i = Intent(Intent.ACTION_VIEW, uri)
            startActivity(i)
        })
*/
    }
}