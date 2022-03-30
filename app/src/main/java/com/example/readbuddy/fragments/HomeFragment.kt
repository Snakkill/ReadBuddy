package com.example.readbuddy.fragments

import android.content.Intent
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.cardview.widget.CardView
import com.android.volley.toolbox.Volley
import com.bumptech.glide.Glide
import com.example.readbuddy.BookInfo
import com.example.readbuddy.R
import com.example.readbuddy.SearchResults
import kotlinx.android.synthetic.main.activity_maps.*
import org.w3c.dom.Text

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class HomeFragment : Fragment() {
    private var param1: String? = null
    private var param2: String? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Create the screen to access view elements in the layout.
        val screen = inflater.inflate(R.layout.fragment_home, container, false)

        val button = screen.findViewById<Button>(R.id.idBtnSearch)

        button.setOnClickListener{
            val searchText = screen.findViewById<EditText>(R.id.searchView)
            val intent = Intent(activity, SearchResults::class.java).apply {
                val s = "query"
                putExtra(s, searchText.getText().toString())
            }
            startActivity(intent)
        }

        val carouselLayout = screen.findViewById<LinearLayout>(R.id.linearLayout_carousel)
        val carouselInflater = LayoutInflater.from(activity)

        val bookList = getDummyBooks()

        // populate the carousel

        for (book in bookList) {
            val view = carouselInflater.inflate(R.layout.carousel_item, carouselLayout, false)
            val thumbnail = view.findViewById<ImageView>(R.id.iv_carouselThumb)
            //val title = view.findViewById<TextView>(R.id.tv_bookTitle)
            //title.text = book.title
            Glide.with(view)
                .load(book.thumb)
                .into(thumbnail)
            carouselLayout.addView(view)
        }

        return screen
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            HomeFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    private fun getDummyBooks(): ArrayList<Book> {
        // Generate 5 books with static content for demonstration purposes.  NOT FINAL.  SERIOUSLY, DON'T EVER DO THIS
        val bookList = arrayListOf<Book>()
        val dummyBooks = mapOf(
            "Think Java" to "https://books.google.com/books/content?id=XYQpDAAAQBAJ&printsec=frontcover&img=1&zoom=1&edge=curl&source=gbs_api",
            "Java Generics and Collections" to "https://books.google.com/books/content?id=VUSbAgAAQBAJ&printsec=frontcover&img=1&zoom=1&edge=curl&source=gbs_api",
            "Effective Java" to "https://books.google.com/books/content?id=ka2VUBqHiWkC&printsec=frontcover&img=1&zoom=1&edge=curl&source=gbs_api",
            "Java for Students" to "https://books.google.com/books/content?id=TRUdyfwdaSoC&printsec=frontcover&img=1&zoom=1&edge=curl&source=gbs_api",
            "TCP/IP Sockets in Java" to  "https://books.google.com/books/content?id=lfHo7uMk7r4C&printsec=frontcover&img=1&zoom=1&edge=curl&source=gbs_api"
        )

        dummyBooks.forEach { entry ->
            val book = Book(entry.key, entry.value)
            bookList.add(book)
        }

        return bookList
    }
}
// Placeholder class, ideally would be the API provided class, but search is using volley.
private class Book (val title : String, val thumb : String)
