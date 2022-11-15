package io.github.keddnyo.midoze.ui.presentation.dial

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import io.github.keddnyo.midoze.internal.CardContentOffset
import io.github.keddnyo.midoze.local.viewmodels.watchface.WatchfaceViewModel

@Composable
fun DialRoute(
    viewModel: WatchfaceViewModel
) {

    val watchfaceList = viewModel.getWatchfaceList()

    LazyColumn(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        watchfaceList.forEach { watchface ->
            item {
                Card(
                    modifier = Modifier
                        .widthIn(min = 0.dp, max = 600.dp)
                        .padding(all = CardContentOffset),
                    elevation = CardDefaults.outlinedCardElevation(2.dp),
                    border = BorderStroke(
                        0.5.dp,
                        Color.Gray
                    )
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(CardContentOffset)
                    ) {
                        AsyncImage(
                            model = watchface.preview,
                            contentDescription = null,
                            modifier = Modifier
                                .width(90.dp)
                                .height(140.dp)
                        )
                        Spacer(
                            modifier = Modifier
                                .width(CardContentOffset)
                        )
                        Column(
                            modifier = Modifier
                                .align(Alignment.CenterVertically),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(
                                text = watchface.title,
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Bold,
                                textAlign = TextAlign.Center
                            )
                            Text(
                                text = watchface.tabName,
                                fontSize = 10.sp,
                                textAlign = TextAlign.Center
                            )
                            Divider(
                                modifier = Modifier
                                    .padding(CardContentOffset),
                                thickness = 0.5.dp,
                                color = Color.Gray
                            )
                            watchface.introduction?.let { introduction->
                                Text(
                                    text = introduction,
                                    fontSize = 10.sp
                                )
                            }
                        }
                    }
                }
            }
        }
    }

}