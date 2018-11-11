package com.hbjpro.androidvoiceassistant.common.rest

import com.hbjpro.androidvoiceassistant.common.tools.Tools
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import okhttp3.logging.HttpLoggingInterceptor

object ApiClient {
    private var retrofit:Retrofit

    init {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        val okHttpClient = OkHttpClient.Builder().addInterceptor(interceptor).build()

        retrofit = Retrofit.Builder()
                .baseUrl(Tools.NEWS_API_BASE_URL)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
    }

    fun<T> createService(serviceClass: Class<T>): T{
        return retrofit.create(serviceClass)
    }
}