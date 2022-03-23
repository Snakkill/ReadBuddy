package com.example.readbuddy.model

import android.content.ContentValues.TAG
import android.util.Log
import com.example.readbuddy.fragments.LeaderboardFragment
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

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


    fun storeFireStore(){

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



    fun checkDb(uid: String): Boolean {

       val check = db.collection("users").whereEqualTo("ID",uid).get()

        if(check == null){
            return true
            Log.d(TAG,"empty, not in Db")
        }
        else{
            Log.v(TAG,"it in the DB")
            return false

        }



    }

}