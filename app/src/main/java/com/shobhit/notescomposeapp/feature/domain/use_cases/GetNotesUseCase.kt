package com.shobhit.notescomposeapp.feature.domain.use_cases

import com.shobhit.notescomposeapp.feature.domain.model.Note
import com.shobhit.notescomposeapp.feature.domain.repository.Repository
import com.shobhit.notescomposeapp.feature.domain.util.NoteOrder
import com.shobhit.notescomposeapp.feature.domain.util.NoteType
import com.shobhit.notescomposeapp.feature.domain.util.OrderType
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetNotesUseCase(private val repository: Repository) {

    operator fun invoke(noteOrder: NoteOrder): Flow<List<Note>> {
        return repository.getNotes().map { it ->
            when(noteOrder.orderType) {
                OrderType.ASCENDING -> {
                    when(noteOrder.noteType){
                        NoteType.TITLE -> {
                            it.sortedBy { it.title }
                        }
                        NoteType.DATE -> {
                            it.sortedBy { it.date }
                        }
                        NoteType.COLOR -> {
                            it.sortedBy { it.color }
                        }
                    }
                }
                OrderType.DESCENDING -> {
                    when(noteOrder.noteType){
                        NoteType.TITLE -> {
                            it.sortedByDescending { it.title }
                        }
                        NoteType.DATE -> {
                            it.sortedByDescending { it.date }
                        }
                        NoteType.COLOR -> {
                            it.sortedByDescending { it.color }
                        }
                    }
                }

            }
        }
    }
}