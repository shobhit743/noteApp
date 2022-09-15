package com.shobhit.notescomposeapp.feature.domain.use_cases

import com.shobhit.notescomposeapp.feature.domain.model.Note
import com.shobhit.notescomposeapp.feature.domain.repository.Repository

class InsertNoteUseCase(private val repository: Repository){
    operator suspend fun invoke(note:Note){
        repository.insertNote(note)
    }
}