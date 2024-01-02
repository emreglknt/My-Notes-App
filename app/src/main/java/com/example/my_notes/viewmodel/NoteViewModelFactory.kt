package com.example.my_notes.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.my_notes.repo.NoteRepo

class NoteViewModelFactory(val app:Application,private val noteRepo: NoteRepo)
    :ViewModelProvider.Factory {


    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        @Suppress("UNCHECKED_CAST")
        return NoteViewModel(app, noteRepo) as T
    }



}