package com.shobhit.notescomposeapp.feature.data.repository

import com.shobhit.notescomposeapp.feature.domain.repository.Repository
import com.shobhit.notescomposeapp.feature.data.data_source.NotesDao
import com.shobhit.notescomposeapp.feature.domain.model.Note
import kotlinx.coroutines.flow.Flow

class RepositoryImpl(private val notesDao: NotesDao) : Repository {
    override suspend fun insertNote(note: Note) {
        notesDao.insertNote(note)
    }

    override fun getNotes(): Flow<List<Note>> {
        return notesDao.getNotes()
    }

    override suspend fun getNoteById(id: Int): Note {
        return notesDao.getNote(id)
    }

    override suspend fun updateNote(note: Note) {
        return notesDao.updateNote(note)
    }

    override suspend fun deleteNote(note: Note) {
        return notesDao.deleteNote(note)
    }
}