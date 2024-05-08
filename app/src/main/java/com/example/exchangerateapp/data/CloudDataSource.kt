package com.example.exchangerateapp.data

import com.example.exchangerateapp.data.model.ExchangeRate
import com.example.exchangerateapp.util.NetworkService
import javax.inject.Inject

class CloudDataSource @Inject constructor(
    private val networkService: NetworkService,
) {
    suspend fun getExchangeRate(): ExchangeRate {
        val result = networkService.getExchangeRates()
        val lastUpdated = System.currentTimeMillis()
        return ExchangeRate(result, lastUpdated)
    }
}