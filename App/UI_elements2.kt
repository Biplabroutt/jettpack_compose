package com.example.ui_elements

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

import com.example.ui_elements.ui.theme.UI_elementsTheme
import com.google.firebase.annotations.concurrent.Background

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            UI_elementsTheme {
                MyApp()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyApp()
{
    Column(
        modifier = Modifier
            .background(Color.Magenta)
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    )
    {
        var showBottomSheet by remember { mutableStateOf(false) }
        var showDialog by remember { mutableStateOf(false) }
        var text by remember { mutableStateOf("") }

        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text("Scaffold example") }
                )
            },
            content = { padding ->
                Column(modifier = Modifier.padding(padding)) {
                    TextField(
                        value = text,
                        onValueChange = { text = it },
                        label = { Text("Enter text") },
                        modifier = Modifier
                            .background(Color.Yellow)
                            .padding(16.dp)
                            .fillMaxWidth()
                    )

                    Button(
                        onClick = { showBottomSheet = true },
                        modifier = Modifier.padding(16.dp)
                    ) {
                        Text("Show Bottom Sheet")
                    }

                    Button(
                        onClick = { showDialog = true },
                        modifier = Modifier.padding(16.dp)
                    ) {
                        Text("Show Dialog")
                    }

                    if (showBottomSheet) {
                        ModalBottomSheet(
                            onDismissRequest = { showBottomSheet = false }
                        ) {
                            Column(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(16.dp)
                            ) {
                                Text(
                                    "Bottom Sheet Content",
                                    style = MaterialTheme.typography.bodyLarge
                                )
                                Button(onClick = { showBottomSheet = false }) {
                                    Text("Close")
                                }
                            }
                        }
                    }

                    if (showDialog) {
                        AlertDialog(
                            onDismissRequest = { showDialog = false },
                            title = { Text("Dialog Title") },
                            text = { Text("This is a dialog message.") },
                            confirmButton = {
                                TextButton(onClick = { showDialog = false }) {
                                    Text("Confirm")
                                }
                            },
                            dismissButton = {
                                TextButton(onClick = { showDialog = false }) {
                                    Text("Dismiss")
                                }
                            }
                        )
                    }
                }
            }

        )
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    UI_elementsTheme {
        MyApp()
    }
}

