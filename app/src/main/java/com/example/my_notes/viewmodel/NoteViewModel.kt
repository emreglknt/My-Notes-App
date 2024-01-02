package com.example.my_notes.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.my_notes.model.NoteData
import com.example.my_notes.repo.NoteRepo
import kotlinx.coroutines.launch




//Bu sınıfın temel amacı, kullanıcı arayüzünden gelen istekleri almak,
// bu istekleri NoteRepo sınıfı  aracılığı ile  veritabanı işlemlerine çevirmek ve sonuçları kullanıcı arayüzüne geri iletmektir.
// Aynı zamanda, yaşam döngüsü bilinci (lifecycle awareness) sağlar, bu da özellikle Android uygulamalarında uygundur.
// Bu sayede, UI'nin bağımsızlığı artar ve uygulamanın yapılandırılmasını kolaylaştırır.


class NoteViewModel(app:Application, private val noteRepo:NoteRepo):AndroidViewModel(app) {


    fun addNote(note:NoteData)=
        viewModelScope.launch {
            noteRepo.insertNote(note)
        }


    fun deleteNote(note:NoteData)=
        viewModelScope.launch {
            noteRepo.deleteNote(note)
        }


    fun updateNote(note:NoteData)=
        viewModelScope.launch {
            noteRepo.updateNote(note)
        }


    fun getAllNotes() = noteRepo.getAllNotes()

    fun searchNote(query:String?) =
        noteRepo.searchNote(query)



}