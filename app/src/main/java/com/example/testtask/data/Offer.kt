package com.example.testtask.data

data class Offer(
    val id: String? = null,
    val title: String,
    val link: String,
    val button: Button? = null
)

data class Button(
    val text: String
)
