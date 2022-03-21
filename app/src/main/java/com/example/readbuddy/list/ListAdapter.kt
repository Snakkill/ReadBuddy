package com.example.readbuddy.list
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.*
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.readbuddy.R
import com.example.readbuddy.model.User
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.DiffUtil
import com.example.readbuddy.databinding.RlistItemBinding
import com.example.readbuddy.list.ListAdapter.UserViewHolder

class ListAdapter(
    private val mListener: ListItemClickListener
):ListAdapter<User, UserViewHolder>(WORDS_COMPARATOR) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        return (UserViewHolder.create(parent))


    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
//        val current = getItem(position)
//        holder.bind(current.title,current.author_name,current.len)
        holder.bind(getItem(position),mListener)

    }

    fun getUserByLoc(position: Int): User {
        return getItem(position)
    }


    class UserViewHolder (val binding : RlistItemBinding) : RecyclerView.ViewHolder(binding.root)
        {
//        val titleTxtView : TextView = itemView.findViewById(R.id.title)
//        val nameTxtView : TextView = itemView.findViewById(R.id.name)
//        val lenTxtView : TextView = itemView.findViewById(R.id.length)
        val imgView :ImageView = itemView.findViewById(R.id.view_list_img)
        val imgCheck : ImageButton = itemView.findViewById(R.id.btn_markRead)





        fun bind(user: User, mListener: ListItemClickListener) {
            binding.isUser=user
            binding.btnRead = mListener
            binding.executePendingBindings()

        }

        companion object {
            fun create(parent: ViewGroup): UserViewHolder {
                val inflater = LayoutInflater.from(parent.context)
                val binding = DataBindingUtil.inflate<RlistItemBinding>(inflater,R.layout.rlist_item, parent, false)

                return UserViewHolder(binding)
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
