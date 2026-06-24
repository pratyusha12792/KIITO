package com.kito.feature.khaoogully

import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.v2.runComposeUiTest
import com.kito.feature.khaoogully.presentation.FoodHomeUiState
import com.kito.feature.khaoogully.presentation.KhaooGullyHomeContent
import kotlin.test.Test

@OptIn(ExperimentalTestApi::class)
class KhaooGullyUiTest {

    @Test
    fun khaoogully_content_renders() = runComposeUiTest {
        setContent {
            KhaooGullyHomeContent(
                state = FoodHomeUiState(),
                showCampusMenu = false,
                onShowCampusMenuChange = {},
                onSearchChange = {},
                onCategoryClick = {},
                onRestaurantClick = {},
                onRetry = {},
                onCampusClick = {},
                onLocationClick = {}
            )
        }
        onNodeWithTag("khaoogully_content").assertIsDisplayed()
    }
}
