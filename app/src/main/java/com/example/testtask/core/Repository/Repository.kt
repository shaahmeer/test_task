package com.example.core.Repository

import Offer
import Vacancy


interface Repository {
    suspend fun getOffers(): List<Offer>
    suspend fun getVacancies(): List<Vacancy>
}
