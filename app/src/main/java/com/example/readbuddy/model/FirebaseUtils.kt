package com.example.readbuddy.model

import android.content.ContentValues.TAG
import android.content.Intent
import android.util.Log
import android.widget.Toast
import com.example.readbuddy.fragments.LeaderboardFragment
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.firestore.QuerySnapshot

import androidx.annotation.NonNull
import androidx.core.content.ContextCompat.startActivity
import com.example.readbuddy.MainActivity

import com.google.android.gms.tasks.OnCompleteListener
import kotlin.concurrent.timerTask


//added gradle firestore SDK

class FirebaseUtils(ID: String,Name:String?,Score:Int){
    private val db = Firebase.firestore

    val id =ID
    val name = Name
    private var score= Score

    //created interface to pull info from callback
    interface UserObjListner{
        fun onUserObjects(personObjs: MutableList<Person>)
    }
    private lateinit var userObjListner: UserObjListner


    fun storeFireStore(uid: String){
        db.collection("users").whereEqualTo("ID", uid).limit(1).get()
                .addOnCompleteListener {
                    if( it.result.isEmpty){
                        // Create a new user with a first and last name
                        val user = hashMapOf(
                                "ID" to id,
                                "Name" to name,
                                "Score" to score
                        )

// Add a new document with a generated ID
                        db.collection("users")
                                .add(user)
                                .addOnSuccessListener { documentReference ->
                                    Log.d(TAG, "DocumentSnapshot added with ID: ${documentReference.id}")
                                }
                                .addOnFailureListener { e ->
                                    Log.w(TAG, "Error adding document", e)
                                }

                    }
                }


    }
//gets all data from DB in Descending order
    fun getAllFireStore(leaderboardFragment: LeaderboardFragment) {
        //list of object
        val personArrayofObjects: MutableList<Person> = mutableListOf()
        userObjListner = leaderboardFragment


       val users = db.collection("users")
               .orderBy("Score", Query.Direction.DESCENDING)
               .get()
               .addOnCompleteListener { task ->
            if (task.isSuccessful) {
                Log.d("TAG", "Inside onComplete function!")
                for (document in task.result) {

                    val userObj = Person (
                         document.data["ID"].toString(),
                        document.data["Name"].toString(),
                            document.data["Score"] as Long
                    )
                    Log.v(TAG,userObj.toString())

                    personArrayofObjects.add(userObj)
                    Log.v(TAG,personArrayofObjects.toString())

                }

               (userObjListner as LeaderboardFragment).onUserObjects(personArrayofObjects)
                Log.v(TAG, personArrayofObjects.toString())

            }

        }



    }



    fun checkDb(uid: String){
        db.collection("users").whereEqualTo("ID", uid).limit(1).get()
                .addOnCompleteListener {
                    if( it.result.isEmpty){


                    }
                }


    }
//Pagecount:Int pram
    fun updateDb(){
        val user = Firebase.auth.currentUser
        if (user != null) {
            //Check if correct user
            val uid = user.uid
            val name = user.displayName

            Log.d(TAG,name+"  "+uid)





            db.collection("users").whereEqualTo("ID",uid).get()
                   .addOnSuccessListener {
                       for(document in it){
                           db.collection("users")
                                   .document(document.id)
                                   .update("Score",910)
                       }
                   }


        } else {
            Log.d(TAG,"User is not logged in properly")
        }

    }

}