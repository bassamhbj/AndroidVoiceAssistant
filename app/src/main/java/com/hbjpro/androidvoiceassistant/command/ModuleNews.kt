package com.hbjpro.androidvoiceassistant.command

import com.hbjpro.androidvoiceassistant.common.data.NewsData
import com.hbjpro.androidvoiceassistant.`interface`.INewsApi
import com.hbjpro.androidvoiceassistant.common.rest.ApiClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ModuleNews {

    fun getNewsFeed(callback: NewsModuleCallback){
        ApiClient.createService(INewsApi::class.java).getTopHeadlines("jp", "b13d74d28e0a4c30b9945524dfec7faf").enqueue(object: Callback<NewsData>{
            override fun onFailure(call: Call<NewsData>, t: Throwable) {
                callback.onError(t.stackTrace.toString())
            }

            override fun onResponse(call: Call<NewsData>, response: Response<NewsData>) {
                if(response?.body() != null && response.code() == 200){
                    callback.onSuccess(response.body()!!)
                }else{
                    callback.onError("News Feed could not be loaded...")
                }
            }
        })
    }

    interface NewsModuleCallback{
        fun onSuccess(newsData: NewsData)
        fun onError(errorMsg: String)
    }
}