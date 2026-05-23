package io.appium.composeplayground.compose

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp

const val TEXT_INPUT_INITIAL = "Enter your text here"

@Composable
fun TextInputDemo(modifier: Modifier = Modifier) {
    var textState by remember {
        mutableStateOf(
            TextFieldValue(
                text = TEXT_INPUT_INITIAL,
                selection = TextRange(TEXT_INPUT_INITIAL.length),
            ),
        )
    }

    Surface(
        modifier = modifier
            .fillMaxSize()
            .composeTestInterop()
            .padding(16.dp),
    ) {
        BasicTextField(
            value = textState,
            onValueChange = { textState = it },
            textStyle = MaterialTheme.typography.bodyLarge,
            modifier = Modifier
                .fillMaxWidth()
                .testTag("text_input"),
        )
    }
}
