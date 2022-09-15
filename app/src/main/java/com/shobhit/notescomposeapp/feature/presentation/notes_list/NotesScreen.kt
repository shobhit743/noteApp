package com.shobhit.notescomposeapp.feature.presentation.notes_list

import androidx.compose.animation.*
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Info
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.shobhit.notescomposeapp.R
import com.shobhit.notescomposeapp.feature.presentation.notes_list.components.NotesItem
import com.shobhit.notescomposeapp.feature.presentation.notes_list.components.OrderSection
import com.shobhit.notescomposeapp.feature.presentation.utils.Screens
import kotlinx.coroutines.launch

@Composable
fun NotesScreen(navController: NavController, viewModel:NotesViewModel = hiltViewModel()){

    val notes =viewModel.notes.value
    val orderSection = viewModel.showOrderSection.collectAsState().value
    val noteOrder = viewModel.noteOrder.collectAsState()
    val scaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()

    Scaffold(modifier = Modifier.fillMaxSize(),scaffoldState = scaffoldState, topBar = {
        TopAppBar(modifier = Modifier.fillMaxWidth(), actions =  {
            IconButton(onClick = {
                viewModel.toggleOrderSection()
            }) {
              Icon(painter = painterResource(id = R.drawable.ic_sort), contentDescription = "", tint = MaterialTheme.colors.onPrimary)
            }
        }, title = {
            Text(text = "Notes", modifier = Modifier.padding(16.dp), style = MaterialTheme.typography.h6, color = Color.White)
        })
    }, floatingActionButton = {
        FloatingActionButton(onClick = {
            navController.navigate(Screens.AddEditScreen.route)
        }) {
            Icon(imageVector = Icons.Default.Add, contentDescription = "")
        }
    }) {
        Surface(modifier = Modifier
            .padding(it)
            .fillMaxSize(),
            color = MaterialTheme.colors.background) {
            Column(modifier = Modifier.fillMaxSize()) {
                    AnimatedVisibility(visible = orderSection) {
                        OrderSection(modifier = Modifier.fillMaxWidth(), noteOrder = noteOrder.value) {
                            viewModel.updateNoteOrder(it)
                        }
                    }

                LazyColumn(modifier = Modifier.fillMaxSize()){
                    items(notes){
                        NotesItem(note = it, modifier = Modifier
                            .fillMaxWidth(), onItemSelected = {
                            navController.navigate(Screens.AddEditScreen.route+"?noteId=${it.id}&noteColor=${it.color}")
                        }) {
                            viewModel.deleteNote(it)
                            scope.launch {
                                val snackbarResult  = scaffoldState.snackbarHostState.showSnackbar("Note deleted ${it.title}", actionLabel = "Undo")
                                if(snackbarResult==SnackbarResult.ActionPerformed){
                                    viewModel.insertNote()
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}