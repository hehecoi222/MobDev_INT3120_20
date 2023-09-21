package com.github.hehecoi222.week4slideimpl.presentation.screens

import android.content.Intent
import android.net.Uri
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.Badge
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LargeTopAppBar
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.material3.surfaceColorAtElevation
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.hapticfeedback.HapticFeedbackType
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalHapticFeedback
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.github.hehecoi222.week4slideimpl.R
import com.github.hehecoi222.week4slideimpl.presentation.activities.SubActivity
import com.github.hehecoi222.week4slideimpl.ui.theme.StatusBarColor
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen() {
    val topBarScrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior(rememberTopAppBarState())
    var isOptMenuExpand by remember { mutableStateOf(false) }
    var isContextMenuExpand by remember {
        mutableStateOf(false)
    }
    val optMenuItems = stringArrayResource(id = R.array.menu_options)
    val ctxMenuItems = stringArrayResource(id = R.array.context_menu_options)
    var optMenuSelectedItem by remember {
        mutableStateOf("")
    }
    val snackBarHostState by remember {
        mutableStateOf(SnackbarHostState())
    }
    var snackBarMessage by remember {
        mutableStateOf("")
    }
    var ctxMenuCount by remember {
        mutableStateOf(0)
    }
    var intentMessage by remember {
        mutableStateOf("")
    }
    var resultOnce by remember {
        mutableStateOf(false)
    }

    val haptics = LocalHapticFeedback.current
    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()
    val result = remember { mutableStateOf("") }
    val launcher =
        rememberLauncherForActivityResult(contract = ActivityResultContracts.StartActivityForResult(),
            onResult = {
                result.value = it.data?.getStringExtra("return").toString()
                resultOnce = false
            })

    StatusBarColor(
        color = when {
            topBarScrollBehavior.state.collapsedFraction >= 0.5 -> MaterialTheme.colorScheme.surfaceColorAtElevation(
                dimensionResource(
                    id = R.dimen.ele_lv2
                )
            )

            else -> MaterialTheme.colorScheme.surface
        }
    )

    Scaffold(
        modifier = Modifier.nestedScroll(topBarScrollBehavior.nestedScrollConnection),
        topBar = {
            MainScreenTopAppBar(scrollBehavior = topBarScrollBehavior, onClickMenu = {
                isOptMenuExpand = true
            }, menuExpand = isOptMenuExpand, onDismissMenu = {
                isOptMenuExpand = false
            }, menuItems = optMenuItems) { text ->
                optMenuSelectedItem = text
                when (optMenuSelectedItem) {
                    optMenuItems.get(0) -> context.startActivity(Intent(context, SubActivity::class.java))
                    optMenuItems.get(1) -> context.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("https://shopee.vn/")))
                    optMenuItems.get(2) -> context.startActivity(Intent(Intent.ACTION_DIAL, Uri.parse("tel:911")))
                }
                coroutineScope.launch {
                    snackBarMessage = "Item $optMenuSelectedItem is selected"
                    snackbarCaller(snackBarHostState, snackBarMessage)
                }
                isOptMenuExpand = false
            }
        },
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            MainScreenContainer(contentPadding = it,
                buttonClick = {
                    coroutineScope.launch {
                        snackBarMessage = "Congrats, you clicked. Now press for actions"
                        snackbarCaller(
                            snackBarHostState, snackBarMessage
                        )
                    }
                },
                buttonLongClick = {
                    haptics.performHapticFeedback(HapticFeedbackType.LongPress)
                    isContextMenuExpand = true
                },
                menuExpand = isContextMenuExpand,
                onDismissMenu = {
                    isContextMenuExpand = false
                },
                menuItems = ctxMenuItems,
                setMenuItem = { text ->
                    if (text == ctxMenuItems.get(0)) {
                        ctxMenuCount++
                    } else if (text == ctxMenuItems.get(1)) {
                        ctxMenuCount--
                    }
                    isContextMenuExpand = false
                },
                badgeNumber = ctxMenuCount,
                intentMessage = intentMessage,
                onIntentMessageChanged = {
                    intentMessage = it
                },
                onIntentMessageSend = {
                    val intent = Intent(context, SubActivity::class.java)
                    intent.putExtra("message", intentMessage)
                    launcher.launch(intent)
                })
            result.value.let {
                if (it != "null" && !resultOnce) {
                    coroutineScope.launch {
                        snackBarMessage = it
                        snackbarCaller(
                            snackBarHostState, snackBarMessage
                        )
                    }
                    resultOnce = true
                }
            }
            if (snackBarMessage != "") {
                SnackbarBottom(
                    hostState = snackBarHostState,
                    modifier = Modifier
                        .padding(it)
                        .wrapContentSize()
                        .align(Alignment.BottomCenter)
                ) {
                    Text(text = snackBarMessage)
                }
            }
        }
    }
}

