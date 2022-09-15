package com.shobhit.notescomposeapp.di

import android.content.Context
import androidx.room.Room
import com.shobhit.notescomposeapp.feature.data.data_source.NoteDatabase
import com.shobhit.notescomposeapp.feature.domain.repository.Repository
import com.shobhit.notescomposeapp.feature.data.repository.RepositoryImpl
import com.shobhit.notescomposeapp.feature.domain.use_cases.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideRoomDb(@ApplicationContext context:Context) =  Room.databaseBuilder(context,
        NoteDatabase::class.java,"note_db").build()

    @Provides
    @Singleton
    fun provideRepository(noteDatabase: NoteDatabase): Repository {
        return RepositoryImpl(noteDatabase.noteDao)
    }

    @Provides
    @Singleton
    fun provideNoteUseCase(repository: Repository) = NoteUseCase(
        deleteNoteUseCase = DeleteNoteUseCase(repository),
        getNotesUseCase = GetNotesUseCase(repository),
        getNoteUseCase = GetNoteUseCase(repository),
        insertNoteUseCase = InsertNoteUseCase(repository),
        updateNoteUseCase = UpdateNoteUseCase(repository)
    )


}