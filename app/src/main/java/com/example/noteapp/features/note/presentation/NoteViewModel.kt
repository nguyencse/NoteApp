package com.example.noteapp.features.note.presentation

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.noteapp.features.note.domain.entities.Note
import com.example.noteapp.features.note.domain.usecases.NoteUseCase
import com.example.noteapp.features.note.domain.utils.NoteOrder
import com.example.noteapp.features.note.domain.utils.OrderType
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NoteViewModel @Inject constructor(
    private val noteUseCase: NoteUseCase
) : ViewModel() {

    private val _state = mutableStateOf(NotesState())
    val state: State<NotesState> = _state

    private var _recentDeletedNote: Note? = null
    private var getNotesJob: Job? = null

    init {
        getNotes(NoteOrder.Date(OrderType.Descending))
    }

    fun onEvent(event: NoteEvent) {
        when (event) {
            is NoteEvent.Order -> {
                if (state.value.noteOrder::class == event.noteOrder::class && state.value.noteOrder.orderType == event.noteOrder.orderType) {
                    return
                }
                noteUseCase.getNotes(event.noteOrder)
            }
            is NoteEvent.DeleteNote -> {
                viewModelScope.launch {
                    noteUseCase.deleteNote(event.note)
                    _recentDeletedNote = event.note
                }
            }
            is NoteEvent.RestoreNote -> {
                viewModelScope.launch {
                    noteUseCase.addNote(_recentDeletedNote ?: return@launch)
                    _recentDeletedNote = null
                }
            }
            is NoteEvent.ToggleOrderSection -> {
                _state.value =
                    _state.value.copy(isOrderSectionVisible = !_state.value.isOrderSectionVisible)
            }
        }
    }

    fun getNotes(noteOrder: NoteOrder) {
        getNotesJob?.cancel()
        getNotesJob = noteUseCase.getNotes(noteOrder).onEach {
            _state.value = _state.value.copy(notes = it, noteOrder = noteOrder)
        }.launchIn(viewModelScope)
    }
}