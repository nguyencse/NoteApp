package com.example.noteapp.features.note.presentation.notes

import com.example.noteapp.features.note.domain.entities.Note
import com.example.noteapp.features.note.domain.utils.NoteOrder

sealed class NoteEvent {
    data class Order(val noteOrder: NoteOrder): NoteEvent()
    data class DeleteNote(val note: Note): NoteEvent()
    object RestoreNote: NoteEvent()
    object ToggleOrderSection: NoteEvent()
}