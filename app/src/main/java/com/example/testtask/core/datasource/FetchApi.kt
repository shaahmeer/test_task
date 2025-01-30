package com.example.datasource

import Offer
import Vacancy

import retrofit2.http.GET

interface FetchApi {
    @GET("u/0/uc?id=1z4TbeDkbfXkvgpoJprXbN85uCcD7f00r&export=download")
    suspend fun getOffers(): List<Offer>

    @GET("u/0/uc?id=1z4TbeDkbfXkvgpoJprXbN85uCcD7f00r&export=download")
    suspend fun getVacancies(): List<Vacancy>
}
