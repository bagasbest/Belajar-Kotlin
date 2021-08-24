package com.bagasbest.jaramba.viewmodel.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bagasbest.jaramba.databinding.ItemChatLeftBinding
import com.bagasbest.jaramba.databinding.ItemChatRightBinding
import com.bagasbest.jaramba.model.model.MessageModel

class ChatAdapter(private val uid: String) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val listMessage = ArrayList<MessageModel>()
    fun setData(items: ArrayList<MessageModel>) {
        listMessage.clear()
        listMessage.addAll(items)
        notifyDataSetChanged()
    }


    companion object {
        private const val MSG_TYPE_LEFT = 0
        private const val MSG_TYPE_RIGHT = 1
        private val TAG = ChatAdapter::class.java.simpleName
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if(viewType == MSG_TYPE_RIGHT) {
            val binding = ItemChatRightBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            MessageViewHolderRight(binding)
        } else {
            val binding = ItemChatLeftBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            MessageViewHolderLeft(binding)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        if(listMessage[position].uid.equals(uid)) {
            (holder as MessageViewHolderRight).bind(listMessage[position])
        } else{
            (holder as MessageViewHolderLeft).bind(listMessage[position])
        }

    }

    override fun getItemCount(): Int = listMessage.size

    override fun getItemViewType(position: Int) : Int{
        //get currently signed user

        return if(listMessage[position].uid.equals(uid)){
            MSG_TYPE_RIGHT
        } else {
            MSG_TYPE_LEFT
        }
    }

    inner class MessageViewHolderRight(private val binding: ItemChatRightBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(list: MessageModel) {
            binding.messageTv.text = list.message
            binding.timeTv.text = list.time
        }
    }

    inner class MessageViewHolderLeft(private val binding: ItemChatLeftBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(list: MessageModel) {
            binding.messageTv.text = list.message
            binding.timeTv.text = list.time
        }
    }


}