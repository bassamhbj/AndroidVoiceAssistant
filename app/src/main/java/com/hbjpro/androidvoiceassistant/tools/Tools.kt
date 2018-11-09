package com.hbjpro.androidvoiceassistant.tools

import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentTransaction

class Tools {

    companion object {
        val NEWS_API_BASE_URL: String = "https://newsapi.org/"
    }

    enum class LanguageCode(val value: String){
        ENGLISH_AMERICA("en-US"),
        SPANISH("es-ES"),
        JAPANESE("a-JP")
    }

    enum class CommandTy{
        OPEN_APP,
        SEARCH_INTERNET,
        NEWS_FEED,
        NOTES,
        INVALID
    }
}