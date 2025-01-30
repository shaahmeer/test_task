package com.example.core.usecases

import Vacancy
import com.example.core.Repository.Repository



class GetVacanciesUseCase(private val repository: Repository) {
    suspend operator fun invoke(): List<Vacancy> = repository.getVacancies()
}
