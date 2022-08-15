package com.example.skiptraffi.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.skiptraffi.Screen
import com.example.skiptraffi.data.Area
import com.example.skiptraffi.util.AppState

@Composable
fun TrafficScreen(appState: AppState, viewModel: TrafficViewModel, navController: NavController) {
    appState.setToolbarState(
        title = "Städer",
        hasBackButton = false,
        hasEndButton = true,
        onEndButtonClicked = { viewModel.getAreaList() })
    viewModel.coordinatesArea
    viewModel.trafficAreas?.let {
        TrafficAreaList(
            trafficAreaList = it,
            navController,
            viewModel,
            bottomBarHeight = appState.bottomBarHeight.value
        )
    }
    viewModel.getAreaListWithCoordinates()
    viewModel.getAreaList()
}

@Composable
fun TrafficAreaList(
    trafficAreaList: List<Area>,
    navController: NavController,
    viewModel: TrafficViewModel,
    bottomBarHeight: Dp
) {
    var selectedIndex by remember { mutableStateOf(-1) }


    LazyColumn(contentPadding = PaddingValues(bottom = bottomBarHeight)) {
        itemsIndexed(items = trafficAreaList) { index, item ->
            TrafficAreaItem(area = item, index, selectedIndex) { i ->
                selectedIndex = i
                navController.navigate(route = Screen.Detail.passCity(item.name))
            }
        }
    }
}

@Composable
fun TrafficAreaItem(area: Area, index: Int, selectedIndex: Int, onClick: (Int) -> Unit) {
    Card(
        modifier = Modifier
            .padding(8.dp, 4.dp)
            .fillMaxWidth()
            .height(110.dp)
            .clickable { onClick(index) },
        shape = RoundedCornerShape(8.dp), elevation = 4.dp
    ) {
        Surface() {

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
                        text = area.name,
                        style = MaterialTheme.typography.subtitle1,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }
    }
}

