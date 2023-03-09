package com.example.noteapp.features.note.data.repositories

import com.example.noteapp.features.note.data.datasources.NoteDao
import com.example.noteapp.features.note.domain.entities.Note
import com.example.noteapp.features.note.domain.repositories.NoteRepository
import kotlinx.coroutines.flow.Flow

class NoteRepositoryImpl(private val noteDao: NoteDao) : NoteRepository {
    override fun getNotes(): Flow<List<Note>> {
        return noteDao.getNotes()
    }

    override suspend fun getNoteById(id: Int): Note? {
        return noteDao.getNoteById(id)
    }

    override suspend fun insertNote(note: Note) {
        noteDao.insertNote(note)
    }

    override suspend fun deleteNote(note: Note) {
        noteDao.deleteNote(note)
    }
}