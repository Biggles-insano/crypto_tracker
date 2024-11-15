package com.uvg.mejia.samuel.presentation

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.uvg.mejia.samuel.repository.Asset
import com.uvg.mejia.samuel.viewmodel.CryptoViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListScreen(navController: NavController, viewModel: CryptoViewModel = viewModel()) {
    val assets by viewModel.assets.collectAsState(initial = emptyList())

    Scaffold(
        topBar = { TopAppBar(title = { Text("Crypto Assets") }) },
        content = { innerPadding ->
            LazyColumn(contentPadding = innerPadding) {
                items(assets) { asset ->
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp)
                            .clickable {
                                navController.navigate("profile_screen/${asset.id}")
                            }
                    ) {
                        Column(modifier = Modifier.padding(16.dp)) {
                            Text(text = "${asset.name} (${asset.symbol})")
                            Text(text = "Price: $${asset.priceUsd}")
                            Text(
                                text = if (asset.changePercent24Hr.startsWith("-")) "Baja: ${asset.changePercent24Hr}" else "Sube: ${asset.changePercent24Hr}",
                                color = if (asset.changePercent24Hr.startsWith("-")) Color.Red else Color.Green
                            )
                        }
                    }
                }
            }
        }
    )
}