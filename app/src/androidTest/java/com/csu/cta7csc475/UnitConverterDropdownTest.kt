package com.csu.cta7csc475

import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import org.junit.Rule
import org.junit.Test

class UnitConverterDropdownTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    @Test
    fun inputDropdown_selectMeters_updatesUnit() {
        // 1. Open the dropdown
        composeTestRule
            .onNodeWithTag("input_unit_button")
            .performClick()

        // 2. Assert dropdown is visible
        composeTestRule
            .onNodeWithTag("input_dropdown")
            .assertExists()

        // 3. Click "Meters"
        composeTestRule
            .onNodeWithTag("input_m")
            .performClick()

        // 4. Assert the button now shows "Meters"
        composeTestRule
            .onNodeWithTag("input_unit_button")
            .assertTextEquals("Meters")
    }

}