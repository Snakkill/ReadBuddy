package com.example.readbuddy.model

import android.content.ContentValues.TAG
import android.util.Log
import com.example.readbuddy.fragments.LeaderboardFragment
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.DocumentSnapshot
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






            db.collection("users").whereEqualTo("ID",uid).get()
                   .addOnSuccessListener {
                       for(document in it){
                      var docRef = db.collection("users").document(document.id)

                           docRef.get().addOnCompleteListener(OnCompleteListener<DocumentSnapshot?> { task ->
                               if (task.isSuccessful) {
                                   val document = task.result
                                   if (document != null) {
                                       Log.i("LOGGER", "Score " + document.getLong("Score"))
                                       db.collection("users")
                                               .document(document.id)
                                               .update("Score", document.getLong("Score")?.plus(50))


                                   } else {
                                       Log.d("LOGGER", "No such document")
                                   }
                               } else {
                                   Log.d("LOGGER", "get failed with ", task.exception)
                               }
                           })



                       }
                   }


        } else {
            Log.d(TAG,"User is not logged in properly")
        }

    }

}