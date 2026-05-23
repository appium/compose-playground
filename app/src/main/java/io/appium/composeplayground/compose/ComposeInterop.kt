@file:OptIn(androidx.compose.ui.ExperimentalComposeUiApi::class)

package io.appium.composeplayground.compose

import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.testTagsAsResourceId

fun Modifier.composeTestInterop(): Modifier = semantics { testTagsAsResourceId = true }
