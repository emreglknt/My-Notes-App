package com.example.my_notes.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.my_notes.model.NoteData


@Dao
interface NoteDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNote(note: NoteData)

    @Update
    suspend fun updateNote(note: NoteData)

    @Delete
    suspend fun deleteNote(note: NoteData)

    // Read   descending order
    @Query("SELECT * FROM NOTES ORDER BY id DESC")
    fun getAllNotes():LiveData<List<NoteData>>


    @Query("SELECT * FROM NOTES WHERE noteTitle LIKE :query OR noteDesc LIKE:query")
        fun searchNote(query: String?):LiveData<List<NoteData>>


}