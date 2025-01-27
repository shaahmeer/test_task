package com.example.core.Repository

import com.example.core.data.Offer
import com.example.core.data.Vacancy

class RepositoryImpl(private val remoteDataSource: RemoteDataSource) : Repository {
    override suspend fun getOffers(): List<Offer> {
        return remoteDataSource.fetchOffers()
    }

    override suspend fun getVacancies(): List<Vacancy> {
        return remoteDataSource.fetchVacancies()
    }
}
