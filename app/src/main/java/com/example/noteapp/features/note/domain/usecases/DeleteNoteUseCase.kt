package com.example.noteapp.features.note.domain.usecases

import com.example.noteapp.features.note.domain.entities.Note
import com.example.noteapp.features.note.domain.repositories.NoteRepository

class DeleteNoteUseCase(private val repository: NoteRepository) {
    suspend operator fun invoke(note: Note) {
        repository.deleteNote(note)
    }
}