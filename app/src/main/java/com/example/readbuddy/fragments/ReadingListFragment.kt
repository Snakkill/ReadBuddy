package com.example.readbuddy.fragments

import android.app.Notification
import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.accessibility.AccessibilityEventCompat.setAction
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.readbuddy.R
import com.example.readbuddy.UserApplication
import com.example.readbuddy.list.ListAdapter
import com.example.readbuddy.list.ListItemClickListener
import com.example.readbuddy.viewmodel.UserViewModel

import com.example.readbuddy.viewmodel.UserViewModelFactory
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_reading_list.*
import nl.dionsegijn.konfetti.models.Shape
import nl.dionsegijn.konfetti.models.Size

class ReadingListFragment : Fragment(),ListItemClickListener{

    private val userViewModel: UserViewModel by viewModels {
        UserViewModelFactory((activity?.application as UserApplication).repository)
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {



        val view = inflater.inflate(R.layout.fragment_reading_list, container, false)


        val adapter = ListAdapter(this)

        val recyclerView = view.findViewById<RecyclerView>(R.id.rList_recycler_view)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

         userViewModel.readAllData.observe(viewLifecycleOwner, Observer {
                user -> user.let{adapter.submitList(it)}

        })

        val itemTouchHelper = ItemTouchHelper( object : ItemTouchHelper.SimpleCallback(0,
            ItemTouchHelper.LEFT ) {
            override fun onMove(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder, target: RecyclerView.ViewHolder): Boolean {
                // do nothing
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                userViewModel.deleteUser(adapter.getUserByLoc(viewHolder.getAdapterPosition()))
            }
        }).attachToRecyclerView(recyclerView)





        return view
    }

    override fun OnListItemClick() {
//        val repliedNotification = Notification.Builder(context, CHANNEL_ID)
//            .setSmallIcon(R.drawable.ic_message)
//            .setContentText(getString(R.string.replied))
//            .build()

        viewKonfetti.build()
            .addColors(Color.YELLOW, Color.GREEN, Color.MAGENTA)
            .setDirection(0.0, 359.0)
            .setSpeed(1f, 5f)
            .setFadeOutEnabled(true)
            .setTimeToLive(2000L)
            .addShapes(Shape.RECT, Shape.CIRCLE)
            .addSizes(Size(12))
            .setPosition(-50f, viewKonfetti.width + 50f, -50f, -50f)
            .streamFor(300, 5000L)
        Toast.makeText(context,"Congratulations",Toast.LENGTH_SHORT).show()

        }
    }





