package com.kito.feature.gpa.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.kito.core.presentation.components.RopeTabRow
import org.koin.compose.koinInject

@Composable
fun GPAScreen(
    viewModel: GPAViewmodel = koinInject()
) {

    val roll by viewModel.roll.collectAsState()
    var selectedTab by remember { mutableStateOf(0) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF121116))
    ) {

        Spacer(
            modifier = Modifier.height(
                WindowInsets.statusBars
                    .asPaddingValues()
                    .calculateTopPadding() + (12.dp)
            )
        )

        Box(
            modifier = Modifier.padding(horizontal = 12.dp)
        ) {
            GPAHeader(
                roll = roll,
                isLoading = roll.isEmpty(),
                onSemesterSelected = { selectedSemester ->
                    // TODO: handle selected semester here
                },

                onBranchSelected = { selectedBranch ->
                    // TODO: handle selected branch here
                }
            )
        }

        Spacer(modifier = Modifier.height(12.dp))

        RopeTabRow(
            selectedTab = selectedTab,
            onTabSelected = { selectedTab = it }
        )

        Spacer(modifier = Modifier.height(16.dp))

        when (selectedTab) {
            0 -> SGPAScreen()
            1 -> CGPAScreen()
        }

        Spacer(
            modifier = Modifier.height(
                WindowInsets.navigationBars
                    .asPaddingValues()
                    .calculateBottomPadding()
            )
        )
    }
}