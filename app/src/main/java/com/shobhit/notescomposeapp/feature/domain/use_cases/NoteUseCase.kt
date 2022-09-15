package com.shobhit.notescomposeapp.feature.domain.use_cases

import com.shobhit.notescomposeapp.feature.domain.model.Note
import com.shobhit.notescomposeapp.feature.domain.util.NoteOrder


class NoteUseCase(private val deleteNoteUseCase: DeleteNoteUseCase,private val getNoteUseCase: GetNoteUseCase,
    private val getNotesUseCase: GetNotesUseCase,
private val insertNoteUseCase: InsertNoteUseCase,private val updateNoteUseCase: UpdateNoteUseCase) {

    suspend fun insertNote(note: Note){
        insertNoteUseCase(note)
    }

    suspend fun updateNote(note: Note){
        updateNoteUseCase(note)
    }

    suspend fun getNoteById(noteId:Int) = getNoteUseCase(noteId)

    fun getNotes(noteOrder: NoteOrder) =  getNotesUseCase(noteOrder)

    suspend fun deleteNote(note: Note){
        deleteNoteUseCase(note)
    }

}