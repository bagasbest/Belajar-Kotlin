package com.bagasbest.berepo.adapter

import android.annotation.SuppressLint
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bagasbest.berepo.DetailActiviy
import com.bagasbest.berepo.R
import com.bagasbest.berepo.model.FollowingModel
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.list_user_following.view.*

class FollowingAdapter :
    RecyclerView.Adapter<FollowingAdapter.FollowingViewHolder>() {


    private val userList = ArrayList<FollowingModel>()
    fun setData(items: ArrayList<FollowingModel>) {
        userList.clear()
        userList.addAll(items)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FollowingViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_user_following, parent, false)
        return FollowingViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: FollowingViewHolder, position: Int) {
        holder.bind(userList[position])

        holder.itemView.setOnClickListener {
            val intent = Intent(holder.itemView.context, DetailActiviy::class.java)
            intent.putExtra(DetailActiviy.OPTION, "following")
            intent.putExtra(DetailActiviy.EXTRA_USER, userList[position])
            holder.itemView.context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int = userList.size

    inner class FollowingViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        @SuppressLint("SetTextI18n")
        fun bind(list: FollowingModel) {
            with(itemView) {
                Glide.with(itemView.context)
                    .load(list.avatar)
                    .placeholder(R.drawable.ic_baseline_face_24)
                    .error(R.drawable.ic_baseline_face_24)
                    .into(iv_avatar_following)
                tv_fullname_following.text = list.username
                tv_id_following.text = list.id.toString()
                urlTv_following.text = "url: " + list.url
            }
        }

    }

}