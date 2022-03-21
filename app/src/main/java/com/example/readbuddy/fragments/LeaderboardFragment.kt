package com.example.readbuddy.fragments

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
import com.example.readbuddy.model.User
import kotlin.random.Random

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [LeaderboardFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class LeaderboardFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    lateinit var leaderboardRecyclerView : RecyclerView

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

        var screen = inflater.inflate(R.layout.fragment_leaderboard, container, false)

        val db = FirebaseUtils("", "", 0)
        val data = db.GetAllFireStore()
        Log.d("DB DEBUG", data.toString())
        val adapter = LeaderboardAdapter(data)
        leaderboardRecyclerView = screen.findViewById(R.id.rv_leaderboard)
        leaderboardRecyclerView.layoutManager = LinearLayoutManager(activity)
        leaderboardRecyclerView.adapter = adapter

        return screen
    }

    /*
    fun generateDummyData(): ArrayList<User> {
        val dummyUserList = arrayListOf<User>()
        for (i in 1..10) {
            val user = User("John", "Doe")
            val randomScore: List<Int> = List(10) { Random.nextInt(0, 1000) }
            user.points = randomScore[0]
            dummyUserList.add(user)
        }
        return dummyUserList
    }
    */

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment LeaderboardFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            LeaderboardFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}