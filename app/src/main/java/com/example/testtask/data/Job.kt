package com.example.testtask.data


data class Job(
    val viewersCount: String,
    val jobTitle: String,
    val location: String,
    val companyName: String,
    val experience: String,
    val publishDate: String,
    var isFavorite: Boolean,
    val salary: String?,
    val schedules: List<String>,
    val appliedNumber: Int?,
    val description: String,
    val responsibilities: String,
    val questions: List<String>
)
