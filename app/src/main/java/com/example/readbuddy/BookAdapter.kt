package com.example.readbuddy

import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.recyclerview.widget.RecyclerView
import com.example.readbuddy.BookAdapter.BookViewHolder
import android.view.ViewGroup
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import java.util.ArrayList
import com.example.readbuddy.BookInfo as BookInfo

// create constructor for array list and context.
class BookAdapter
    (
    // variables for arraylist and context.
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
        holder.authorTV.text = bookInfo.authors[0]
        holder.pageCountTV.text = bookInfo.pageCount.toString() + "Pages"
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
          i.putExtra("previewLink", bookInfo.previewLink)
          i.putExtra("authors", bookInfo.authors[0])
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
        var authorTV: TextView
        var pageCountTV: TextView
        var dateTV: TextView
        var bookIV: ImageView

        init {
            nameTV = itemView.findViewById(R.id.idTVBookTitle)
            authorTV = itemView.findViewById(R.id.idTVAuthor)
            pageCountTV = itemView.findViewById(R.id.idTVPageCount)
            dateTV = itemView.findViewById(R.id.idTVDate)
            bookIV = itemView.findViewById(R.id.idIVbook)
        }
    }
}


