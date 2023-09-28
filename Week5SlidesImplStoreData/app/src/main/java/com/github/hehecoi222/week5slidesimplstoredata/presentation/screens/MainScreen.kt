package com.github.hehecoi222.week5slidesimplstoredata.presentation.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.github.hehecoi222.week5slidesimplstoredata.domain.entities.SimpleEntity
import com.github.hehecoi222.week5slidesimplstoredata.domain.viewmodel.MainViewModel
import kotlinx.coroutines.launch

@Composable
fun MainScreen(viewModel: MainViewModel = viewModel()) {
    val uiState by viewModel.uiState.collectAsState()
    val navController = rememberNavController()


    NavHost(navController = navController, startDestination = "main") {
        composable("main") {
            LazyColumn {
                item {
                    AddDataField(
                        modifier = Modifier.fillMaxWidth(),
                        field = "DataStore",
                        userInputKey = viewModel.userInputKey,
                        userInputValue = viewModel.userInputValue,
                        onUserInputKeyChanged = {
                            viewModel.userInputKey = it
                        },
                        onUserInputValueChanged = {
                            viewModel.userInputValue = it
                        },
                        onAddButtonClicked = {
                            viewModel.viewModelScope.launch {
                                viewModel.onSubmitDS()
                            }
                        },
                        onNavigateFullList = {
                            navController.navigate("list-ds")
                        })
                }
                item {
                    AddDataField(
                        modifier = Modifier.fillMaxWidth(),
                        field = "File Public",
                        userInputKey = viewModel.userInputKey,
                        userInputValue = viewModel.userInputValue,
                        onUserInputKeyChanged = {
                            viewModel.userInputKey = it
                        },
                        onUserInputValueChanged = {
                            viewModel.userInputValue = it
                        },
                        onAddButtonClicked = {
                            viewModel.viewModelScope.launch {
                                viewModel.onSubmitFile()
                            }
                        },
                        onNavigateFullList = {
                            navController.navigate("list-file")
                        }
                    )
//                    TextField(value = viewModel.userFile, onValueChange = {
//                        viewModel.userFile = it
//                    }, modifier = Modifier.padding(bottom = 8.dp), label = { Text(text = "Value") })
//                    Button(onClick = , modifier = Modifier.padding(bottom = 8.dp)) {
//                        Text(text = "Set File")
//                    }
                }
                item {
                    AddDataField(
                        modifier = Modifier.fillMaxWidth(),
                        field = "Room",
                        userInputKey = viewModel.userInputKey,
                        userInputValue = viewModel.userInputValue,
                        onUserInputKeyChanged = {
                            viewModel.userInputKey = it
                        },
                        onUserInputValueChanged = {
                            viewModel.userInputValue = it
                        },
                        onAddButtonClicked = {
                            viewModel.onSubmitRoom(SimpleEntity(viewModel.userInputKey, viewModel.userInputValue))
                        },
                        onNavigateFullList = {
                            navController.navigate("list-room")
                        }
                    )
                }

            }
        }
        composable("list-ds") {
            ListScreen(
                modifier = Modifier.fillMaxSize(),
                values = uiState.allKeyDS ?: emptyMap<String, String>(),
                onLoadButton = {
                    viewModel.viewModelScope.launch {
                        viewModel.onLoadTabDS()
                    }
                },
                onRemoveButton = {
                    viewModel.viewModelScope.launch {
                        viewModel.onRemoveDS(it.toString())
                    }
                })
        }
        composable("list-file") {
            ListScreen(
                modifier = Modifier.fillMaxSize(),
                values = uiState.allKeyFile ?: emptyMap<String, String>(),
                onLoadButton = {
                    viewModel.viewModelScope.launch {
                        viewModel.onLoadTabFile()
                    }
                },
                onRemoveButton = {
                    viewModel.viewModelScope.launch {
                        viewModel.onRemoveFile(it.toString())
                    }
                })
        }
        composable("list-room") {
            ListScreen(
                modifier = Modifier.fillMaxSize(),
                values = emptyMap<String, String>(),
                roomValue = uiState.allKeyRoom ?: emptyList(),
                onLoadButton = {
                    viewModel.onLoadTabRoom()
                },
                onRemoveButton = {
                    viewModel.onRemoveRoom(it as SimpleEntity)
                })
        }
    }
}

@Composable
private fun ListScreen(
    modifier: Modifier = Modifier,
    values: Map<*, *>,
    roomValue: List<SimpleEntity> = emptyList(),
    onLoadButton: () -> Unit = {},
    onRemoveButton: (Any) -> Unit = {}
) {
    LazyColumn(modifier = modifier) {
        item {
            Button(onClick = onLoadButton, modifier = Modifier.padding(bottom = 8.dp)) {
                Text(text = "Load")
            }
        }
        items(values.keys.toList()) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(bottom = 8.dp)
            ) {
                Text(text = "$it:", modifier = Modifier.padding(bottom = 8.dp))
                Text(text = "${values[it]}", modifier = Modifier.padding(bottom = 8.dp))
                Button(onClick = {
                    if (it != null) {
                        onRemoveButton(it)
                    }
                }, modifier = Modifier.padding(bottom = 8.dp)) {
                    Text(text = "Remove")
                }
            }
        }
        items(roomValue) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(bottom = 8.dp)
            ) {
                Text(text = "${it.key}:", modifier = Modifier.padding(bottom = 8.dp))
                Text(text = it.value, modifier = Modifier.padding(bottom = 8.dp))
                Button(onClick = {
                    if (it != null) {
                        onRemoveButton(it)
                    }
                }, modifier = Modifier.padding(bottom = 8.dp)) {
                    Text(text = "Remove")
                }
            }
        }
    }
}

@Composable
private fun AddDataField(
    modifier: Modifier = Modifier,
    field: String = "",
    userInputKey: String = "",
    userInputValue: String = "",
    onUserInputKeyChanged: (String) -> Unit = {},
    onUserInputValueChanged: (String) -> Unit = {},
    onAddButtonClicked: () -> Unit = {},
    onNavigateFullList: () -> Unit = {}
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        TextField(value = userInputKey, onValueChange = {
            onUserInputKeyChanged(it)
        }, modifier = Modifier.padding(bottom = 8.dp), label = { Text(text = "Key") })
        TextField(value = userInputValue, onValueChange = {
            onUserInputValueChanged(it)
        }, modifier = Modifier.padding(bottom = 8.dp), label = { Text(text = "Value") })
        Button(onClick = onAddButtonClicked, modifier = Modifier.padding(bottom = 8.dp)) {
            Text(text = "Add $field")
        }
        Button(onClick = onNavigateFullList, modifier = Modifier.padding(bottom = 8.dp)) {
            Text(text = "List $field")
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun AddDataFieldPreview() {
    AddDataField(Modifier.fillMaxSize())
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun MainScreenPreview() {
    MainScreen()
}