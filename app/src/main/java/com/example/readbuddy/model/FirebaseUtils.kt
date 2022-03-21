package com.example.readbuddy.model

import android.content.ContentValues.TAG
import android.util.Log
import com.example.readbuddy.fragments.LeaderboardFragment
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

//added gradel firestore SDK

class FirebaseUtils(ID: String,Name:String?,Score:Int){
    val db = Firebase.firestore

    val ID =ID
    val Name = Name
    var Score= Score

    interface UserObjListner{
        fun onUserObjects(personObjs: MutableList<Person>)
    }
    lateinit var userObjListner: UserObjListner


    fun StoreFireStore(){

        // Create a new user with a first and last name
        val user = hashMapOf(
                "ID" to ID,
                "Name" to Name,
                "Score" to Score
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

    fun GetAllFireStore(leaderboardFragment: LeaderboardFragment) {
        //list of object
        var personArrayofObjects: MutableList<Person> = mutableListOf()
        userObjListner = leaderboardFragment


       val users = db.collection("users")
               .orderBy("Score", Query.Direction.DESCENDING)
               .get()
               .addOnCompleteListener { task ->
            if (task.isSuccessful) {
                Log.d("TAG", "Inside onComplete function!");
                for (document in task.result) {

                    val userObj = Person (
                         document.data["ID"].toString(),
                        document.data["Name"].toString(),
                            document.data["Score"] as Long
                    )
                    Log.v(TAG,"DB entry"+userObj.toString())

                    personArrayofObjects.add(userObj)
                    Log.v(TAG,"HELLO for loop"+personArrayofObjects.toString())

                }
             //   sendObject{userArrayofObjects}
               (userObjListner as LeaderboardFragment).onUserObjects(personArrayofObjects)
                Log.v(TAG,"HELLO you"+personArrayofObjects)

            }

        }



    }

    private fun sendObject(function: () -> MutableList<Person>) {

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