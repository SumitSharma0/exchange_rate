package com.example.exchangerateapp.util

import com.example.exchangerateapp.data.response.ErrorResponse
import com.example.exchangerateapp.data.response.ExchangeRatesResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.http.appendPathSegments
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import javax.inject.Inject

val ktorClient = HttpClient(CIO) {
    install(ContentNegotiation) {
        json(Json {
            ignoreUnknownKeys = true
        })
    }
}

class NetworkService @Inject constructor(
    private val ktorClient: HttpClient
)  {

    companion object {

        const val APP_ID = ""//BuildConfig.api_key

        private const val URL = "https://openexchangerates.org/api/"
    }

    suspend fun getExchangeRates(): Map<String, Double> {
        val httpResponse = ktorClient.get(URL) {
            url {
                appendPathSegments("latest.json")
                parameters.append("app_id", APP_ID)
            }
        }
        return if (httpResponse.status.value in 200..299) {
            httpResponse.body<ExchangeRatesResponse>().rates
        } else {
            val error = httpResponse.body<ErrorResponse>()
            throw IllegalStateException(error.message)
        }
    }
}