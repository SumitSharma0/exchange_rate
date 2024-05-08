package com.example.exchangerateapp.data.model

data class ExchangeRate(
    val data: Map<String, Double>,
    val lastUpdated: Long
) {
    companion object {
        val Empty = ExchangeRate(
            emptyMap(),
            0
        )
    }
}
