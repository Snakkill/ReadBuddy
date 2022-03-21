package com.example.readbuddy

import android.content.Context
import android.content.Intent
import android.util.Log
//import com.example.readbuddy.BookInfo.title
//import com.example.readbuddy.BookInfo.pageCount
//import com.example.readbuddy.BookInfo.publishedDate
//import com.example.readbuddy.BookInfo.thumbnail
//import com.example.readbuddy.BookInfo
import androidx.recyclerview.widget.RecyclerView
import com.example.readbuddy.BookAdapter.BookViewHolder
import android.view.ViewGroup
import android.view.LayoutInflater
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import com.example.readbuddy.R
import android.widget.TextView
import com.bumptech.glide.Glide
import com.bumptech.glide.module.AppGlideModule
import java.util.ArrayList

class BookAdapter // create constructor for array list and context.
    (
    // creating variables for arraylist and context.
    private val bookInfoArrayList: ArrayList<BookInfo>, private val mcontext: Context
) : RecyclerView.Adapter<BookViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookViewHolder {

        // inflate layout for item of recycler view item.
        val view = LayoutInflater.from(parent.context).inflate(R.layout.book_rv_item, parent, false)
        return BookViewHolder(view)
    }

    override fun onBindViewHolder(holder: BookViewHolder, position: Int) {

        // set data to each UI component.
        val bookInfo = bookInfoArrayList[position]
        holder.nameTV.text = bookInfo.title
        // holder.publisherTV.setText(bookInfo.getPublisher());
        holder.pageCountTV.text = "Pages : " + bookInfo.pageCount
        holder.dateTV.text = bookInfo.publishedDate
        var imageUrl = bookInfo.thumbnail.drop(4)
        imageUrl = "https$imageUrl"
        Log.d("API DEBUG", imageUrl)
        Glide.with(holder.itemView)
            .load(imageUrl)
            .placeholder(R.drawable.ic_baseline_error_24)
            .error(R.drawable.ic_baseline_error_24)
            .into(holder.bookIV)


        // start intent for book details, passing data from BookInfo class
        holder.itemView.setOnClickListener {
            val i = Intent(mcontext, BookDetails::class.java)

              i.putExtra("title", bookInfo.title)
              i.putExtra("subtitle", bookInfo.subtitle)
              i.putExtra("publishedDate", bookInfo.publishedDate)
              i.putExtra("pageCount", bookInfo.pageCount)
              i.putExtra("description", bookInfo.description)
              i.putExtra("thumbnail", bookInfo.thumbnail)
              // preview link
              //intent.putExtra("previewLink" bookInfo.title)
              // info link
              //intent.putExtra("infoLink" bookInfo.title)
              mcontext.startActivity(i)

        }



    }

    override fun getItemCount(): Int {
        // return size of array list.
        return bookInfoArrayList.size
    }

    inner class BookViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        // initialize text view and image views.
        var nameTV: TextView
        var publisherTV: TextView? = null
        var pageCountTV: TextView
        var dateTV: TextView
        var bookIV: ImageView

        init {
            nameTV = itemView.findViewById(R.id.idTVBookTitle)
            // publisherTV = itemView.findViewById(R.id.idTVpublisher);
            pageCountTV = itemView.findViewById(R.id.idTVPageCount)
            dateTV = itemView.findViewById(R.id.idTVDate)
            bookIV = itemView.findViewById(R.id.idIVbook)
        }
    }
}