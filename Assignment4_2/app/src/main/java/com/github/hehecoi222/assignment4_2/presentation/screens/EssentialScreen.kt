package com.github.hehecoi222.assignment4_2.presentation.screens

import android.view.ContextThemeWrapper
import android.view.LayoutInflater
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import com.github.hehecoi222.assignment4_2.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EssentialScreen() {
    Scaffold(
        topBar = {
            Surface(shadowElevation = 3.dp) {
                TopAppBar(
                    title = {
                        Text(text = stringResource(id = R.string.app_name), color = MaterialTheme.colorScheme.onPrimary)
                    },
                    colors = TopAppBarDefaults.smallTopAppBarColors(MaterialTheme.colorScheme.primary)
                )
            }
        },
    ) {
        EssentialContainer(
            modifier = Modifier
                .padding(dimensionResource(id = R.dimen.margin_padding_around))
                .padding(it)
        )
    }
}

@Composable
private fun EssentialContainer(modifier: Modifier = Modifier) {
    val style =
        if (isSystemInDarkTheme()) R.style.Theme_Assignment4_2_Dark else R.style.Theme_Assignment4_2

    Column(modifier = modifier) {
        AndroidView(
            factory = { ctx ->
                LayoutInflater.from(ContextThemeWrapper(ctx, style))
                    .inflate(R.layout.two_edittext, null, false)
            },
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(bottom = dimensionResource(id = R.dimen.margin_padding_around))
        )
        AndroidView(
            factory = { ctx ->
                LayoutInflater.from(ContextThemeWrapper(ctx, style))
                    .inflate(R.layout.two_radio, null, false)
            }, modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(
                    bottom = dimensionResource(
                        id = R.dimen.margin_padding_around
                    )
                )
        )
        AndroidView(
            factory = { ctx ->
                LayoutInflater.from(ContextThemeWrapper(ctx, style))
                    .inflate(R.layout.checkboxes, null, false)
            }, modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(
                    bottom = dimensionResource(
                        id = R.dimen.margin_padding_around
                    )
                )
        )
        AndroidView(
            factory = { ctx ->
                LayoutInflater.from(ContextThemeWrapper(ctx, style))
                    .inflate(R.layout.orders, null, false)
            }, modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
        )

    }
}

@Preview(showBackground = true)
@Composable
private fun EssentialScreenPreview() {
    EssentialScreen()
}