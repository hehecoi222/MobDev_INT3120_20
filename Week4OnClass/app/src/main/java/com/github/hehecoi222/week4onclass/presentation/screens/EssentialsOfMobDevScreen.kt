package com.github.hehecoi222.week4onclass.presentation.screens

import androidx.annotation.StringRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.surfaceColorAtElevation
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.github.hehecoi222.week4onclass.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EssentialsOfMobDevScreen() {
    Scaffold(
        topBar = {
            Surface(shadowElevation = 3.dp) {
                TopAppBar(
                    title = {
                        Text(text = stringResource(id = R.string.app_name), color = MaterialTheme.colorScheme.onSurface)
                    },
                    colors = TopAppBarDefaults.smallTopAppBarColors(containerColor = MaterialTheme.colorScheme.surfaceColorAtElevation(3.dp))
                )
            }
        },

        ) { paddingValues ->
        EssentialsContainer(
            modifier = Modifier
                .padding(paddingValues)
                .padding(20.dp)
        )
    }
}

@Composable
private fun EssentialsContainer(modifier: Modifier = Modifier) {
    var name by remember { mutableStateOf("") }
    var phoneNum by remember { mutableStateOf("") }
    val radioOptionsFirst = listOf("Cheese", "2 x Cheese", "None")
    val (radioOptionsFirstSelected, onFirstOptionChanged) = remember {
        mutableStateOf(radioOptionsFirst[0])
    }
    val radioOptionsSecond = listOf("Square", "Round")
    val (radioOptionsSecondSelected, onSecondOptionChanged) = remember {
        mutableStateOf(radioOptionsSecond[0])
    }
    val options = listOf("Pepperoni", "Mushroom", "Veggies")
    var selectedOptions by remember {
        mutableStateOf(List(0) { _ -> "" })
    }

    val focusManager = LocalFocusManager.current

    Column(modifier = modifier) {
        InformationTextField(
            value = name,
            onValueChange = { name = it },
            label = R.string.name_tf_label,
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Next
            ),
            keyboardActions = KeyboardActions(onNext = {
                focusManager.moveFocus(FocusDirection.Down)
            }),
            modifier = Modifier.padding(bottom = 16.dp)
        )
        InformationTextField(
            value = phoneNum,
            onValueChange = { phoneNum = it },
            label = R.string.phone_tf_label,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Phone, imeAction = ImeAction.Done
            ),
            keyboardActions = KeyboardActions(onDone = {
                focusManager.clearFocus()
            }),
            modifier = Modifier.padding(bottom = 16.dp)
        )
        RadioGroup(
            radioOptions = radioOptionsFirst,
            selectedOption = radioOptionsFirstSelected,
            onOptionChanged = onFirstOptionChanged,
            modifier = Modifier
                .wrapContentSize()
                .padding(bottom = 16.dp)
        )
        RadioGroup(
            radioOptions = radioOptionsSecond,
            selectedOption = radioOptionsSecondSelected,
            onOptionChanged = onSecondOptionChanged,
            modifier = Modifier
                .wrapContentSize()
                .padding(bottom = 16.dp)
        )
        CheckboxList(
            checkboxOptions = options, selectedOptions = selectedOptions, onOptionClick = { text ->
                selectedOptions = if (selectedOptions.contains(text)) {
                    selectedOptions.filter { text != it }
                } else {
                    selectedOptions.plus(text)
                }
            }, modifier = Modifier.padding(bottom = 16.dp)
        )
        Text(text = stringResource(id = R.string.summary))
        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
                .padding(end = 16.dp)
                .background(color = MaterialTheme.colorScheme.surfaceTint)
        ) { }
        ExitAndOrderButtonRow(modifier = Modifier.fillMaxWidth(), exitOnClick = { }) { }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun InformationTextField(
    value: String,
    onValueChange: (String) -> Unit,
    @StringRes label: Int,
    modifier: Modifier = Modifier,
    keyboardActions: KeyboardActions = KeyboardActions {},
    keyboardOptions: KeyboardOptions = KeyboardOptions(),
) {
    TextField(
        value = value, onValueChange = onValueChange, label = {
            Text(text = stringResource(id = label))
        }, keyboardOptions = keyboardOptions, keyboardActions = keyboardActions, modifier = modifier
    )
}

@Composable
private fun RadioGroup(
    modifier: Modifier = Modifier,
    radioOptions: List<String>,
    selectedOption: String,
    onOptionChanged: (String) -> Unit = {}
) {
    Row(
        modifier = modifier
    ) {
        radioOptions.forEach {
            Row(
                modifier = Modifier
                    .selectable(selected = it == selectedOption, onClick = {
                        onOptionChanged(it)
                    })
                    .padding(end = 20.dp), verticalAlignment = Alignment.CenterVertically
            ) {
                RadioButton(
                    selected = it == selectedOption,
                    onClick = null,
                    modifier = Modifier.padding(end = 5.dp)
                )
                Text(text = it)
            }
        }
    }
}

@Composable
private fun CheckboxList(
    modifier: Modifier = Modifier,
    checkboxOptions: List<String>,
    selectedOptions: List<String>,
    onOptionClick: (String) -> Unit = {}
) {
    Row(modifier = modifier) {
        checkboxOptions.forEach {
            val checked = selectedOptions.contains(it)
            Row(modifier = Modifier
                .selectable(selected = checked, onClick = {
                    onOptionClick(it)
                })
                .padding(end = 20.dp), verticalAlignment = Alignment.CenterVertically) {
                Checkbox(
                    checked = checked,
                    onCheckedChange = null,
                    modifier = Modifier.padding(end = 5.dp)
                )
                Text(text = it)
            }
        }
    }
}

@Composable
private fun ExitAndOrderButtonRow(
    modifier: Modifier = Modifier, exitOnClick: () -> Unit, orderOnClick: () -> Unit
) {
    Row(modifier = modifier, horizontalArrangement = Arrangement.SpaceBetween) {
        Button(onClick = exitOnClick) {
            Text(text = stringResource(id = R.string.exit_btn))
        }
        Button(onClick = orderOnClick) {
            Text(text = stringResource(id = R.string.order_btn))
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun CheckboxListPreview() {
    val options = listOf("Pepperoni", "Mushroom", "Veggies")
    var selectedOptions by remember {
        mutableStateOf(List(0) { _ -> "" })
    }

    CheckboxList(
        checkboxOptions = options,
        selectedOptions = selectedOptions,
        onOptionClick = { text ->
            selectedOptions = if (selectedOptions.contains(text)) {
                selectedOptions.filter { text != it }
            } else {
                selectedOptions.plus(text)
            }
        })
}

@Preview(showBackground = true)
@Composable
private fun RadioGroupPreview() {
    val options = listOf("ABC", "DEF", "GHI")
    val (selectedOption, onOptionSelected) = remember {
        mutableStateOf(
            options[0]
        )
    }

    RadioGroup(
        radioOptions = options,
        selectedOption = selectedOption,
        onOptionChanged = onOptionSelected,
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
    )
}

@Preview(showBackground = true)
@Composable
fun EssentialsOfMobDevScreenPreview() {
    EssentialsOfMobDevScreen()
}
