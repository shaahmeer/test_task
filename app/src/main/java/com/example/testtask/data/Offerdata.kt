package com.example.testtask.data

class Offerdata {

    val offerdata = listOf(
        Offer(
            id = "near_vacancies",
            title = "Вакансии рядом с вами",
            link = "https://hh.ru/"
        ),
        Offer(
            id = "level_up_resume",
            title = "Поднять резюме в поиске",
            link = "https://hh.ru/mentors?from=footer_new&hhtmFromLabel=footer_new&hhtmFrom=main&purposeId=1",
            button = Button(text = "Поднять")
        ),
        Offer(
            id = "temporary_job",
            title = "Временная работа или подработка",
            link = "https://hh.ru/"
        ),
        Offer(
            id = null,
            title = "Полезные статьи и советы",
            link = "https://hh.ru/articles?hhtmFrom=main"
        )


    )
}