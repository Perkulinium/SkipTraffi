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
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.skiptraffi.Screen
import com.example.skiptraffi.data.Area
import com.example.skiptraffi.ui.components.SearchView
import com.example.skiptraffi.util.AppState
import java.util.*

@Composable
fun TrafficScreen(appState: AppState, viewModel: TrafficViewModel, navController: NavController) {
    appState.setToolbarState(
        title = "Städer",
        hasBackButton = false,
        hasEndButton = true,
        onEndButtonClicked = { viewModel.getAreaList() })


    val textState = remember { mutableStateOf(TextFieldValue("")) }

    Column(modifier = Modifier.fillMaxSize()) {
        SearchView(state = textState)

        viewModel.coordinatesArea
        viewModel.trafficAreas?.let {
            TrafficAreaList(
                trafficAreaList = it,
                navController,
                bottomBarHeight = appState.bottomBarHeight.value,
                textState
            )
        }
        viewModel.getAreaListWithCoordinates()
        viewModel.getAreaList()

    }
}

@Composable
fun TrafficAreaList(
    trafficAreaList: List<Area>,
    navController: NavController,
    bottomBarHeight: Dp,
    state: MutableState<TextFieldValue>
) {
    var selectedIndex by remember { mutableStateOf(-1) }
    var filteredAreas: List<Area>

    LazyColumn(contentPadding = PaddingValues(bottom = bottomBarHeight)) {

        val searchedText = state.value.text
        filteredAreas = if (searchedText.isEmpty()) {
            trafficAreaList
        } else {
            val resultList = ArrayList<Area>()
            for (area in trafficAreaList) {
                if (area.name.contains(searchedText.replaceFirstChar { it.titlecase(Locale.getDefault()) })
                ) {
                    resultList.add(area)
                }
            }
            resultList
        }

        itemsIndexed(items = filteredAreas) { index, item ->
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

