package com.shobhit.notescomposeapp.feature.presentation.notes_list.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.RadioButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.shobhit.notescomposeapp.feature.domain.util.NoteOrder
import com.shobhit.notescomposeapp.feature.domain.util.NoteType
import com.shobhit.notescomposeapp.feature.domain.util.OrderType

@Composable
fun OrderTypeComponent(modifier: Modifier = Modifier, noteOrder: NoteOrder, onButtonClicked:(NoteOrder) -> Unit){
    Column(modifier = modifier) {
        Row(modifier = Modifier.fillMaxWidth()) {
            Row( verticalAlignment =Alignment.CenterVertically, modifier = Modifier.clickable {
                onButtonClicked(noteOrder.copy(noteType = NoteType.TITLE))
            }) {
                RadioButton(selected = noteOrder.noteType== NoteType.TITLE, onClick = {
                    onButtonClicked(noteOrder.copy(noteType = NoteType.TITLE))
                })
                Text(text = "Title")
            }
            Row( verticalAlignment =Alignment.CenterVertically, modifier = Modifier.clickable {
                onButtonClicked(noteOrder.copy(noteType = NoteType.DATE))
            }) {
                RadioButton(selected = noteOrder.noteType== NoteType.DATE, onClick = {
                    onButtonClicked(noteOrder.copy(noteType = NoteType.DATE))
                })
                Text(text = "Date")
            }
            Row( verticalAlignment =Alignment.CenterVertically, modifier = Modifier.clickable {
                onButtonClicked(noteOrder.copy(noteType = NoteType.COLOR))
            }) {
                RadioButton(selected = noteOrder.noteType== NoteType.COLOR, onClick = {
                    onButtonClicked(noteOrder.copy(noteType = NoteType.COLOR))
                })
                Text(text = "Color")
            }

        }
        Row(modifier = Modifier.fillMaxWidth()) {
            Row( verticalAlignment =Alignment.CenterVertically, modifier = Modifier.clickable {
                onButtonClicked(noteOrder.copy(orderType = OrderType.ASCENDING))
            }) {
                RadioButton(selected = noteOrder.orderType== OrderType.ASCENDING, onClick = {
                    onButtonClicked(noteOrder.copy(orderType = OrderType.ASCENDING))
                })
                Text(text = "Ascending")
            }
            Row( verticalAlignment =Alignment.CenterVertically, modifier = Modifier.clickable {
                onButtonClicked(noteOrder.copy(orderType = OrderType.DESCENDING))
            }) {
                RadioButton(selected = noteOrder.orderType== OrderType.DESCENDING, onClick = {
                    onButtonClicked(noteOrder.copy(orderType = OrderType.DESCENDING))
                })
                Text(text = "Descending")
            }
        }

    }

}