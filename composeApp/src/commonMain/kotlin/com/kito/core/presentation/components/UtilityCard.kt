package com.kito.core.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.CalendarMonth
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExperimentalMaterial3ExpressiveApi
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.carousel.rememberCarouselState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp


data class Utilities(
    val title : String,
    val icon: ImageVector,
    val itemBoxColor: Color,
    val textColor: Color,
    val iconGradient: Brush
)

val UtilityList = listOf<Utilities>(
    Utilities(
        title = "GPA",
        icon = Icons.Rounded.CalendarMonth,
        itemBoxColor = Color(0xFF583E30),
        textColor = Color(0xFFF9E1C1),
        iconGradient = Brush.horizontalGradient(
            colors = listOf(
                Color(0xFFC69D89),
                Color(0xFF684B3B)
            )
        )
    )
)

@OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterial3ExpressiveApi::class)
@Composable
fun UtilityCard() {
    val colors = UIColors()
    Box(
        modifier = Modifier
            .clip(
                RoundedCornerShape(32.dp)
            )
            .background(
                color = colors.cardBackground
            )
            .fillMaxWidth()
    ) {
        Column {
//            ShrinkingCarouselRow(
//                itemCount = 6,
//                itemWidth = 100.dp,
//                itemSpacing = 8.dp,
//                minScale = 0.6f
//            ) { index ->
//                GradientIcon(
//                    imageVector = Icons.Rounded.CalendarMonth,
//                    contentDescription = "Calendar",
//                    modifier = Modifier
//                        .size(64.dp)
//                        .align(Alignment.BottomEnd)
//                        .offset(x = 8.dp, y = 4.dp)
//                        .graphicsLayer { scaleX = 1.2f; scaleY = 1.2f},
//                    gradient = Brush.horizontalGradient(
//                        colors = listOf(Color(0xFFC7895F), Color(0xFF765138))
//                    )
//                )
//                Text(
//                    text = "GPA",
//                    maxLines = 1,
//                    overflow = TextOverflow.Ellipsis,
//                    modifier = Modifier.padding(8.dp),
//                    fontFamily = FontFamily.Monospace,
//                    style = MaterialTheme.typography.titleMediumEmphasized,
//                    fontWeight = FontWeight.SemiBold,
//                    color = Color(0xFFC7895F)
//                )
//            }
            ParallaxCarouselRow(
                itemCount = UtilityList.size,
                itemWidth = 100.dp,
                itemSpacing = 8.dp,
                contentPadding = PaddingValues(horizontal = 8.dp, vertical = 8.dp),
                parallaxFraction = 0.4f,
                itemBoxColor = { index->
                    UtilityList[index].itemBoxColor
                }
            ) { index ->
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .clickable(
                            onClick = {

                            }
                        )
                ) {
                    GradientIcon(
                        imageVector = Icons.Rounded.CalendarMonth,
                        contentDescription = "Calendar",
                        modifier = Modifier
                            .size(64.dp)
                            .align(Alignment.BottomEnd)
                            .offset(x = 8.dp, y = 4.dp)
                            .graphicsLayer { scaleX = 1.2f; scaleY = 1.2f },
                        gradient = UtilityList[index].iconGradient
                    )
                    Text(
                        text = "GPA",
                        modifier = Modifier.padding(8.dp),
                        fontFamily = FontFamily.Monospace,
                        overflow = TextOverflow.Ellipsis,
                        maxLines = 1,
                        style = MaterialTheme.typography.titleLargeEmphasized,
                        fontWeight = FontWeight.SemiBold,
                        color = UtilityList[index].textColor
                    )
                }
            }
//            ShrinkingParallaxCarouselRow(
//                itemCount = 6,
//                itemWidth = 100.dp,
//                itemSpacing = 8.dp,
//                contentPadding = PaddingValues(horizontal = 8.dp, vertical = 8.dp),
//                cardColor = Color(0xFF5F2B04),
//                cornerRadius = 28.dp,
//                minScale = 0.6f,
//                parallaxFraction = 0.4f
//            ) { index ->
//                GradientIcon(
//                    imageVector = Icons.Rounded.CalendarMonth,
//                    contentDescription = "Calendar",
//                    modifier = Modifier
//                        .size(64.dp)
//                        .align(Alignment.BottomEnd)
//                        .offset(x = 8.dp, y = 4.dp)
//                        .graphicsLayer {
//                            scaleX = 1.2f
//                            scaleY = 1.2f
//                        },
//                    gradient = Brush.horizontalGradient(
//                        colors = listOf(
//                            Color(0xFFC7895F),
//                            Color(0xFF765138)
//                        )
//                    )
//                )
//                Text(
//                    text = "GPA",
//                    maxLines = 1,
//                    overflow = TextOverflow.Ellipsis,
//                    modifier = Modifier.padding(8.dp),
//                    fontFamily = FontFamily.Monospace,
//                    style = MaterialTheme.typography.titleMediumEmphasized,
//                    fontWeight = FontWeight.SemiBold,
//                    color = Color(0xFFC7895F)
//                )
//            }
        }
    }
}