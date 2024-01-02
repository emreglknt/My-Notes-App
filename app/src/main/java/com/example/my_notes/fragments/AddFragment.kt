package com.example.my_notes.fragments

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
import com.example.my_notes.MainActivity
import com.example.my_notes.R
import com.example.my_notes.databinding.FragmentAddBinding
import com.example.my_notes.model.NoteData
import com.example.my_notes.viewmodel.NoteViewModel

class AddFragment : Fragment(R.layout.fragment_add),MenuProvider {

    private var addFragmentAddBinding : FragmentAddBinding?=null
    private val binding get() = addFragmentAddBinding!!

    private lateinit var   notesViewModel: NoteViewModel

    private lateinit var addNoteView:View

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        addFragmentAddBinding=FragmentAddBinding.inflate(inflater,container,false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val menuHost: MenuHost =requireActivity()
        menuHost.addMenuProvider(this,viewLifecycleOwner, Lifecycle.State.RESUMED)
        notesViewModel=(activity as MainActivity).noteViewModel
        addNoteView = view
    }


    private fun saveNote(view: View){
        val noteTitle = binding.addNoteTitle.text.toString().trim()
        val noteDesc= binding.addNoteDesc.text.toString().trim()

        if(noteTitle.isNotEmpty()){
            val note = NoteData(0,noteTitle,noteDesc)
            notesViewModel.addNote(note)

            Toast.makeText(addNoteView.context,"Note Added Successfully",Toast.LENGTH_SHORT).show()
            view.findNavController().popBackStack(R.id.homeFragment,false)
        }else{
            Toast.makeText(addNoteView.context,"Please enter note title",Toast.LENGTH_SHORT).show()
        }

    }

    override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
      menu.clear()
        menuInflater.inflate(R.menu.add_note,menu)
    }

    override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
      return when(menuItem.itemId){
          R.id.saveMenu->{
              saveNote(addNoteView)
              true

      }
          else-> false
      }
    }


    override fun onDestroy() {
        super.onDestroy()
        addFragmentAddBinding = null
    }



}