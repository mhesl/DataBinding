package com.example.databinding

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.Navigation
import com.example.databinding.room.Note
import com.example.databinding.room.NoteDataBase
import kotlinx.android.synthetic.main.fragment_add_note.*
import kotlinx.coroutines.launch


class AddNoteFragment : BaseFragment() {

    private var note: Note? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_note, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        arguments?.let {
            note = AddNoteFragmentArgs.fromBundle(it).note
            titleET.setText(note?.title)
            bodyET.setText(note?.body)
        }


        buttonSave.setOnClickListener {
            val noteTitle:String = titleET.text.toString().trim()
            val noteBody :String = bodyET.text.toString().trim()

            if(noteTitle.isNullOrEmpty()){
                titleET.error = "Title error"
                return@setOnClickListener
            }
            if(noteBody.isNullOrEmpty()){
                titleET.error = "body error"
                return@setOnClickListener
            }
            val newNote =Note(noteTitle , noteBody)

            launch {
                if(note == null)
                    saveNote(newNote)
                else
                    updateNote(newNote)
            }



            navigateBack()
        }
    }


    private suspend fun updateNote(newNote :Note){
        newNote.id = note!! .id
        context?.let {
            NoteDataBase(it).getNodeDao().updateNote(newNote)
            Toast.makeText(it , "Note updated" ,  Toast.LENGTH_LONG).show()
        }
    }

    private suspend fun saveNote(note:Note){
            context?.let {
                NoteDataBase(it).getNodeDao().addNote(note)
            }
    }
    private fun navigateBack(){
        val action =  AddNoteFragmentDirections.actionSaveNote()
        Navigation.findNavController(buttonSave).navigate(action)
    }


}