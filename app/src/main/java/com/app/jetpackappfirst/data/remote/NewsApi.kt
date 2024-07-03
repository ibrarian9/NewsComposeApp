package com.app.jetpackappfirst.data.remote

import com.app.jetpackappfirst.data.remote.dto.NewsResponse
import com.app.jetpackappfirst.util.Constants
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApi {

    @GET("everything")
    suspend fun getNews(
        @Query("q") q: String = "bitcoin",
        @Query("page") page: Int,
        @Query("sources") source: String,
        @Query("apiKey") apiKey: String = Constants.API_KEY
    ): NewsResponse

    @GET("everything")
    suspend fun searchNews(
        @Query("q") searchQuery: String,
        @Query("page") page: Int,
        @Query("sources") source: String,
        @Query("apiKey") apiKey: String = Constants.API_KEY
    ): NewsResponse
}