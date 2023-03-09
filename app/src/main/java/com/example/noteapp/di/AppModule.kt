package com.example.noteapp.di

import android.app.Application
import androidx.room.Room
import com.example.noteapp.features.note.data.datasources.NoteDatabase
import com.example.noteapp.features.note.data.repositories.NoteRepositoryImpl
import com.example.noteapp.features.note.domain.repositories.NoteRepository
import com.example.noteapp.features.note.domain.usecases.AddNoteUseCase
import com.example.noteapp.features.note.domain.usecases.DeleteNoteUseCase
import com.example.noteapp.features.note.domain.usecases.GetNoteUseCase
import com.example.noteapp.features.note.domain.usecases.NoteUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideNoteDatabase(app: Application): NoteDatabase {
        return Room.databaseBuilder(
            app,
            NoteDatabase::class.java,
            NoteDatabase.DATABASE_NAME,
        ).build()
    }

    @Provides
    @Singleton
    fun provideNoteRepository(noteDatabase: NoteDatabase): NoteRepository {
        return NoteRepositoryImpl(noteDatabase.noteDao)
    }

    @Provides
    @Singleton
    fun provideNoteUseCase(repository: NoteRepository): NoteUseCase {
        return NoteUseCase(
            getNotes = GetNoteUseCase(repository),
            deleteNote = DeleteNoteUseCase(repository),
            addNote = AddNoteUseCase(repository)
        )
    }
}