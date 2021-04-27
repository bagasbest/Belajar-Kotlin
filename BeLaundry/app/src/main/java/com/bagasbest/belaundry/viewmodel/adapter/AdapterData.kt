package com.bagasbest.belaundry.viewmodel.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bagasbest.belaundry.databinding.ItemLaundryBinding
import com.bagasbest.belaundry.model.model.LaundryModel


class AdapterData : RecyclerView.Adapter<AdapterData.AdapterViewHolder>(){


    var listData = mutableListOf<LaundryModel>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdapterViewHolder {
        val binding = ItemLaundryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return AdapterViewHolder(binding)
    }

    override fun onBindViewHolder(holder: AdapterViewHolder, position: Int) {
        holder.bind(listData[position])
    }

    override fun getItemCount(): Int = this.listData.size

    inner class AdapterViewHolder(private val binding: ItemLaundryBinding) : RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SetTextI18n")
        fun bind(laundry: LaundryModel) {
            with(binding) {
                binding.tvTitle.text = laundry.name
                binding.tvId.text = laundry.id.toString()
                binding.tvAddress.text = laundry.address
                binding.tvPhone.text = laundry.phone
                binding.tvPrice.text = "Rp. " + laundry.price

                itemView.setOnClickListener {
                    Toast.makeText(itemView.context, laundry.name, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}