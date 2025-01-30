package com.example.core.Repository

import Offer
import Vacancy
import com.example.core.datasource.RemoteDataSource


class RepositoryImpl(private val remoteDataSource: RemoteDataSource) : Repository {
    override suspend fun getOffers(): List<Offer> {
        return remoteDataSource.fetchOffers()
    }

    override suspend fun getVacancies(): List<Vacancy> {
        return remoteDataSource.fetchVacancies()
    }
}
