package com.shobhit.notescomposeapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.ui.graphics.toArgb
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.shobhit.notescomposeapp.feature.presentation.add_edit_note.AddEditNotesScreen
import com.shobhit.notescomposeapp.feature.domain.model.Note
import com.shobhit.notescomposeapp.feature.presentation.notes_list.NotesScreen
import com.shobhit.notescomposeapp.ui.theme.NotesComposeAppTheme
import com.shobhit.notescomposeapp.feature.presentation.utils.Screens
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NotesComposeAppTheme {
                // A surface container using the 'background' color from the theme
                val navController = rememberNavController()
                NavHost(navController = navController, startDestination = Screens.NotesScreen.route){
                    composable(Screens.NotesScreen.route){
                        NotesScreen(navController = navController)
                    }
                    composable(Screens.AddEditScreen.route+"?noteId={noteId}&noteColor={noteColor}", arguments = listOf(
                        navArgument("noteId"){
                        type = NavType.IntType
                        defaultValue = -1
                    },navArgument("noteColor"){
                            type = NavType.IntType
                            defaultValue = Note.colorsList[0].toArgb()
                        })){
                        AddEditNotesScreen(navController = navController)
                    }
                }
            }
        }
    }
}

