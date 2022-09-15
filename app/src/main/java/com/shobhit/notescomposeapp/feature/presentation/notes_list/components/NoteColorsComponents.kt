package com.shobhit.notescomposeapp.feature.presentation.notes_list.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.unit.dp
import com.shobhit.notescomposeapp.feature.domain.model.Note

@Composable
fun NoteColorsComponents(modifier: Modifier,noteColor:Int,onUpdateColor:(Color) -> Unit){
    Row(modifier = modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceAround) {
        Note.colorsList.forEach { color ->
            val colorInt = color.toArgb()
            Box(modifier = Modifier.size(50.dp).clip(CircleShape).border(if(noteColor==colorInt) 4.dp else 0.dp,
                if(noteColor==colorInt) Color.Black else Color.Transparent, CircleShape).background(color).clickable {
                onUpdateColor(color)
            })
        }
    }
}