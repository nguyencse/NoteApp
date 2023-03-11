package com.example.noteapp.features.note.domain.usecases

import com.example.noteapp.features.note.domain.entities.Note
import com.example.noteapp.features.note.domain.repositories.NoteRepository

class GetNoteByIdUseCase(private val repository: NoteRepository) {
    suspend operator fun invoke(id: Int): Note? {
        return repository.getNoteById(id)
    }
}