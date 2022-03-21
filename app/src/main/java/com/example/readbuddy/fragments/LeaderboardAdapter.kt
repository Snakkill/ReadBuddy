package com.example.readbuddy.fragments

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.readbuddy.model.Person

import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import com.example.readbuddy.R

class LeaderboardAdapter(private val uList: List<Person>) : RecyclerView.Adapter<LeaderboardAdapter.ViewHolder>() {
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvRank : TextView = view.findViewById(R.id.tv_rank)
        val tvName : TextView = view.findViewById(R.id.tv_name)
        val tvPoints : TextView = view.findViewById(R.id.tv_points)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): LeaderboardAdapter.ViewHolder {

        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.leaderboard_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        // val DB = FirebaseUtils("","",0)
        // val UserList=DB.GetAllFireStore()


        // Sort the list by descending points
       // uList.sortedByDescending { it.points }
        val UserViewModel = uList[position]
        val fullname = UserViewModel.name
        holder.tvName.text = fullname
        holder.tvPoints.text = UserViewModel.Score.toString()
        val rank = position + 1 // Fix 0 index
        holder.tvRank.text = rank.toString()

    }

    override fun getItemCount(): Int {
        return uList.size
    }
}