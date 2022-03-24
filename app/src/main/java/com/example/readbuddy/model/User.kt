package com.example.readbuddy.model


import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "reading_list")
data class User (

    val title :String,
    val author_name :String,
    val len:Int,
    val url:String
){
    @PrimaryKey(autoGenerate = true)
    var id :Int = 0


}