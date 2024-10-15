package com.example.swiss.ui.dialogs

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.ui.test.assertIsEnabled
import androidx.compose.ui.test.assertIsNotEnabled
import androidx.compose.ui.test.isDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performTextInput
import com.example.swiss.ui.activities.sheets.AddActivityBottomSheet
import com.example.swiss.ui.theme.SwissTheme
import com.example.swiss.ui.vm.ViewModel
import io.mockk.mockkObject
import org.junit.Rule
import org.junit.Test
import org.koin.compose.koinInject

class AddActivityBottomSheetTest {

    @get:Rule
    val composeRule = createComposeRule()
    lateinit var viewModelMock: ViewModel

    @OptIn(ExperimentalMaterial3Api::class)
    fun setUpMock(
        mockLambda: () -> Unit = {}
    ) {
        composeRule.setContent {
            SwissTheme {
                viewModelMock = koinInject()
                mockkObject(viewModelMock) {
                    mockLambda.invoke()
                }
                AddActivityBottomSheet()
            }
        }
    }

    @Test
    fun isSheetVisible() {
        setUpMock()
        composeRule.onNodeWithTag("Sheet").isDisplayed()
    }

    @Test
    fun isConfirmButtonDisabled() {
        setUpMock()
        composeRule
            .onNodeWithTag("textField")
            .performTextInput("")

        composeRule
            .onNodeWithText("Conferma")
            .assertIsNotEnabled()
    }

    @Test
    fun isConfirmButtonEnabled() {
        setUpMock()
        composeRule
            .onNodeWithTag("textField")
            .performTextInput("Prova")

        composeRule
            .onNodeWithText("Conferma")
            .assertIsEnabled()
    }

}