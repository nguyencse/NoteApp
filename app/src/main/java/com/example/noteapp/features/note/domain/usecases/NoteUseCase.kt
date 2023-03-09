package com.example.noteapp.features.note.domain.usecases

data class NoteUseCase(
    val getNotes: GetNoteUseCase,
    val deleteNote: DeleteNoteUseCase,
    val addNote: AddNoteUseCase,
)
