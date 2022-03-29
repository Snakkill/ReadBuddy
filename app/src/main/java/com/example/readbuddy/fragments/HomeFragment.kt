package com.example.readbuddy.fragments

import android.content.Intent
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.SearchView
import com.example.readbuddy.R
import com.example.readbuddy.SearchResults

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class HomeFragment : Fragment() {
    private var param1: String? = null
    private var param2: String? = null
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
        // Create the screen to access view elements in the layout.
        val screen = inflater.inflate(R.layout.fragment_home, container, false)
        val button = screen.findViewById<Button>(R.id.idBtnSearch)
        //val carousel = screen.findViewById<LinearLayout>(R.id.book_carousel)
        // Handle the search function
        button.setOnClickListener{
            val searchText = screen.findViewById<EditText>(R.id.searchView)
            val intent = Intent(activity, SearchResults::class.java).apply {
                val s = "query"
                putExtra(s, searchText.getText().toString())
            }
            startActivity(intent)
        }

        return screen
    }



    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            HomeFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}
