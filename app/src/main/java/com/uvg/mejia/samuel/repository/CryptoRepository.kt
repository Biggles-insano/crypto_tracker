package com.uvg.mejia.samuel.repository

import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.plugins.logging.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json

@Serializable
data class AssetResponse(val data: List<Asset>)

@Serializable
data class Asset(
    val id: String,
    val rank: String,
    val symbol: String,
    val name: String,
    val supply: String,
    val maxSupply: String?,
    val marketCapUsd: String,
    val priceUsd: String,
    val changePercent24Hr: String,
    val updated: String? = null
)

@Serializable
data class AssetDetailResponse(
    val data: AssetDetail,
    val timestamp: Long
)

@Serializable
data class AssetDetail(
    val id: String,
    val rank: String,
    val symbol: String,
    val name: String,
    val supply: String,
    val maxSupply: String?,
    val marketCapUsd: String,
    val volumeUsd24Hr: String,
    val priceUsd: String,
    val changePercent24Hr: String,
    val vwap24Hr: String,
    val explorer: String
)

class CryptoRepository {
    private val client = HttpClient {
        install(ContentNegotiation) {
            json(Json {
                ignoreUnknownKeys = true
            })
        }
        install(Logging) {
            logger = Logger.SIMPLE
            level = LogLevel.BODY
        }
    }

    suspend fun getAssets(): List<Asset> {
        val response: HttpResponse = client.get("https://api.coincap.io/v2/assets")
        return response.body<AssetResponse>().data
    }

    suspend fun getAssetDetails(assetId: String): AssetDetail {
        val response: HttpResponse = client.get("https://api.coincap.io/v2/assets/$assetId")
        return response.body<AssetDetailResponse>().data
    }
}