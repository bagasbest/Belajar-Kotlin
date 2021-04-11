package com.bagasbest.fundamental2.academy.ui.reader.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bagasbest.fundamental2.academy.data.ModuleEntity
import com.bagasbest.fundamental2.databinding.ItemsModuleListCustomBinding

class ModuleListAdapter internal constructor(private val listener: MyAdapterClickListener) : RecyclerView.Adapter<ModuleListAdapter.ModuleViewHolder>(){

    private val listModules = ArrayList<ModuleEntity>()

    fun setModules(modules: List<ModuleEntity>?) {
        if(modules == null) return
        listModules.clear()
        listModules.addAll(modules)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ModuleViewHolder {
        val binding = ItemsModuleListCustomBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ModuleViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ModuleViewHolder, position: Int) {
        val module = listModules[position]
        holder.bind(module)
        holder.itemView.setOnClickListener {
            listener.onItemClicked(holder.adapterPosition, listModules[holder.adapterPosition].moduleId)
        }
    }

    override fun getItemCount(): Int = listModules.size


    inner class ModuleViewHolder(private val binding: ItemsModuleListCustomBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(module: ModuleEntity) {
            binding.textModuleTitle.text = module.title
        }
    }
}

interface MyAdapterClickListener {
     fun onItemClicked(adapterPosition: Int, moduleId: String)
}

