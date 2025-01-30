package com.example.core.usecases

import Offer
import com.example.core.Repository.Repository




class GetOffersUseCase(private val repository: Repository) {
    suspend operator fun invoke(): List<Offer> = repository.getOffers()
}

