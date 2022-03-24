package com.example.readbuddy.model


import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "reading_list")
data class User (

    val title :String,
    val author_name :String,
    val len:Int,
    val url:String
):Parcelable {
    @PrimaryKey(autoGenerate = true)
    var id :Int = 0


}