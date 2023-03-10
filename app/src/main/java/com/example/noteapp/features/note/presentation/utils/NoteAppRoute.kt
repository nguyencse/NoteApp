package com.example.noteapp.features.note.presentation.utils

sealed class NoteAppRoute(val routeName: String) {
    object NotesPage: NoteAppRoute(routeName = "notes_page")
    object AddEditPage: NoteAppRoute(routeName = "add_edit_page")
}