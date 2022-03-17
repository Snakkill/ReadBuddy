package com.example.readbuddy

import java.util.ArrayList

class BookInfo     // constructor class for BookInfo
    (//getter and setter methods
    // creating string, int and array list
    // variables for our book details
    var title: String, var subtitle: String, var authors: ArrayList<String>, publisher: String?,
    var publishedDate: String, var description: String, var pageCount: Int, var thumbnail: String,
    var previewLink: String, var infoLink: String, buyLink: String?
)