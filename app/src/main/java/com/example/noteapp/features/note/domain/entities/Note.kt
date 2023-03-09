package com.example.noteapp.features.note.domain.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.noteapp.ui.theme.*

@Entity
data class Note(
    val title: String,
    val content: String,
    val timestamp: Long,
    val color: Int,
    @PrimaryKey val id: Int? = null,
) {
    companion object {
        val listOfColors = listOf(RedOrange, LightGreen, Violet, BabyBlue, RedPink)
    }
}

class InvalidNoteException(message: String): Exception(message)