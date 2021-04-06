package com.bagasbest.berepo.adapter

import android.annotation.SuppressLint
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bagasbest.berepo.DetailActiviy
import com.bagasbest.berepo.R
import com.bagasbest.berepo.model.FollowerModel
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.list_user_follower.view.*


class FollowerAdapter :
    RecyclerView.Adapter<FollowerAdapter.FollowerViewHolder>() {


    private val userList = ArrayList<FollowerModel>()
    fun setData(items: ArrayList<FollowerModel>) {
        userList.clear()
        userList.addAll(items)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FollowerViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_user_follower, parent, false)
        return FollowerViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: FollowerViewHolder, position: Int) {
        holder.bind(userList[position])

        holder.itemView.setOnClickListener {
            val intent = Intent(holder.itemView.context, DetailActiviy::class.java)
            intent.putExtra(DetailActiviy.EXTRA_USER, userList[position])
            holder.itemView.context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int = userList.size

    inner class FollowerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        @SuppressLint("SetTextI18n")
        fun bind(list: FollowerModel) {
            with(itemView) {
                Glide.with(itemView.context)
                    .load(list.avatar)
                    .placeholder(R.drawable.ic_baseline_face_24)
                    .error(R.drawable.ic_baseline_face_24)
                    .into(iv_avatar_follower)
                tv_fullname_follower.text = list.username
                tv_id_follower.text = list.id.toString()
                urlTv_follower.text = "url: " + list.url
            }
        }
    }

}