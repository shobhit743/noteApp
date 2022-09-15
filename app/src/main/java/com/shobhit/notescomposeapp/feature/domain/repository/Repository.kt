package com.shobhit.notescomposeapp.feature.domain.repository

import com.shobhit.notescomposeapp.feature.domain.model.Note
import kotlinx.coroutines.flow.Flow

interface Repository {

    suspend fun insertNote(note: Note)

    fun getNotes():Flow<List<Note>>

    suspend fun getNoteById(id:Int): Note?

    suspend fun updateNote(note: Note)

    suspend fun deleteNote(note: Note)

}