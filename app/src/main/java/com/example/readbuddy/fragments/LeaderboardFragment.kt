package com.example.readbuddy.fragments

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.readbuddy.R
import com.example.readbuddy.model.FirebaseUtils
import com.example.readbuddy.model.Person

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class LeaderboardFragment : Fragment(), FirebaseUtils.UserObjListner {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private lateinit var leaderboardRecyclerView : RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Dummy data to test leaderboard layouts.
        // Inflate the layout for this fragment

        val screen = inflater.inflate(R.layout.fragment_leaderboard, container, false)

        val db = FirebaseUtils("", "", 0)
        val data = db.getAllFireStore(this)
        Log.d(TAG,data.toString()+"HELLO")

        leaderboardRecyclerView = screen.findViewById(R.id.rv_leaderboard)
        leaderboardRecyclerView.layoutManager = LinearLayoutManager(activity)
        return screen
    }

    override fun onUserObjects(personObjs: MutableList<Person>) {
        val adapter = LeaderboardAdapter(personObjs)
        leaderboardRecyclerView.adapter = adapter
    }
}