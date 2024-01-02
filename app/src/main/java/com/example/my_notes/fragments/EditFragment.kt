package com.example.my_notes.fragments

import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.lifecycle.Lifecycle
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import com.example.my_notes.MainActivity
import com.example.my_notes.R
import com.example.my_notes.databinding.FragmentAddBinding
import com.example.my_notes.databinding.FragmentEditBinding
import com.example.my_notes.model.NoteData
import com.example.my_notes.viewmodel.NoteViewModel


class EditFragment : Fragment(R.layout.fragment_edit), MenuProvider {


    private var editBinding : FragmentEditBinding?=null
    private val binding get() = editBinding!!

    private lateinit var notesViewModel: NoteViewModel
    private lateinit var currentNote:NoteData



    private val args: EditFragmentArgs by navArgs()



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
       editBinding=FragmentEditBinding.inflate(inflater,container,false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val menuHost: MenuHost =requireActivity()
        menuHost.addMenuProvider(this,viewLifecycleOwner, Lifecycle.State.RESUMED)
        notesViewModel=(activity as MainActivity).noteViewModel
        currentNote = args.note!!

        binding.editNoteTitle.setText(currentNote.noteTitle)
        binding.editNoteDesc.setText(currentNote.noteDesc)

        binding.editNoteFab.setOnClickListener{
            val notetitle = binding.editNoteTitle.text.toString().trim()
            val notedesc = binding.editNoteDesc.text.toString().trim()

            if(notetitle.isNotEmpty()){
                val note = NoteData(currentNote.id,notetitle,notedesc)
                notesViewModel.updateNote(note)
                view.findNavController().popBackStack(R.id.homeFragment,false)
            } else{
                Toast.makeText(context,"PLease enter note title",Toast.LENGTH_SHORT).show()
            }

        }


    }


    private fun deletNote(){
        AlertDialog.Builder(activity).apply {
            setTitle("Delete Note")
            setMessage("Are you sure delete this note ?")
            setPositiveButton("Delete"){_,_ ->
                notesViewModel.deleteNote(currentNote)
                Toast.makeText(context,"Note Deleted",Toast.LENGTH_SHORT).show()
                view?.findNavController()?.popBackStack(R.id.homeFragment,false)
            }
            setNegativeButton("Cancel",null)
        }.create().show()
    }

    override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
     menu.clear()
        menuInflater.inflate(R.menu.edit_note,menu)
    }

    override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
        return when(menuItem.itemId){
            R.id.deletemenu->{
                deletNote()
                true
            }else->false
        }
    }


    override fun onDestroy() {
        super.onDestroy()
        editBinding=null
    }


}