package com.shobhit.notescomposeapp.feature.presentation.utils

sealed class Screens(val route:String){
    object NotesScreen:Screens("notes")
    object AddEditScreen:Screens("add_edit_screen")
}
