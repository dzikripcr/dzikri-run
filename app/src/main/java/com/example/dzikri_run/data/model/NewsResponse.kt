package com.example.dzikri_run.data.model

data class NewsResponse(
    val message: String,
    val total: Int,
    val data: List<NewsModel>
)