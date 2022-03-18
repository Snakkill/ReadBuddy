package com.example.readbuddy

import android.content.Context
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
import android.widget.ImageView
import com.example.readbuddy.R
import com.squareup.picasso.Picasso
import android.widget.TextView
import com.bumptech.glide.Glide
import java.util.ArrayList

class BookAdapter     // create constructor for array list and context.
    (// creating variables for arraylist and context.
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
        //holder.bookIV.setImageIcon(bookInfo.getThumbnail());

        // set image from URL in image view.
        //Picasso.get().load(bookInfo.thumbnail).into(holder.bookIV)
        // R.drawable.icon
       // Glide.with().load(bookInfo.thumbnail).into(holder.bookIV);

        /*
        // click listener for recycler view
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // inside on click listener method we are calling a new activity
                // and passing all the data of that item in next intent.
                Intent i = new Intent(mcontext, BookDetails.class);
                i.putExtra("title", bookInfo.getTitle());
                i.putExtra("subtitle", bookInfo.getSubtitle());
                i.putExtra("authors", bookInfo.getAuthors());
                // i.putExtra("publisher", bookInfo.getPublisher());
                i.putExtra("publishedDate", bookInfo.getPublishedDate());
                i.putExtra("description", bookInfo.getDescription());
                i.putExtra("pageCount", bookInfo.getPageCount());
                i.putExtra("thumbnail", bookInfo.getThumbnail());
                i.putExtra("previewLink", bookInfo.getPreviewLink());
                i.putExtra("infoLink", bookInfo.getInfoLink());
                // i.putExtra("buyLink", bookInfo.getBuyLink());


                // starting new intent.
                mcontext.startActivity(i);
            }
        });*/
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