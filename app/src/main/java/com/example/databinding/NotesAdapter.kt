package com.example.databinding

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.HorizontalScrollView
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.databinding.room.Note
import kotlinx.android.synthetic.main.item_note.view.*

class NotesAdapter (val notes : List<Note>): RecyclerView.Adapter<NotesAdapter.NoteViewHolder>(){

    class NoteViewHolder(val view: View) : RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val inflater :LayoutInflater = LayoutInflater.from(parent.context)
        val view :View = inflater.inflate(R.layout.item_note ,parent ,  false)
        return NoteViewHolder(view)
    }

    override fun getItemCount() = notes.size

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        holder.view.titleTV.text = notes[position].title
        holder.view.bodyTV.text = notes[position].body
        holder.view.setOnClickListener {
            val action = HomeFragmentDirections.actionAddNote()
            action.note = notes[position]
            Navigation.findNavController(it).navigate(action)
        }

    }
}