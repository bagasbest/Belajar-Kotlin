package com.bagasbest.berepo.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bagasbest.berepo.DetailActiviy
import com.bagasbest.berepo.R
import com.bagasbest.berepo.model.FavoriteModel
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.list_user_favorite.view.*

class FavoriteAdapter : RecyclerView.Adapter<FavoriteAdapter.FavoriteViewHolder>() {

    private val favoriteList = ArrayList<FavoriteModel>()
    fun setData(items: ArrayList<FavoriteModel>) {
        favoriteList.clear()
        favoriteList.addAll(items)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_user_favorite, parent, false)
        return FavoriteViewHolder(view)
    }

    override fun onBindViewHolder(holder: FavoriteViewHolder, position: Int) {
        holder.bind(favoriteList[position])
        holder.itemView.setOnClickListener {
            val intent = Intent(holder.itemView.context, DetailActiviy::class.java)
            intent.putExtra(DetailActiviy.OPTION, "favorite")
            intent.putExtra(DetailActiviy.EXTRA_USER, favoriteList[position])
            holder.itemView.context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int = favoriteList.size

    inner class FavoriteViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(favorite: FavoriteModel) {
            with(itemView) {
                Glide.with(itemView.context)
                    .load(favorite.avatar)
                    .placeholder(R.drawable.ic_baseline_face_24)
                    .error(R.drawable.ic_baseline_face_24)
                    .into(iv_avatar_favorite)
                tv_fullname_favorite.text = favorite.username
                tv_id_favorite.text = favorite.id.toString()
                urlTv_favorite.text = favorite.url
            }
        }

    }
}