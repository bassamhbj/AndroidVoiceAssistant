package com.hbjpro.androidvoiceassistant.Command

import com.google.gson.GsonBuilder
import com.hbjpro.androidvoiceassistant.Data.NewsData
import com.hbjpro.androidvoiceassistant.Interface.INewsApi
import com.hbjpro.androidvoiceassistant.Tools.Tools
import io.reactivex.Observable
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class ModuleNews {

    fun getNewsFeed(callback: NewsModuleCallback){
        val retrofit = Retrofit.Builder().addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(Tools.NEWS_API_BASE_URL).build()

        val newsApi = retrofit.create(INewsApi::class.java)

        var response = newsApi.getTopHeadlines("jp", "")

        callback.onSuccess(response)
    }

    interface NewsModuleCallback(){
        fun onSuccess(observable: Observable<NewsData>)
        fun onError()
    }
}