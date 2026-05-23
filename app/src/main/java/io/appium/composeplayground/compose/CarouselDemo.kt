package io.appium.composeplayground.compose

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

private const val GRACE_HOPPER = "Grace Hopper"

@Composable
fun CarouselDemo(modifier: Modifier = Modifier) {
    LazyRow(
        modifier = modifier
            .fillMaxSize()
            .composeTestInterop(),
        contentPadding = PaddingValues(16.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp),
    ) {
        items(listOf(GRACE_HOPPER, GRACE_HOPPER)) { name ->
            Card(modifier = Modifier.fillParentMaxWidth(0.85f)) {
                Text(
                    text = name,
                    style = MaterialTheme.typography.headlineSmall,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(24.dp),
                )
            }
        }
    }
}
