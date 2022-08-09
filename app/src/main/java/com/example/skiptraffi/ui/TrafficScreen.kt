package com.example.skiptraffi.ui

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.skiptraffi.Screen
import com.example.skiptraffi.data.Area
import com.example.skiptraffi.util.Constants.LATITUDE_KEY
import com.example.skiptraffi.util.Constants.LONGITUDE_KEY
import com.google.accompanist.permissions.ExperimentalPermissionsApi


@Composable
fun TrafficScreen(viewModel: TrafficViewModel, navController: NavController) {
    Scaffold(
        topBar = { MyTopAppBar(onClick = { viewModel.getAreaList() }) }
    ) {
        Log.d("TestHej", "2: " + LONGITUDE_KEY)
        Log.d("TestHej", "2: " + LATITUDE_KEY)
        viewModel.coordinatesArea
        viewModel.trafficAreas?.let {
            TrafficAreaList(
                trafficAreaList = it,
                navController,
                viewModel
            )
        }



        LaunchedEffect(key1 = Unit) {
            viewModel.getAreaListWithCoordinates(LONGITUDE_KEY!!, LATITUDE_KEY!!)
            viewModel.getAreaList()
        }
    }
}

@Composable
fun MyTopAppBar(onClick: () -> Unit) {
    TopAppBar(
        title = { Text("My Application") },
        actions = {
            IconButton(onClick = { onClick.invoke() }) {
                Icon(Icons.Filled.Refresh, null)
            }
        },
        backgroundColor = MaterialTheme.colors.primary,
        contentColor = Color.White,
        elevation = 10.dp
    )
}

@Composable
fun TrafficAreaList(
    trafficAreaList: List<Area>,
    navController: NavController,
    viewModel: TrafficViewModel
) {
    var selectedIndex by remember { mutableStateOf(-1) }
    LazyColumn {
        item {
            CurrentPositionItem(onClick = {
                   Log.d("TestHej", "3: " + LONGITUDE_KEY)
                Log.d("TestHej", "3: " + LATITUDE_KEY)


                navController.navigate(route = Screen.Detail.passCity(viewModel.coordinatesArea))

            })
        }

        itemsIndexed(items = trafficAreaList) { index, item ->
            TrafficAreaItem(area = item, index, selectedIndex) { i ->
                selectedIndex = i
                navController.navigate(route = Screen.Detail.passCity(item.name))
            }
        }
    }
}


@Composable
fun CurrentPositionItem(onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .padding(8.dp, 4.dp)
            .fillMaxWidth()
            .height(110.dp)
            .clickable { onClick.invoke() },
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
                        text = "Current Position",
                        style = MaterialTheme.typography.subtitle1,
                        fontWeight = FontWeight.Bold
                    )
                }
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
