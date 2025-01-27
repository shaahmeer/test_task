package com.example.testtask.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class JobDetailsViewModel : ViewModel() {

    // LiveData for job details
    private val _jobTitle = MutableLiveData<String>()
    val jobTitle: LiveData<String> get() = _jobTitle

    private val _location = MutableLiveData<String>()
    val location: LiveData<String> get() = _location

    private val _companyName = MutableLiveData<String>()
    val companyName: LiveData<String> get() = _companyName

    private val _experience = MutableLiveData<String>()
    val experience: LiveData<String> get() = _experience

    private val _publishDate = MutableLiveData<String>()
    val publishDate: LiveData<String> get() = _publishDate

    private val _viewersCount = MutableLiveData<String>()
    val viewersCount: LiveData<String> get() = _viewersCount

    private val _salary = MutableLiveData<String>()
    val salary: LiveData<String> get() = _salary

    private val _schedules = MutableLiveData<List<String>>()
    val schedules: LiveData<List<String>> get() = _schedules

    private val _appliedNumber = MutableLiveData<Int>()
    val appliedNumber: LiveData<Int> get() = _appliedNumber

    private val _description = MutableLiveData<String>()
    val description: LiveData<String> get() = _description

    private val _responsibilities = MutableLiveData<List<String>>()
    val responsibilities: LiveData<List<String>> get() = _responsibilities

    private val _questions = MutableLiveData<List<String>>()
    val questions: LiveData<List<String>> get() = _questions

    private val _isFavorite = MutableLiveData<Boolean>()
    val isFavorite: LiveData<Boolean> get() = _isFavorite

    // Update job details (called when data is retrieved from the Intent or repository)
    fun setJobDetails(
        jobTitle: String,
        location: String,
        companyName: String,
        experience: String,
        publishDate: String,
        viewersCount: String,
        salary: String,
        schedules: List<String>,
        appliedNumber: Int,
        description: String,
        responsibilities: List<String>,
        questions: List<String>,
        isFavorite: Boolean
    ) {
        _jobTitle.value = jobTitle
        _location.value = location
        _companyName.value = companyName
        _experience.value = experience
        _publishDate.value = publishDate
        _viewersCount.value = viewersCount
        _salary.value = salary
        _schedules.value = schedules
        _appliedNumber.value = appliedNumber
        _description.value = description
        _responsibilities.value = responsibilities
        _questions.value = questions
        _isFavorite.value = isFavorite
    }

    // Toggle favorite status
    fun toggleFavorite() {
        _isFavorite.value = _isFavorite.value?.not()
    }
}
