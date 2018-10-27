package com.hbjpro.androidvoiceassistant.Tools

class Tools {
    enum class LanguageCode{
        ENGLISH_AMERICA,
        SPANISH,
        JAPANESE
    }

    enum class CommandTy{
        OPEN_APP,
        SEARCH_INTERNET,
        INVALID
    }

    /* --- Static Methods --- */
    fun getLocationStr(code: LanguageCode) = when(code){
        LanguageCode.ENGLISH_AMERICA -> "en-US"
        LanguageCode.SPANISH -> "es-ES"
        LanguageCode.JAPANESE -> "ja-JP"
    }
}