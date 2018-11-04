package com.hbjpro.androidvoiceassistant.Command

import com.hbjpro.androidvoiceassistant.Data.NewsData
import com.hbjpro.androidvoiceassistant.Interface.INewsApi
import com.hbjpro.androidvoiceassistant.common.rest.ApiClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ModuleNews {

    fun getNewsFeed(callback: NewsModuleCallback){
        ApiClient.createService(INewsApi::class.java).getTopHeadlines("jp", "").enqueue(object: Callback<NewsData>{
            override fun onFailure(call: Call<NewsData>, t: Throwable) {
                callback.onError()
            }

            override fun onResponse(call: Call<NewsData>, response: Response<NewsData>) {
                if(response?.body() != null && response.code() == 200){
                    callback.onSuccess(response.body()!!)
                }else{
                    callback.onError()
                }
            }
        })
    }

    interface NewsModuleCallback{
        fun onSuccess(newsData: NewsData)
        fun onError()
    }
}