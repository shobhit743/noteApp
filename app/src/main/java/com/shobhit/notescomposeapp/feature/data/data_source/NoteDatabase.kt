package com.shobhit.notescomposeapp.feature.data.data_source

import androidx.room.Database
import androidx.room.RoomDatabase
import com.shobhit.notescomposeapp.feature.domain.model.Note

@Database(entities = [Note::class], version = 1)
abstract class NoteDatabase:RoomDatabase() {
    abstract val noteDao: NotesDao
}