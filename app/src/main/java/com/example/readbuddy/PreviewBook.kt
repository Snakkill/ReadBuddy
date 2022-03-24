package com.example.readbuddy

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebViewClient
import kotlinx.android.synthetic.main.activity_preview_book.*

class PreviewBook : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_preview_book)

        // call webView
        webViewSetup()
    }

    @SuppressLint("SetJavaScriptEnabled")
    private fun webViewSetup(){

        // set xml element to webviewclient
        wb_webView.webViewClient = WebViewClient()

        wb_webView.apply {
            // get link that was passed in the intent
            val link = intent.getStringExtra("link")

            // load the url of the book preview
            loadUrl(link.toString())
            settings.javaScriptEnabled = true
        }
    }
}