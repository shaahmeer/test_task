package com.example.core.usecases

import com.example.core.Repository.Repository
import com.example.core.data.Offer
import com.example.core.data.Job

class getOffersUseCase(private val repository: Repository) {
    suspend operator fun invoke(): List<Offer> = repository.getOffers()
}

