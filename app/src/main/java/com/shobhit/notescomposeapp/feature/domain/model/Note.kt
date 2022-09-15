package com.shobhit.notescomposeapp.feature.domain.model

import androidx.compose.ui.graphics.Color
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = Note.TABLE_NAME)
data class Note(val title:String, val content:String, val color:Int,val date:Long = System.currentTimeMillis(), @PrimaryKey(autoGenerate = true) val id:Int = 0){

    companion object {
        const val TABLE_NAME = "notes_table"
        val colorsList = mutableListOf<Color>(Color.White,Color.LightGray,Color.Cyan,Color.Yellow)
    }

}
