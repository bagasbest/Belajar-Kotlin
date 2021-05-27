package com.bagasbest.fundamental2.room.ui.insert.adapter

import android.app.Activity
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bagasbest.fundamental2.databinding.ItemNoteBinding
import com.bagasbest.fundamental2.room.database.Note
import com.bagasbest.fundamental2.room.helper.NoteDiffCallback
import com.bagasbest.fundamental2.room.ui.main.MyRoomNoteAddUpdateActivity

class NoteAdapter internal constructor(private val activity: Activity) : RecyclerView.Adapter<NoteAdapter.NoteViewHolder>() {

  private val listNote = ArrayList<Note>()
    
    fun setData (items: List<Note>) {
        val diffCallback = NoteDiffCallback(this.listNote, items)
        val diffResult = DiffUtil.calculateDiff(diffCallback)
        
        this.listNote.clear()
        this.listNote.addAll(items)
        diffResult.dispatchUpdatesTo(this)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val binding = ItemNoteBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NoteViewHolder(binding)
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        holder.bind(listNote[position])
    }

    override fun getItemCount(): Int = listNote.size

    inner class NoteViewHolder(private val binding : ItemNoteBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(note: Note) {
            with(binding) {
                tvItemTitle.text = note.title
                tvItemDescription.text = note.description
                tvItemDate.text = note.date
                cvItemNote.setOnClickListener {
                    val intent = Intent(activity, MyRoomNoteAddUpdateActivity::class.java)
                    intent.putExtra(MyRoomNoteAddUpdateActivity.EXTRA_POSITION, adapterPosition)
                    intent.putExtra(MyRoomNoteAddUpdateActivity.EXTRA_NOTE, note)
                    activity.startActivityForResult(intent, MyRoomNoteAddUpdateActivity.REQUEST_UPDATE)
                }
            }
        }

    }
}