private suspend fun snackbarCaller(
    snackBarHostState: SnackbarHostState, message: String
) {
    Log.d("SNACKBAR", "Called $message")
    snackBarHostState.currentSnackbarData?.dismiss()
    snackBarHostState.showSnackbar(
        message = message, duration = SnackbarDuration.Short
    )
}

@Composable
private fun SnackbarBottom(
    modifier: Modifier = Modifier, hostState: SnackbarHostState, messageText: @Composable () -> Unit
) {
    SnackbarHost(
        hostState = hostState, modifier = modifier
    ) {
        Snackbar {
            messageText()
        }
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

@OptIn(ExperimentalFoundationApi::class, ExperimentalMaterial3Api::class)
@Composable
private fun MainScreenContainer(
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues,
    buttonLongClick: () -> Unit = {},
    buttonClick: () -> Unit = {},
    menuExpand: Boolean = false,
    onDismissMenu: () -> Unit = {},
    menuItems: Array<String> = emptyArray(),
    setMenuItem: (String) -> Unit = {},
    badgeNumber: Int = 0,
    intentMessage: String = "",
    onIntentMessageChanged: (String) -> Unit = {},
    onIntentMessageSend: () -> Unit = {},
) {
    LazyColumn(modifier = modifier, contentPadding = contentPadding) {
        item {
            Surface(
                Modifier
                    .fillMaxWidth()
                    .wrapContentSize(Alignment.Center)
            ) {
                Surface(
                    modifier = Modifier
                        .combinedClickable(
                            onLongClick = buttonLongClick,
                            onLongClickLabel = stringResource(id = R.string.long_pressed_label),
                            onClick = buttonClick
                        )
                        .background(
                            MaterialTheme.colorScheme.secondaryContainer, RoundedCornerShape(50.dp)
                        )
                        .padding(10.dp)
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text(
                            stringResource(id = R.string.long_pressed_btn),
                            modifier = Modifier.background(MaterialTheme.colorScheme.secondaryContainer).padding(end = 5.dp),
                            color = MaterialTheme.colorScheme.onSecondaryContainer,
                            style = MaterialTheme.typography.labelLarge
                        )
                        if (badgeNumber != 0) {
                            Surface(
                                modifier = Modifier.background(MaterialTheme.colorScheme.secondaryContainer)
                            ) {
                                Badge(
                                    containerColor = MaterialTheme.colorScheme.error,
                                    contentColor = MaterialTheme.colorScheme.onError
                                ) {
                                    Text(text = badgeNumber.toString(), Modifier.semantics {
                                        contentDescription = "${
                                            when {
                                                badgeNumber <= 1 -> "$badgeNumber time"
                                                else -> "$badgeNumber times"
                                            }
                                        } added"
                                    })
                                }

                            }
                        }
                    }
                }
            }
        }
        item {
            Box {
                Text(
                    text = stringResource(id = R.string.show_here),
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentSize(Alignment.Center)
                )
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

        }
        item {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                TextField(value = intentMessage, onValueChange = {
                    onIntentMessageChanged(it)
                })
                Button(onClick = onIntentMessageSend) {
                    Text(text = stringResource(id = R.string.intent_send_btn))
                }
            }
        }
        items(10) {
            Surface(modifier = Modifier.fillMaxSize()) {
                Text(
                    text = stringResource(id = R.string.screen_1_text),
                    modifier = Modifier
                        .fillMaxSize()
                        .wrapContentSize(Alignment.Center)
                        .padding(vertical = 200.dp)
                )
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun MainScreenPreview() {
    MainScreen()
}

@Preview
@Composable
private fun BoxPreview() {
    Box(modifier = Modifier.wrapContentSize(), contentAlignment = Alignment.BottomCenter) {
        Text(
            text = "TEST", modifier = Modifier
                .background(Color.Red)
                .fillMaxWidth()
        )
    }
}