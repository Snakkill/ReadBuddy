package com.example.readbuddy.fragments

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.readbuddy.model.Person

import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import com.example.readbuddy.R

/*
    Handles filling the leaderboard with leaderboard items.
 */
class LeaderboardAdapter(private val uList: List<Person>) : RecyclerView.Adapter<LeaderboardAdapter.ViewHolder>() {
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvRank : TextView = view.findViewById(R.id.tv_rank)
        val tvName : TextView = view.findViewById(R.id.tv_name)
        val tvPoints : TextView = view.findViewById(R.id.tv_points)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    /*
        Write fragment behavior code down below
     */
    ): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.leaderboard_item, parent, false)
        return ViewHolder(view)
    }

    /*
        Handle inflating the recyclerview with data
     */
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val userViewModel = uList[position]
        val fullName = userViewModel.name
        holder.tvName.text = fullName
        holder.tvPoints.text = userViewModel.Score.toString()
        val rank = position + 1 // Fix 0 index
        holder.tvRank.text = rank.toString()

    }

    override fun getItemCount(): Int {
        return uList.size
    }
}