package com.example.dzikri_run.data.api

import com.example.dzikri_run.data.model.NewsResponse
import retrofit2.http.GET

interface NewsApiService {

    @GET("v1/cnn-news/")
    suspend fun getNews(): NewsResponse
}