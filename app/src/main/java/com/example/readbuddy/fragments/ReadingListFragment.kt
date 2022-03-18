package com.example.readbuddy.fragments

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.readbuddy.R
import com.example.readbuddy.UserApplication
import com.example.readbuddy.list.ListAdapter
import com.example.readbuddy.repository.UserRepository
import com.example.readbuddy.viewmodel.UserViewModel

import com.example.readbuddy.viewmodel.UserViewModelFactory

class ReadingListFragment : Fragment() {

    private lateinit var mUserViewModel : UserViewModel
    private val userViewModel: UserViewModel by viewModels {
        UserViewModelFactory((activity?.application as UserApplication).repository)
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_reading_list, container, false)


        val adapter = ListAdapter()
        val recyclerView = view.findViewById<RecyclerView>(R.id.rList_recycler_view)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

         userViewModel.readAllData.observe(viewLifecycleOwner, Observer {
                user -> user.let{adapter.submitList(it)}

        })


        return view
    }


}