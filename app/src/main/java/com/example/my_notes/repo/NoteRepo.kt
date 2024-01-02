package com.example.my_notes.repo

import androidx.room.Query
import com.example.my_notes.database.NoteDatabase
import com.example.my_notes.model.NoteData





// Bu NoteRepo (Repository) sınıfı,
// veritabanı işlemlerini yöneten ve veritabanı ile arayüz sağlayan bir ara katman (repository) sınıfıdır.
// Bu tür bir sınıf, uygulama katmanları arasında bir bağlantı görevi görerek veritabanı işlemlerini soyutlar
// ve diğer katmanlara daha kolay erişim sağlar.


class NoteRepo(private val db:NoteDatabase){

    suspend fun insertNote(note:NoteData) = db.getNoteDAO().insertNote(note)
    suspend fun  deleteNote(note: NoteData) = db.getNoteDAO().deleteNote(note)
    suspend fun  updateNote(note: NoteData) = db.getNoteDAO().updateNote(note)

    fun getAllNotes() = db.getNoteDAO().getAllNotes()
    fun searchNote(query: String?) = db.getNoteDAO().searchNote(query)


}