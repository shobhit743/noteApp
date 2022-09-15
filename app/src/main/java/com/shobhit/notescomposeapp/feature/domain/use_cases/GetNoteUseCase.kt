package com.shobhit.notescomposeapp.feature.domain.use_cases

import com.shobhit.notescomposeapp.feature.domain.util.NoteOrder
import com.shobhit.notescomposeapp.feature.domain.util.NoteType
import com.shobhit.notescomposeapp.feature.domain.util.OrderType
import com.shobhit.notescomposeapp.feature.domain.repository.Repository
import com.shobhit.notescomposeapp.feature.domain.model.Note
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetNoteUseCase(private val repository: Repository) {

    operator suspend fun invoke(noteId:Int) =  repository.getNoteById(noteId)
}