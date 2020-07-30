package com.example.databinding

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.databinding.databinding.ItemNoteBinding
import com.example.databinding.room.Note
import kotlinx.android.synthetic.main.item_note.view.*

class NotesAdapter (val notes : List<Note>): RecyclerView.Adapter<NotesAdapter.NoteViewHolder>() , NoteClickListener{

    class NoteViewHolder(val view: ItemNoteBinding) : RecyclerView.ViewHolder(view.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val inflater :LayoutInflater = LayoutInflater.from(parent.context)
        val view :ItemNoteBinding = DataBindingUtil.inflate<ItemNoteBinding>(inflater , R.layout.item_note , parent , false)
        return NoteViewHolder(view)
    }

    override fun getItemCount() = notes.size

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        holder.view.note = notes[position]
        holder.view.listener = this
//        holder.view.titleTV.text = notes[position].title
//        holder.view.bodyTV.text = notes[position].body
        holder.view.root.setOnClickListener {

        }

    }

    override fun noteClicked(view: View) {
        val id :Int? = view.idTV.text.toString().toIntOrNull()
        var note :Note? = null
        for (n : Note in notes)
            if(n.id == id){
                note = n
                break
            }
        note?.let {
            val action = HomeFragmentDirections.actionAddNote()
            action.note = note
            Navigation.findNavController(view).navigate(action)
        }
    }
}