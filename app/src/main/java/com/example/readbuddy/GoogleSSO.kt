@file:Suppress("SpellCheckingInspection", "SpellCheckingInspection", "SpellCheckingInspection")

package com.example.readbuddy

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.readbuddy.model.FirebaseUtils
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase


class GoogleSSO : AppCompatActivity() {

   private companion object{
       private  const val TAG= "GoogleSSO"
       private const val RC_GOOGLE_SIGN_IN  =4926
   }
    private lateinit var auth: FirebaseAuth



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_google_sso_kotlin)

        // Initialize Firebase Auth
        auth = Firebase.auth


        // Configure Google Sign In
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id)) //don't touch //don't touch
                .requestEmail()
                .build()
       val client = GoogleSignIn.getClient(this,gso)

       val btnSignIn = findViewById<com.google.android.gms.common.SignInButton>(R.id.btnSignIn)
        btnSignIn.setOnClickListener{
            val signInIntent = client.signInIntent
            startActivityForResult(signInIntent,RC_GOOGLE_SIGN_IN)
        }
    }
    override fun onStart() {
        super.onStart()
        // Check if user is signed in (non-null) and update UI accordingly.
        val currentUser = auth.currentUser
        updateUI(currentUser)
    }

    private fun updateUI(user: FirebaseUser?) {
        if(user == null){
            Log.d(TAG,"User is null")
            return

        }
        user.let {

            val name = user.displayName
            val uid = user.uid

            val db = FirebaseUtils(uid,name,0)
            val inDB= db.checkDb(uid) // check if in DB already

            if(inDB){
                db.storeFireStore() // store to DB
            }


            if (name != null) {
                Log.v(TAG,uid)
            }

        }



        val intent = Intent(this,MainActivity::class.java)
        startActivity(intent)

        finish() // close Log activity so not in backstack
    }



    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {

        super.onActivityResult(requestCode, resultCode, data)

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_GOOGLE_SIGN_IN) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            try {
                // Google Sign In was successful, authenticate with Firebase
                val account = task.getResult(ApiException::class.java)!!
                Log.d(TAG, "firebaseAuthWithGoogle:" + account.id)
                firebaseAuthWithGoogle(account.idToken!!)


            } catch (e: ApiException) {
                // Google Sign In failed, update UI appropriately
                Log.w(TAG, "Google sign in failed", e)
            }
        }
    }
    private fun firebaseAuthWithGoogle(idToken: String) {
        val credential = GoogleAuthProvider.getCredential(idToken, null)
        auth.signInWithCredential(credential)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        // Sign in success, update UI with the signed-in user's information
                        Log.d(TAG, "signInWithCredential:success")
                        val user = auth.currentUser
                        updateUI(user)
                    } else {
                        // If sign in fails, display a message to the user.
                        Log.w(TAG, "signInWithCredential:failure", task.exception)
                        updateUI(null)
                    }
                }
    }

    // for debug log in button

     fun startMain(view: View) {

        val intent = Intent(this,MainActivity::class.java)
        startActivity(intent)

        }




}