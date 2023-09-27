package com.github.hehecoi222.week4onclass.presentation.screens

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LargeTopAppBar
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import com.github.hehecoi222.week4onclass.R
import com.github.hehecoi222.week4onclass.presentation.activities.Assignment1
import com.github.hehecoi222.week4onclass.presentation.activities.Assignment2

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen() {
    val topBarScrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior(rememberTopAppBarState())
    var isOptMenuExpand by remember { mutableStateOf(false) }
    val optMenuItems = stringArrayResource(id = R.array.menu_options)
    var optMenuSelectedItem by remember {
        mutableStateOf("")
    }
    val context = LocalContext.current

    Scaffold(topBar = {
        MainScreenTopAppBar(scrollBehavior = topBarScrollBehavior, onClickMenu = {
            isOptMenuExpand = true
        }, menuExpand = isOptMenuExpand, onDismissMenu = {
            isOptMenuExpand = false
        }, menuItems = optMenuItems) { text ->
            optMenuSelectedItem = text
            when (optMenuSelectedItem) {
                optMenuItems.get(0) -> context.startActivity(
                    Intent(
                        context,
                        Assignment1::class.java
                    )
                )

                optMenuItems.get(1) -> context.startActivity(
                    Intent(
                        context,
                        Assignment2::class.java
                    )
                )
            }
            isOptMenuExpand = false
        }
    }) {
        Text("Hello World", modifier = Modifier.padding(it))
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun MainScreenTopAppBar(
    modifier: Modifier = Modifier,
    scrollBehavior: TopAppBarScrollBehavior,
    onClickMenu: () -> Unit,
    onDismissMenu: () -> Unit,
    menuExpand: Boolean,
    menuItems: Array<String>,
    setMenuItem: (String) -> Unit
) {
    LargeTopAppBar(actions = {
        IconButton(onClick = onClickMenu) {
            Icon(Icons.Default.MoreVert, "More menu")
            DropdownMenu(expanded = menuExpand, onDismissRequest = onDismissMenu) {
                menuItems.forEach { text ->
                    DropdownMenuItem(text = {
                        Text(text, maxLines = 1, overflow = TextOverflow.Ellipsis)
                    }, onClick = {
                        setMenuItem(text)
                    })
                }
            }
        }
    }, title = {
        Text(
            stringResource(id = R.string.app_name),
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
    }, scrollBehavior = scrollBehavior, modifier = modifier
    )
}