package com.example.week6slidesimpl.presentation.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.week6slidesimpl.presentation.viewmodels.MainViewModel

@Composable
fun MainScreen(viewModel: MainViewModel = viewModel()) {
    val uiState by viewModel.uiState.collectAsState()
    val isDialogShow = remember {
        mutableStateOf(false)
    }
    val whichItem = remember {
        mutableStateOf("")
    }

    Column(Modifier.fillMaxSize(), Arrangement.Center, Alignment.CenterHorizontally) {
        TextField(
            value = viewModel.userInputFirstName,
            onValueChange = { viewModel.userInputFirstName = it })
        TextField(
            value = viewModel.userInputLastName,
            onValueChange = { viewModel.userInputLastName = it })
        Button(onClick = { viewModel.onSave() }) {
            Text(text = "Submit")
        }
        Spacer(modifier = Modifier.padding(10.dp))
        Button(onClick = { viewModel.onLoadTab() }) {
            Text(text = "Load")
        }
        LazyColumn(modifier = Modifier.fillMaxSize()) {
            items(uiState.allUser) {
                Text(text = it.toString())
                Button(onClick = { viewModel.onDelete(it[0]) }) {
                    Text(text = "Remove")
                }
                Button(onClick = {
                    whichItem.value = it[0]
                    viewModel.selectedUpdate = ""
                    viewModel.userInputUpdateValue = ""
                    isDialogShow.value = true
                }) {
                    Text(text = "Update")
                }
            }
        }
    }
    if (isDialogShow.value) {
        DialogCustom(
            onDismissRequest = {
                isDialogShow.value = false
            },
            columnValue = viewModel.selectedUpdate,
            columnItems = viewModel.columns,
            onColumnSelected = { viewModel.selectedUpdate = it },
            value = viewModel.userInputUpdateValue,
            onValueChange = { viewModel.userInputUpdateValue = it },
            onSubmit = {
                viewModel.onUpdate(whichItem.value)
                isDialogShow.value = false
            }
        )
    }
}

@Composable
fun DialogCustom(
    modifier: Modifier = Modifier,
    onDismissRequest: () -> Unit = {},
    columnValue: String = "",
    columnItems: List<String> = emptyList(),
    onColumnSelected: (String) -> Unit = {},
    value: String = "",
    onValueChange: (String) -> Unit = {},
    onSubmit: () -> Unit = {},
) {
    val isDropdownExpand = remember {
        mutableStateOf(false)
    }
    AlertDialog(
        onDismissRequest = onDismissRequest,
        title = { Text(text = "Update") },
        confirmButton = {
            TextButton(onClick = onSubmit) {
                Text(text = "Submit")
            }
        },
        dismissButton = {
            TextButton(onClick = onDismissRequest) {
                Text(text = "Cancel")
            }
        }, modifier = modifier,
        text = {
            Column {
                Box(
                    modifier = Modifier
                        .wrapContentSize(Alignment.TopStart)
                ) {
                    OutlinedTextField(
                        value = columnValue,
                        readOnly = true,
                        label = {
                                Text(text = "Column")
                        },
                        onValueChange = { },
                        trailingIcon = {
                            Icon(
                                imageVector = Icons.Default.ArrowDropDown,
                                contentDescription = "Dropdown",
                                modifier = Modifier.clickable {
                                    isDropdownExpand.value = true
                                })
                        })
                    DropdownMenu(
                        expanded = isDropdownExpand.value,
                        modifier = Modifier.width(IntrinsicSize.Max),
                        onDismissRequest = { isDropdownExpand.value = false }) {
                        columnItems.forEach {
                            DropdownMenuItem(text = {
                                Text(text = it)
                            }, onClick = {
                                onColumnSelected(it)
                                isDropdownExpand.value = false
                            })
                        }
                    }
                }
                Spacer(modifier = Modifier.padding(10.dp))
                OutlinedTextField(value = value, onValueChange = onValueChange, label = {
                    Text(text = "Value")
                })
            }
        }
    )
}


@Preview(showBackground = true)
@Composable
fun DialogCustomPreview() {
    DialogCustom()
}
