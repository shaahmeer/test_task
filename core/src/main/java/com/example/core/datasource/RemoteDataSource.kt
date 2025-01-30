package com.example.core.datasource

import Offer
import Vacancy
import com.example.datasource.FetchApi


class RemoteDataSource(private val api: FetchApi) {
    suspend fun fetchOffers(): List<Offer> = api.getOffers()
    suspend fun fetchVacancies(): List<Vacancy> = api.getVacancies()
}
