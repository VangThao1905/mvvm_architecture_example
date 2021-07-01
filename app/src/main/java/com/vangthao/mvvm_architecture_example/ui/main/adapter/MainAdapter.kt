package com.vangthao.mvvm_architecture_example.ui.main.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.vangthao.mvvm_architecture_example.R
import com.vangthao.mvvm_architecture_example.data.model.User
import kotlinx.android.synthetic.main.user_item.view.*

class MainAdapter(private val users: ArrayList<User>) :
    RecyclerView.Adapter<MainAdapter.UserViewHolder>() {

    class UserViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(user: User) {
            itemView.txt_user_name.text = user.name
            itemView.txt_email.text = user.email

            Log.d("UserAdapter", "bind: ${user.avatar}")
            Glide.with(itemView.img_avatar.context)
                .load(user.avatar)
                .into(itemView.img_avatar)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = UserViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.user_item, parent, false)
    )

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        Log.d("UserAdapter", "onBindViewHolder position:$position")
        holder.bind(users[position])
    }

    override fun getItemCount(): Int {
        Log.d("UserAdapter", "getItemCount not limit: ${users.size}")
        return users.size
    }

    fun addData(list: List<User>) {
        users.addAll(list)
    }
}