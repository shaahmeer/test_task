package com.example.testtask.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.testtask.data.Job

class SharedViewModel : ViewModel() {
    private val _allJobs = MutableLiveData<List<Job>>()
    val allJobs: LiveData<List<Job>> get() = _allJobs

    private val _filteredJobs = MutableLiveData<List<Job>>()
    val filteredJobs: LiveData<List<Job>> get() = _filteredJobs

    private val _favoriteJobs = MutableLiveData<List<Job>>() // Add favorite jobs LiveData
    val favoriteJobs: LiveData<List<Job>> get() = _favoriteJobs

    // Initialize job list
    fun setAllJobs(jobs: List<Job>) {
        _allJobs.value = jobs
        _filteredJobs.value = jobs // Initially, all jobs are displayed
        updateFavoriteJobs() // Update the favorite jobs list
    }

    // Filter jobs based on the query
    fun filterJobs(query: String) {
        val currentJobs = _allJobs.value ?: return
        _filteredJobs.value = if (query.isEmpty()) {
            currentJobs
        } else {
            currentJobs.filter {
                it.jobTitle.contains(query, ignoreCase = true) ||
                        it.companyName.contains(query, ignoreCase = true) ||
                        it.location.contains(query, ignoreCase = true)
            }
        }
    }
    fun addFavoriteJob(job: Job) {
        val currentFavorites = _favoriteJobs.value?.toMutableList() ?: mutableListOf()
        currentFavorites.add(job)
        _favoriteJobs.value = currentFavorites
    }

    fun toggleFavorite(job: Job) {
        val currentJobs = _allJobs.value?.toMutableList() ?: return
        val index = currentJobs.indexOf(job)
        if (index != -1) {
            val updatedJob = job.copy(isFavorite = !job.isFavorite)
            currentJobs[index] = updatedJob
            _allJobs.value = currentJobs

            _filteredJobs.value = _filteredJobs.value?.map {
                if (it == job) updatedJob else it

            }
            updateFavoriteJobs() // Update the favorite jobs list
        }
    }

    // Remove a job from favorites
    fun removeFavoriteJob(job: Job) {
        if (job.isFavorite) toggleFavorite(job)
    }

    // Update the favorite jobs list
    private fun updateFavoriteJobs() {
        _favoriteJobs.value = _allJobs.value?.filter { it.isFavorite }
    }
}
