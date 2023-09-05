package com.example.myapplication.welcomehomer

import android.widget.NumberPicker
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
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

    }, floatingActionButton = {

    },
        content = { _ ->
            WelcomeHomerContainer()
        })
}

@Composable
private fun WelcomeHomerContainer(modifier: Modifier = Modifier) {
    val radioOptions = listOf("PayPal", "Direct")
    val (selectedOption, onOptionSelected) = remember { mutableStateOf(radioOptions[0] ) }
    var amount by remember { mutableStateOf("0")}
    Column(modifier = modifier) {
        Text(text = stringResource(id = R.string.app_title))
        Text(text = stringResource(R.string.pay_method))
        Column {
            radioOptions.forEach { text ->
                Row(modifier = Modifier.selectable(
                    selected = (text == selectedOption),
                    onClick = { onOptionSelected(text) }
                )) {
                    RadioButton(
                        selected = (text == selectedOption),
                        onClick = null
                    )
                    Text(text = text)
                }
            }
        }
        AndroidView(factory = { ctx ->
            NumberPicker(ctx).apply {
                minValue = 0
                maxValue = 1000
                wrapSelectorWheel = true
                setOnValueChangedListener {numberPicker, oldVal, newVal ->

                }
            }
        })
        LinearProgressIndicator(progress = 0.25f)
        Row {
            Text(text = stringResource(R.string.amount))
            TextField(
                value = amount,
                onValueChange = {text -> amount = text },
                label = {},
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
            )
        }
    }
}