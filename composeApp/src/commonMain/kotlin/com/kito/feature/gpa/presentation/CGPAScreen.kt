package com.kito.feature.gpa.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.kito.core.presentation.components.UIColors
import kotlin.math.roundToInt

@Composable
fun CGPAScreen() {

    val uiColors = UIColors()

    var oldCgpa by remember { mutableStateOf("") }
    var completedSem by remember { mutableStateOf("") }
    var currentSgpa by remember { mutableStateOf("") }

    val newCgpa by remember(oldCgpa, completedSem, currentSgpa) {
        derivedStateOf {
            val old = oldCgpa.toDoubleOrNull() ?: 0.0
            val sem = completedSem.toIntOrNull() ?: 0
            val sgpa = currentSgpa.toDoubleOrNull() ?: 0.0
            calculateCGPA(old, sem, sgpa)
        }
    }

    val cgpaInputs = listOf(
        "Previous CGPA",
        "Semesters Completed",
        "Current SGPA"
    )

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF121116))
            .padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.spacedBy(6.dp) // 🔥 better spacing
    ) {

        item { Spacer(modifier = Modifier.height(16.dp)) }

        itemsIndexed(cgpaInputs) { index, label ->

            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .heightIn(min = 110.dp),
                colors = CardDefaults.cardColors(containerColor = Color.Transparent),
                elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
                shape = RoundedCornerShape(
                    topStart = if (index == 0) 24.dp else 4.dp,
                    topEnd = if (index == 0) 24.dp else 4.dp,
                    bottomStart = if (index == cgpaInputs.lastIndex) 24.dp else 4.dp,
                    bottomEnd = if (index == cgpaInputs.lastIndex) 24.dp else 4.dp
                )
            ) {

                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(
                            brush = Brush.linearGradient(
                                colors = listOf(
                                    uiColors.cardBackground,
                                    Color(0xFF2F222F),
                                    Color(0xFF2F222F),
                                    uiColors.cardBackgroundHigh
                                )
                            )
                        )
                        .padding(16.dp)
                ) {

                    Column {

                        Text(
                            text = label,
                            fontFamily = FontFamily.Monospace,
                            fontWeight = FontWeight.Bold,
                            color = uiColors.textPrimary,
                            style = MaterialTheme.typography.titleMedium
                        )

                        Spacer(modifier = Modifier.height(12.dp))

                        when (index) {

                            0 -> OutlinedTextField(
                                value = oldCgpa,
                                onValueChange = { oldCgpa = it },
                                modifier = Modifier.fillMaxWidth(),
                                singleLine = true
                            )

                            1 -> OutlinedTextField(
                                value = completedSem,
                                onValueChange = { completedSem = it },
                                modifier = Modifier.fillMaxWidth(),
                                singleLine = true
                            )

                            2 -> OutlinedTextField(
                                value = currentSgpa,
                                onValueChange = { currentSgpa = it },
                                modifier = Modifier.fillMaxWidth(),
                                singleLine = true
                            )
                        }
                    }
                }
            }
        }

        item { Spacer(modifier = Modifier.height(20.dp)) }

        //RESULT CARD
        item {

            val rounded = (newCgpa * 100).roundToInt() / 100.0

            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 24.dp),
                colors = CardDefaults.cardColors(containerColor = Color.Transparent),
                elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
                shape = RoundedCornerShape(24.dp)
            ) {

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(
                            brush = Brush.linearGradient(
                                colors = listOf(
                                    uiColors.cardBackground,
                                    Color(0xFF2F222F),
                                    Color(0xFF2F222F),
                                    uiColors.cardBackgroundHigh
                                )
                            )
                        )
                        .padding(vertical = 24.dp),
                    contentAlignment = Alignment.Center
                ) {

                    Column(horizontalAlignment = Alignment.CenterHorizontally) {

                        Text(
                            text = "CGPA",
                            fontFamily = FontFamily.Monospace,
                            color = uiColors.textSecondary,
                            style = MaterialTheme.typography.bodyMedium
                        )

                        Spacer(modifier = Modifier.height(6.dp))

                        Text(
                            text = rounded.toString(),
                            fontFamily = FontFamily.Monospace,
                            fontWeight = FontWeight.Bold,
                            color = uiColors.progressAccent,
                            style = MaterialTheme.typography.headlineMedium
                        )
                    }
                }
            }
        }

        item {
            Spacer(
                modifier = Modifier.height(
                    42.dp + WindowInsets.navigationBars.asPaddingValues()
                        .calculateBottomPadding()
                )
            )
        }
    }
}