package com.shobhit.notescomposeapp.feature.presentation.notes_list

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shobhit.notescomposeapp.feature.domain.model.Note
import com.shobhit.notescomposeapp.feature.domain.use_cases.GetNoteUseCase
import com.shobhit.notescomposeapp.feature.domain.repository.Repository
import com.shobhit.notescomposeapp.feature.domain.use_cases.NoteUseCase
import com.shobhit.notescomposeapp.feature.domain.util.NoteOrder
import com.shobhit.notescomposeapp.feature.domain.util.NoteType
import com.shobhit.notescomposeapp.feature.domain.util.OrderType
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NotesViewModel @Inject constructor(private val noteUseCase: NoteUseCase): ViewModel() {

    private val _notes:MutableState<List<Note>>  = mutableStateOf(emptyList())
    val notes:State<List<Note>> = _notes

    private val _showOrderSection:MutableStateFlow<Boolean> = MutableStateFlow(false)
    val showOrderSection:StateFlow<Boolean> = _showOrderSection

    private var _noteOrder:MutableStateFlow<NoteOrder> = MutableStateFlow(NoteOrder(NoteType.TITLE,
        OrderType.ASCENDING))
    val noteOrder:StateFlow<NoteOrder> = _noteOrder

    var recentlyDeleteNote:Note? = null

    init {
        updateNoteOrder(NoteOrder(NoteType.DATE, OrderType.DESCENDING))
    }

    fun toggleOrderSection(){
        _showOrderSection.value = !_showOrderSection.value
    }

    fun updateNoteOrder(noteOrder: NoteOrder){
       _noteOrder.value = noteOrder
        viewModelScope.launch {
            noteUseCase.getNotes(noteOrder).collect{
                _notes.value = it
            }
        }
    }

    fun deleteNote(note: Note){
        viewModelScope.launch {
            recentlyDeleteNote=note
            noteUseCase.deleteNote(note)
        }
    }

    fun insertNote(){
        viewModelScope.launch {
           recentlyDeleteNote?.let {
              noteUseCase.insertNote(it)
               recentlyDeleteNote = null
           }
        }
    }


}