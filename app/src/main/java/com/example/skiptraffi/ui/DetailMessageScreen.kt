package com.example.skiptraffi.ui

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.skiptraffi.data.Message

@Composable
fun DetailScreen(
    navController: NavController,
    viewModel: TrafficMessageViewModel,
    cityName: String?
) {
    Surface(color = MaterialTheme.colors.background, modifier = Modifier.fillMaxSize()) {
        viewModel.getMessageList()
        TrafficMessageList(trafficMessegeList = viewModel.trafficMessage ?: emptyList())
        Log.d("TestHej", "...: " + cityName)
    }
}


@Composable
fun TrafficMessageList(trafficMessegeList: List<Message>) {
    LazyColumn {
        itemsIndexed(items = trafficMessegeList) { index, item ->
            TrafficMessageItem(trafficMessage = item)
            Log.d("TestHej", "item: " + item.title)
        }
    }
}

@Composable
fun TrafficMessageItem(trafficMessage: Message) {
    Card(
        modifier = Modifier
            .padding(8.dp, 4.dp)
            .fillMaxWidth(),
        shape = RoundedCornerShape(8.dp), elevation = 4.dp
    ) {
        Surface(color = MaterialTheme.colors.background) {
            Row(
                Modifier
                    .padding(4.dp)
                    .fillMaxSize()
            ) {

                Column(
                    verticalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .padding(4.dp)
                        .fillMaxHeight()
                        .weight(0.8f)
                ) {
                    Text(
                        text = trafficMessage.title,
                        style = MaterialTheme.typography.subtitle1,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = "Priority: " + trafficMessage.priority.toString(),
                        style = MaterialTheme.typography.body2,
                        fontWeight = FontWeight.Normal
                    )
                    Text(
                        text = trafficMessage.exactlocation,
                        style = MaterialTheme.typography.body2,
                        fontWeight = FontWeight.Normal
                    )
                    Text(
                        modifier = Modifier.padding(top = 4.dp),
                        text = trafficMessage.description,
                        style = MaterialTheme.typography.body2,
                        fontWeight = FontWeight.Normal
                    )
                    Text(
                        modifier = Modifier.padding(top = 4.dp),
                        text = trafficMessage.subcategory,
                        style = MaterialTheme.typography.body2,
                        fontWeight = FontWeight.Normal
                    )
                }
            }
        }
    }
}