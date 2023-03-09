package com.example.noteapp.features.note.presentation

import com.example.noteapp.features.note.domain.entities.Note
import com.example.noteapp.features.note.domain.utils.NoteOrder
import com.example.noteapp.features.note.domain.utils.OrderType

data class NotesState(
    val notes: List<Note> = emptyList(),
    val noteOrder: NoteOrder = NoteOrder.Date(OrderType.Descending),
    val isOrderSectionVisible: Boolean = false,
)