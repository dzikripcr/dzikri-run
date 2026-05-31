package com.example.dzikri_run.data.model

data class NewsModel(
    val title: String,
    val link: String,
    val contentSnippet: String,
    val isoDate: String,
    val image: NewsImage?
)