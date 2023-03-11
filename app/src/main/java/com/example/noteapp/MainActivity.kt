package com.example.noteapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.noteapp.features.note.presentation.add_edit_note.AddEditNotePage
import com.example.noteapp.features.note.presentation.notes.NotePage
import com.example.noteapp.features.note.presentation.utils.NoteAppRoute
import com.example.noteapp.ui.theme.NoteAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NoteAppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize()
                ) {
                    val navController = rememberNavController()
                    NavHost(
                        navController = navController,
                        startDestination = NoteAppRoute.NotesPage.routeName
                    ) {
                        composable(route = NoteAppRoute.NotesPage.routeName) {
                            NotePage(navController = navController)
                        }
                        composable(route = NoteAppRoute.AddEditPage.routeName + "?noteId={noteId}&noteColor={noteColor}",
                            arguments = listOf(navArgument(name = "noteId") {
                                type = NavType.IntType
                                defaultValue = -1
                            }, navArgument(name = "noteColor") {
                                type = NavType.IntType
                                defaultValue = -1
                            })
                        ) {
                            val color = it.arguments?.getInt("noteColor") ?: -1
                            AddEditNotePage(navController = navController, noteColor = color)
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    NoteAppTheme {
        Greeting(name = "Nguyen")
    }
}