package com.hbjpro.androidvoiceassistant.Tools

class Tools {
    enum class LanguageCode(val value: String){
        ENGLISH_AMERICA("en-US"),
        SPANISH("es-ES"),
        JAPANESE("a-JP")
    }

    enum class CommandTy{
        OPEN_APP,
        SEARCH_INTERNET,
        INVALID
    }
}