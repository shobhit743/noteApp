package com.shobhit.notescomposeapp.feature.data.data_source

import androidx.room.*
import com.shobhit.notescomposeapp.feature.domain.model.Note
import kotlinx.coroutines.flow.Flow

@Dao
interface NotesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNote(note: Note)

    @Query("Select * from ${Note.TABLE_NAME}")
    fun getNotes():Flow<List<Note>>

    @Query("Select * from ${Note.TABLE_NAME} where id=:id")
    suspend fun getNote(id:Int): Note

    @Update
    suspend fun updateNote(note: Note)

    @Delete
    suspend fun deleteNote(note: Note)


}