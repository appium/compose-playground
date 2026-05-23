package io.appium.composeplayground

import androidx.compose.ui.test.assertCountEquals
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onAllNodesWithText
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextClearance
import androidx.compose.ui.test.performTextInput
import androidx.compose.ui.test.performTextReplacement
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.matcher.ViewMatchers.withText
import io.appium.composeplayground.compose.TEXT_INPUT_INITIAL
import io.appium.composeplayground.navigation.Routes
import org.junit.Rule
import org.junit.Test

class ComposePlaygroundTest {

    @get:Rule
    val composeRule = createAndroidComposeRule<MainActivity>()

    @Test
    fun clickableComponent_showsDialogOnClick() {
        openMenuItem(Routes.CLICKABLE)

        composeRule.onNodeWithTag("lol").assertIsDisplayed()
        composeRule.onNodeWithContentDescription("desc").assertIsDisplayed()
        composeRule.onNodeWithText("Click to see dialog").performClick()
        composeRule
            .onNodeWithText("Congratulations! You just clicked the text successfully")
            .assertIsDisplayed()
    }

    @Test
    fun horizontalCarousel_showsTwoGraceHopperLabels() {
        openMenuItem(Routes.CAROUSEL)

        composeRule.onAllNodesWithText("Grace Hopper").assertCountEquals(2)
    }

    @Test
    fun displayText_rendersComposeContent() {
        openMenuItem(Routes.DISPLAY_TEXT)

        composeRule.onNodeWithText("Hello from Jetpack Compose").assertIsDisplayed()
    }

    @Test
    fun textInput_supportsAppendReplaceAndClear() {
        openMenuItem(Routes.TEXT_INPUT)

        val field = composeRule.onNodeWithTag("text_input")
        field.assertIsDisplayed()
        composeRule.onNode(hasText(TEXT_INPUT_INITIAL)).assertIsDisplayed()

        field.performTextInput("hello")
        composeRule.onNode(hasText("${TEXT_INPUT_INITIAL}hello")).assertIsDisplayed()

        field.performTextReplacement("テスト")
        composeRule.onNode(hasText("テスト")).assertIsDisplayed()

        field.performTextClearance()
        composeRule.onNode(hasText(TEXT_INPUT_INITIAL)).assertDoesNotExist()
    }

    private fun openMenuItem(label: String) {
        onView(withText(label)).perform(click())
        composeRule.waitForIdle()
    }
}
