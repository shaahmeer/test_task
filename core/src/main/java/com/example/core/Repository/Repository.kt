package com.example.core.Repository

import com.example.core.data.Offer
import com.example.core.data.Job

interface Repository {
    suspend fun getOffers(): List<Offer>
    suspend fun getVacancies(): List<Job>
}
