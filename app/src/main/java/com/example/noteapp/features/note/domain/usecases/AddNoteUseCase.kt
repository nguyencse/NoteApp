package com.example.noteapp.features.note.domain.usecases

import com.example.noteapp.features.note.domain.entities.InvalidNoteException
import com.example.noteapp.features.note.domain.entities.Note
import com.example.noteapp.features.note.domain.repositories.NoteRepository

class AddNoteUseCase(private val repository: NoteRepository) {

    @Throws(InvalidNoteException::class)
    suspend operator fun invoke(note: Note) {
        if (note.title.isBlank()) {
            throw InvalidNoteException("The title of the note can't be empty.")
        } else if (note.content.isBlank()) {
            throw InvalidNoteException("The content of the note can't be empty.")
        }
        repository.insertNote(note)
    }
}