package com.shobhit.notescomposeapp.feature.presentation.notes_list.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.shobhit.notescomposeapp.feature.domain.util.NoteOrder

@Composable
fun OrderSection(modifier: Modifier = Modifier, noteOrder: NoteOrder, onButtonClicked:(NoteOrder) -> Unit){
   OrderTypeComponent(modifier = modifier, noteOrder = noteOrder, onButtonClicked= {
       onButtonClicked(it)
   })
}