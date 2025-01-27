package com.example.core.datasource

import com.example.core.data.Offer
import com.example.core.data.Vacancy


interface FetchApi {
        @GET("your_api_endpoint") // Replace with your actual endpoint
        suspend fun getOffers(): List<Offer>

        @GET("your_api_endpoint") // Replace with your actual endpoint
        suspend fun getVacancies(): List<Vacancy>
    }

