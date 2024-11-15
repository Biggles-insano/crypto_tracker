package com.uvg.mejia.samuel.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.uvg.mejia.samuel.repository.Asset
import com.uvg.mejia.samuel.repository.AssetDetail
import com.uvg.mejia.samuel.repository.CryptoRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class CryptoViewModel : ViewModel() {
    private val repository = CryptoRepository()

    private val _assets = MutableStateFlow<List<Asset>>(emptyList())
    val assets: StateFlow<List<Asset>> get() = _assets

    fun getAssetDetails(assetId: String) = MutableStateFlow<AssetDetail?>(null).also { flow -> // Cambiado a AssetDetail?
        viewModelScope.launch {
            flow.value = repository.getAssetDetails(assetId)
        }
    }

    init {
        viewModelScope.launch {
            _assets.value = repository.getAssets()
        }
    }
}