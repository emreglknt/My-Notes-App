package com.example.my_notes

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.example.my_notes.database.NoteDatabase
import com.example.my_notes.repo.NoteRepo
import com.example.my_notes.viewmodel.NoteViewModel
import com.example.my_notes.viewmodel.NoteViewModelFactory

class MainActivity : AppCompatActivity() {

    lateinit var noteViewModel: NoteViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupViewModel()
    }


    private fun setupViewModel(){
        val noteRepo = NoteRepo(NoteDatabase(this))
        val viewmodelfactory = NoteViewModelFactory(application,noteRepo)

        noteViewModel = ViewModelProvider(this,viewmodelfactory)[NoteViewModel::class.java]

    }


}