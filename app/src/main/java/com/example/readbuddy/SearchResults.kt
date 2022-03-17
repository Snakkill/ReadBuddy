package com.example.readbuddy

import androidx.appcompat.app.AppCompatActivity
import com.android.volley.RequestQueue
import com.example.readbuddy.BookInfo
import android.os.Bundle
import android.view.View
import android.widget.*
import com.example.readbuddy.R
import com.android.volley.toolbox.Volley
import com.android.volley.toolbox.JsonObjectRequest
import org.json.JSONObject
import org.json.JSONArray
import com.example.readbuddy.BookAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import org.json.JSONException
import com.android.volley.Request
import com.android.volley.VolleyError
import java.util.ArrayList

class SearchResults : AppCompatActivity() {
    // creating variables
    private var mRequestQueue: RequestQueue? = null
    private var bookInfoArrayList: ArrayList<BookInfo>? = null
    private var progressBar: ProgressBar? = null
    private var searchEdt: EditText? = null
    private var searchBtn: ImageButton? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_results)

        // initializing our views.
        val progressBar = findViewById<ProgressBar>(R.id.idLoadingPB)
        //val searchEdt = findViewById<EditText>(R.id.searchView)
        //val searchBtn = findViewById<Button>(R.id.idBtnSearch)

        // initializing on click listener for search button.
        /*
        searchBtn.setOnClickListener(View.OnClickListener {
            progressBar.setVisibility(View.VISIBLE)

            // checking if edittext field is empty or not.
            if (searchEdt.getText().toString().isEmpty()) {
                searchEdt.setError("Please enter search query")
                return@OnClickListener
            }

            // get all the books from the API.

        })*/

        val queryString = intent.getStringExtra("query").toString()
        getBooksInfo(queryString)
    }

    private fun getBooksInfo(query: String) {

        // creating a new array list.
        val bookInfoArrayList = ArrayList<BookInfo>()

        // variable for request queue.
        val mRequestQueue = Volley.newRequestQueue(this@SearchResults)

        // clear cache when data is being updated.
        mRequestQueue.getCache().clear()

        // get url in json
        val url = "https://www.googleapis.com/books/v1/volumes?q=$query"

        // create a new request queue.
        val queue = Volley.newRequestQueue(this@SearchResults)


        // make json object request passing url
        val booksObjrequest = JsonObjectRequest(Request.Method.GET, url, null, { response ->
            //progressBar?.visibility = View.GONE
            // extract all json data.
            try {
                val itemsArray = response.getJSONArray("items")
                for (i in 0 until itemsArray.length()) {
                    val itemsObj = itemsArray.getJSONObject(i)
                    val volumeObj = itemsObj.getJSONObject("volumeInfo")
                    val title = volumeObj.optString("title")
                    val subtitle = volumeObj.optString("subtitle")
                    val authorsArray = volumeObj.getJSONArray("authors")
                    val publisher = volumeObj.optString("publisher")
                    val publishedDate = volumeObj.optString("publishedDate")
                    val description = volumeObj.optString("description")
                    val pageCount = volumeObj.optInt("pageCount")
                    val imageLinks = volumeObj.optJSONObject("imageLinks")
                    val thumbnail = imageLinks.optString("thumbnail")
                    val previewLink = volumeObj.optString("previewLink")
                    val infoLink = volumeObj.optString("infoLink")
                    val saleInfoObj = itemsObj.optJSONObject("saleInfo")
                    val buyLink = saleInfoObj.optString("buyLink")
                    val authorsArrayList = ArrayList<String>()
                    if (authorsArray.length() != 0) {
                        for (j in 0 until authorsArray.length()) {
                            authorsArrayList.add(authorsArray.optString(i))
                        }
                    }

                    // save extracted data in bookinfo class
                    val bookInfo = BookInfo(
                        title,
                        subtitle,
                        authorsArrayList,
                        publisher,
                        publishedDate,
                        description,
                        pageCount,
                        thumbnail,
                        previewLink,
                        infoLink,
                        buyLink
                    )

                    // pass booklist class in array list.
                    bookInfoArrayList!!.add(bookInfo)

                    // pass our array list in adapter class.
                    val adapter = BookAdapter(bookInfoArrayList, this@SearchResults)

                    // add linear layout manager for recycler view.
                    val linearLayoutManager =
                        LinearLayoutManager(this@SearchResults, RecyclerView.VERTICAL, false)
                    val mRecyclerView = findViewById<View>(R.id.idRVBooks) as RecyclerView

                    // in below line we are setting layout manager and
                    // adapter to our recycler view.
                    mRecyclerView.layoutManager = linearLayoutManager
                    mRecyclerView.adapter = adapter
                }
            } catch (e: JSONException) {
                e.printStackTrace()
                // displaying a toast message when we get any error from API
                Toast.makeText(this@SearchResults, "No Data Found$e", Toast.LENGTH_SHORT).show()
            }
        }) { error -> // also displaying error message in toast.
            Toast.makeText(this@SearchResults, "Error found is $error", Toast.LENGTH_SHORT).show()
        }

        // add json object request in our request queue.
        queue.add(booksObjrequest)
    }
}