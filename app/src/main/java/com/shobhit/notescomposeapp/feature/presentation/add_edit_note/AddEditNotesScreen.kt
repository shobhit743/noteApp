package com.shobhit.notescomposeapp.feature.presentation.add_edit_note

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.shobhit.notescomposeapp.feature.presentation.notes_list.components.NoteColorsComponents
import com.shobhit.notescomposeapp.feature.presentation.utils.Event

@Composable
fun AddEditNotesScreen(
        navController: NavController,
        viewModel: AddEditNotesViewModel = hiltViewModel()
) {
    val title = viewModel.name.value
    val desc = viewModel.desc.value
    val noteColor = viewModel.color.value
    val animatedColor = animateColorAsState(targetValue = noteColor, animationSpec = tween(400)).value.toArgb()
    val noteId = viewModel.currentNoteId
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(navigationIcon = {
                IconButton(onClick = {
                    navController.navigateUp()
                }) {
                    Icon(imageVector = Icons.Default.ArrowBack,
                        contentDescription = "")
                }
            }, modifier = Modifier.fillMaxWidth(), title = {
                Text(text =if(noteId==-1) "Add Note" else "Update Note")
            }, actions = {
                IconButton(onClick = { viewModel.updateEvent(Event.SaveNote(title,desc,noteColor.toArgb()))
                navController.navigateUp()}) {
                    Icon(imageVector = Icons.Default.Check, contentDescription = "", tint = MaterialTheme.colors.onPrimary)
                }
            })
        },
    ) {
        Surface(modifier = Modifier
            .padding(it)
            .fillMaxSize()
            ) {
            Column(modifier = Modifier
                .fillMaxSize()
                .background(color = Color(animatedColor))
                .padding(16.dp)) {
                NoteColorsComponents(modifier = Modifier.fillMaxWidth(), noteColor = noteColor.toArgb()){
                    viewModel.updateEvent(Event.ColorEvent(it.toArgb()))
                }
                Spacer(modifier = Modifier.height(10.dp))
                OutlinedTextField(
                    modifier = Modifier.fillMaxWidth(), value = title,
                    onValueChange = {
                        viewModel.updateEvent(Event.TitleEvent(it))
                    },
                    placeholder = {
                        Text(text = "Title")
                    },
                    singleLine = true,
                )
                Spacer(modifier = Modifier.height(10.dp))
                OutlinedTextField(modifier = Modifier.fillMaxWidth(),
                    value = desc,
                    onValueChange = {
                        viewModel.updateEvent(Event.DescEvent(it))
                    },
                    placeholder = {
                        Text(text = "Description")
                    })
            }
        }
    }
}