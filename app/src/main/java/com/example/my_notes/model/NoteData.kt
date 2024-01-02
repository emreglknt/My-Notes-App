package com.example.my_notes.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

    @Entity(tableName = "notes")
    @Parcelize
        data class NoteData(

            @PrimaryKey(autoGenerate = true)
            val id :Int,
            val noteTitle : String,
            val noteDesc : String

        ):Parcelable
