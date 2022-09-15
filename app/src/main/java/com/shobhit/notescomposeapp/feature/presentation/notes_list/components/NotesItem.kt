package com.shobhit.notescomposeapp.feature.presentation.notes_list.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.shobhit.notescomposeapp.feature.domain.model.Note

@Composable
fun NotesItem(note: Note, modifier: Modifier = Modifier, onItemSelected:(Note) -> Unit,onItemDeleteClick:(Note)->Unit){
    Box(modifier = modifier.padding( 10.dp).border(2.dp,Color.Black, RoundedCornerShape(10.dp))
        .clip(RoundedCornerShape(10.dp)).background(color = Color(note.color))){
        Column(modifier = Modifier
            .fillMaxWidth()
            .clickable {
                onItemSelected(note)
            }.padding(10.dp)) {
            Text(text = note.title, style = MaterialTheme.typography.h6)
            Text(text = note.content, style = MaterialTheme.typography.body1)
        }
        IconButton(onClick = {
            onItemDeleteClick(note)
        },modifier = Modifier.align(Alignment.BottomEnd)) {
            Icon(imageVector = Icons.Default.Delete, contentDescription = "")
        }
    }
}