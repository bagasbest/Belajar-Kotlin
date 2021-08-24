package com.bagasbest.jaramba.viewmodel.adapter

import android.annotation.SuppressLint
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bagasbest.jaramba.R
import com.bagasbest.jaramba.databinding.ItemHistoryBinding
import com.bagasbest.jaramba.model.model.HistoryModel
import com.bagasbest.jaramba.view.activity.ChatActivity
import com.bagasbest.jaramba.view.activity.HistoryDetailActivity

class HistoryAdapter : RecyclerView.Adapter<HistoryAdapter.HistoryViewHolder>() {

    private val historyList = ArrayList<HistoryModel>()
    fun setData(items: ArrayList<HistoryModel>) {
        historyList.clear()
        historyList.addAll(items)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryViewHolder {
        val binding = ItemHistoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return HistoryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: HistoryViewHolder, position: Int) {
        holder.bind(historyList[position])
    }

    override fun getItemCount(): Int = historyList.size


    inner class HistoryViewHolder(private val binding: ItemHistoryBinding) : RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SetTextI18n", "ResourceAsColor")
        fun bind(historyModel: HistoryModel) {
            with(binding) {
                transportMode.text = historyModel.transportationMode
                destination.text = "Destinasi: " + historyModel.destination
                rating.text = historyModel.rating.toString()
                status.text = "Status: " + historyModel.status


                if(historyModel.status == "Selesai") {
                    chatDriver.visibility = View.INVISIBLE
                    view2.background = ContextCompat.getDrawable(itemView.context, R.drawable.ic_rounded_2)
                    transportMode.setTextColor(R.color.black)
                    destination.setTextColor(R.color.black)
                }



                itemView.setOnClickListener {
                    val intent = Intent(it.context, HistoryDetailActivity::class.java)
                    intent.putExtra(HistoryDetailActivity.EXTRA_HISTORY, historyModel)
                    itemView.context.startActivity(intent)
                }

                chatDriver.setOnClickListener {
                    val intent = Intent(it.context, ChatActivity::class.java)
                    intent.putExtra(ChatActivity.EXTRA_TRIP_ID, historyModel.tripId)
                    itemView.context.startActivity(intent)
                }
            }
        }

    }


}