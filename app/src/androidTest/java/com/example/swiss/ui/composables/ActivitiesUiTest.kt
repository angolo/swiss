package com.example.swiss.ui.composables

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsNotDisplayed
import androidx.compose.ui.test.isDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import com.example.swiss.network.data.common.Activity
import com.example.swiss.network.data.common.UserModel
import com.example.swiss.ui.activities.ActivitiesView
import com.example.swiss.ui.theme.SwissTheme
import com.example.swiss.ui.vm.ViewModel
import io.mockk.every
import io.mockk.mockkObject
import org.junit.Rule
import org.junit.Test
import org.koin.compose.koinInject

class ActivitiesUiTest {

    @get:Rule
    val composeRule = createComposeRule()
    lateinit var viewModelMock: ViewModel

    fun setUpMock(
        mockLambda: () -> Unit = {}
    ) {
        composeRule.setContent {
            SwissTheme {
                viewModelMock = koinInject()
                mockkObject(viewModelMock) {
                    mockLambda.invoke()
                    ActivitiesView(
                        "1",
                        viewModelMock
                    )
                }
            }
        }
    }

    @Test
    fun verifyNoActivitiesMessageIsVisible() {
        setUpMock { every { viewModelMock.users } returns mutableStateListOf() }
        composeRule
            .onNodeWithText("Nessuna attività trovata!")
            .assertIsDisplayed()

    }

    @Test
    fun verifyNoActivitiesMessageIsNotVisible() {
        setUpMock {
            every { viewModelMock.users } returns mutableStateListOf(
                UserModel(
                    _id = "1",
                    username = "prova1",
                    activities = listOf(
                        Activity()
                    )
                )
            )
        }
        composeRule
            .onNodeWithText("Nessuna attività trovata!")
            .assertIsNotDisplayed()
    }

    @Test
    fun verifyFabOnClick() {
        setUpMock()
        composeRule.run {
            onNodeWithTag("Fab")
                .performClick()

            onNodeWithText("Inserisci il nome dell'attività")
                .isDisplayed()
        }
    }


}