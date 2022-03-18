package com.example.readbuddy.list


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.readbuddy.R
import com.example.readbuddy.model.User
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.DiffUtil
import com.example.readbuddy.list.ListAdapter.UserViewHolder

class ListAdapter :ListAdapter<User, UserViewHolder>(WORDS_COMPARATOR) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        return UserViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val current = getItem(position)
        holder.bind(current.title,current.author_name,current.len)
    }


    class UserViewHolder (itemView : View) : RecyclerView.ViewHolder(itemView){
        val titleTxtView : TextView = itemView.findViewById(R.id.title)
        val nameTxtView : TextView = itemView.findViewById(R.id.name)
        val lenTxtView : TextView = itemView.findViewById(R.id.length)
        fun bind(title: String?,name:String?, length:Int?) {
            titleTxtView.text = title
            nameTxtView.text = name
            lenTxtView.text = length.toString()
        }

        companion object {
            fun create(parent: ViewGroup): UserViewHolder {
                val view: View = LayoutInflater.from(parent.context)
                    .inflate(R.layout.rlist_item, parent, false)
                return UserViewHolder(view)
            }
        }
    }
    companion object {
        private val WORDS_COMPARATOR = object : DiffUtil.ItemCallback<User>() {
            override fun areItemsTheSame(oldItem: User, newItem: User): Boolean {
                return oldItem === newItem
            }

            override fun areContentsTheSame(oldItem: User, newItem: User): Boolean {
                return (oldItem.title == newItem.title &&
                        oldItem.author_name == newItem.author_name &&
                        oldItem.len == newItem.len )
            }
        }
    }
}
