package com.shobhit.notescomposeapp.feature.presentation.add_edit_note

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shobhit.notescomposeapp.feature.domain.model.Note
import com.shobhit.notescomposeapp.feature.domain.repository.Repository
import com.shobhit.notescomposeapp.feature.domain.use_cases.NoteUseCase
import com.shobhit.notescomposeapp.feature.presentation.utils.Event
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddEditNotesViewModel @Inject constructor(val noteUseCase: NoteUseCase ,savedStateHandle: SavedStateHandle):ViewModel() {

    private var  _name:MutableState<String> = mutableStateOf("")
    val name:State<String> = _name

    private var  _desc:MutableState<String> = mutableStateOf("")
    val desc:State<String> = _desc

    private var  _color:MutableState<Color> = mutableStateOf(Note.colorsList[0])
    val color:State<Color> = _color

    var currentNoteId = -1

    init {
        savedStateHandle.get<Int>("noteId")?.let {noteId ->
            if(noteId!=-1){
                viewModelScope.launch {
                    val note = noteUseCase.getNoteById(noteId)
                    currentNoteId = noteId
                    note?.let {
                        _name.value = note.title
                        _desc.value = note.content
                        _color.value = Color(note.color)
                    }
                }
            }
        }

    }



    fun updateEvent(event: Event){
        when(event){
            is Event.TitleEvent ->{
                _name.value = event.title
            }
            is Event.DescEvent ->{
                _desc.value = event.desc
            }
            is Event.ColorEvent -> {
                _color.value = Color(event.color)
            }
            is Event.SaveNote ->{
                viewModelScope.launch(Dispatchers.IO) {
                    val note = Note(event.title,event.desc,event.color)
                    if(currentNoteId==-1) {
                        noteUseCase.insertNote(note)
                    } else {
                        noteUseCase.updateNote(note.copy(id = currentNoteId))
                    }
                }

            }
        }
    }


}