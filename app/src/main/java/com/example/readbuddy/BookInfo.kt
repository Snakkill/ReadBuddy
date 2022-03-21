package com.example.readbuddy

import java.util.ArrayList

class BookInfo
    (
    var title: String, var subtitle: String, var authors: ArrayList<String>, publisher: String?,
    var publishedDate: String, var description: String, var pageCount: Int, var thumbnail: String,
    var previewLink: String, var infoLink: String, buyLink: String?
)