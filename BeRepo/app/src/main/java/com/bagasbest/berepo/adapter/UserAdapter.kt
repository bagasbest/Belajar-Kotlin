package com.bagasbest.berepo.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bagasbest.berepo.DetailActiviy
import com.bagasbest.berepo.R
import com.bagasbest.berepo.model.UserModel
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.github_user_list_item.view.*

class UserAdapter(private val userList: List<UserModel>) :
    RecyclerView.Adapter<UserAdapter.UserAdapterViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): UserAdapterViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.github_user_list_item, parent, false)
        return UserAdapterViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: UserAdapter.UserAdapterViewHolder, position: Int) {
        holder.bind(userList[position])

        holder.itemView.setOnClickListener {
            val intent = Intent (holder.itemView.context, DetailActiviy::class.java)
            intent.putExtra(DetailActiviy.EXTRA_USER, userList[position])
            holder.itemView.context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return userList.size
    }

    inner class UserAdapterViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

        fun bind (list: UserModel) {
            with(itemView) {
                Glide.with(itemView.context)
                    .load(list.avatar)
                    .error(R.drawable.ic_baseline_face_24)
                    .into(iv_avatar)
                tv_fullname.text = list.name
                tv_username.text = list.username
                tv_company.text = list.company
            }
        }
    }


}