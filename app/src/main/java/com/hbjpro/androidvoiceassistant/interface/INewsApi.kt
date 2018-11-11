package com.hbjpro.androidvoiceassistant.`interface`

import com.hbjpro.androidvoiceassistant.common.data.NewsData
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface INewsApi {
    @GET("v2/top-headlines")
    fun getTopHeadlines(@Query("country")country: String, @Query("apiKey")apiKey: String): Call<NewsData>
}