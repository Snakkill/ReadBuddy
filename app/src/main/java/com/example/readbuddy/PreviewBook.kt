package com.example.readbuddy

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebView
import android.webkit.WebViewClient
import kotlinx.android.synthetic.main.activity_preview_book.*

class PreviewBook : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_preview_book)

        // call a js function to be loaded after the page loads
        val webViewClient = object : WebViewClient() {
            override fun onPageFinished(view: WebView, url: String) {
                loadJs(view)
            }
        }

        // set xml element to webviewclient
        wb_webView.webViewClient = webViewClient
        // configure webview
        wb_webView.apply {
            settings.javaScriptEnabled = true
            settings.loadWithOverviewMode = true
            settings.allowFileAccess = true
            settings.allowContentAccess = true
            // get link that was passed in the intent and adjust to https
            val link = intent.getStringExtra("link").toString().drop(4)
            // call book preview url passed from API
            loadUrl("https$link")
        }
    }

    // perform js modifications to page
    private fun loadJs(webView: WebView) {
        webView.loadUrl(
            """javascript:(function f() {
                    document.getElementById('menu_container').style.display = 'none';
                    document.getElementById('gbz').style.display = 'none';
                    document.getElementById('gb').style.display = 'none';
                    document.getElementById('scroll_atb').style.overflow = 'scroll';
                    
                    
                    document.getElementById('search_bar').style.display = 'none';
                    
                    var center = document.getElementById('volume-center');
                    center.style.position = 'absolute';
                    center.style.top = '0';
                    center.style.left = '0';
                    center.style.height = '100vh';
                    center.style.padding = '0 25px 0 25px';
                    
                    document.getElementById('viewport').style.zoom = '50%';
                    
                  
                    var center = document.querySelector('.overflow-scrolling');
                    center.style.overflow = 'hidden';
                    center.style.width = '100%';
                    center.style.paddingBottom = '800px';
                    center.style.paddingRight = '15px';
                  
                  
                    document.querySelector('.kd-appbar').style.display = 'none';

                })()"""
        )
    }
}

//center.style.padding = '25px';