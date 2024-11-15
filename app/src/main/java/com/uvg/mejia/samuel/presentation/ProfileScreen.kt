package com.uvg.mejia.samuel.presentation

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.uvg.mejia.samuel.viewmodel.CryptoViewModel
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(navController: NavController, assetId: String, viewModel: CryptoViewModel = viewModel()) {
    val assetDetail = viewModel.getAssetDetails(assetId).collectAsState(initial = null).value

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Asset Details") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Text("Back")
                    }
                }
            )
        },
        content = { padding ->  // Se utiliza el parámetro 'padding'
            assetDetail?.let {
                Column(
                    modifier = Modifier
                        .padding(padding)  // Aplicamos el padding de Scaffold
                        .padding(16.dp)   // Padding adicional personalizado
                ) {
                    Text(text = "Details for Asset: ${it.name}")
                    Text(text = "Supply: ${it.supply}")
                    Text(text = "Max Supply: ${it.maxSupply ?: "N/A"}")
                    Text(text = "Market Cap USD: ${it.marketCapUsd}")
                    Text(text = "Volume 24Hr: ${it.volumeUsd24Hr}")
                    Text(text = "Price USD: ${it.priceUsd}")
                    Text(text = "Change 24Hr: ${it.changePercent24Hr}")
                    Text(text = "Explorer: ${it.explorer}")
                }
            }
        }
    )
}