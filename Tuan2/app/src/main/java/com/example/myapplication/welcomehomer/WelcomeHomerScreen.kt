package com.example.myapplication.welcomehomer

import android.widget.NumberPicker
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.MailOutline
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.viewinterop.AndroidView
import com.example.myapplication.R

@Preview(showBackground = true)
@Composable
private fun WelcomeHomerScreenPreview() {
    WelcomeHomerScreen()
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WelcomeHomerScreen() {
    Scaffold(topBar = {
        TopAppBar(
            title = {
                Text(text = stringResource(R.string.app_name), maxLines = 1, overflow = TextOverflow.Ellipsis)
            },
            actions = {
                Icon(
                    imageVector = Icons.Default.MoreVert,
                    contentDescription = "More"
                )
            }
        )
    }, floatingActionButton = {
        FloatingActionButton(
            onClick = {},
            content = {
                Icon(
                    imageVector = Icons.Default.MailOutline,
                    contentDescription = "Mail"
                )
            }
        )
    }, content = { padding ->
        WelcomeHomerContainer(Modifier, padding)
    })
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun WelcomeHomerContainer(modifier: Modifier = Modifier, contentPadding: PaddingValues) {
    val radioOptions = listOf("PayPal", "Direct")
    val (selectedOption, onOptionSelected) = remember { mutableStateOf(radioOptions[0]) }
    var amount by remember { mutableStateOf("0") }
    LazyColumn(modifier = modifier, contentPadding = contentPadding) {
        item { Text(text = stringResource(id = R.string.app_title)) }
        item { Text(text = stringResource(R.string.pay_method)) }
        item {
            Column {
                radioOptions.forEach { text ->
                    Row(modifier = Modifier.selectable(selected = (text == selectedOption),
                        onClick = { onOptionSelected(text) })) {
                        RadioButton(
                            selected = (text == selectedOption), onClick = null
                        )
                        Text(text = text)
                    }
                }
            }
        }
        item {
            AndroidView(factory = { ctx ->
                NumberPicker(ctx).apply {
                    minValue = 0
                    maxValue = 1000
                    wrapSelectorWheel = true
                    setOnValueChangedListener { numberPicker, oldVal, newVal ->

                    }
                }
            })
        }
        item { LinearProgressIndicator(progress = 0.25f) }
        item {
            Row {
                Text(text = stringResource(R.string.amount))
                TextField(value = amount,
                    onValueChange = { text -> amount = text },
                    label = {},
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                )
            }
        }
        item {
            Row {
                Button(onClick = {}) {
                    Text(text = stringResource(R.string.donate_btn))
                }
                Text(text = stringResource(R.string.total, 1998))
            }
        }
    }
}