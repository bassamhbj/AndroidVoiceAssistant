package com.hbjpro.androidvoiceassistant.command

import com.hbjpro.androidvoiceassistant.common.tools.Tools

// More than one word for one order, next version

open class CommandBuilder {
    var _mapOrder:HashMap<String, Tools.CommandTy>

    init {
        _mapOrder = HashMap()
    }

    /* --- Public Methods --- */
    fun createMapOrder(languageCode: Tools.LanguageCode){
        when(languageCode){
            Tools.LanguageCode.ENGLISH_AMERICA -> _mapOrder = createMapEnglish()
            Tools.LanguageCode.SPANISH -> _mapOrder = createMapSpanish()
            Tools.LanguageCode.JAPANESE -> _mapOrder = createMapJapanese()
        }
    }

    fun getKeyWord(code: Tools.LanguageCode) = when(code){
        Tools.LanguageCode.ENGLISH_AMERICA -> "voice"
        Tools.LanguageCode.SPANISH -> "voz"
        Tools.LanguageCode.JAPANESE -> "声"
    }

    /* --- Protected Methods --- */
    fun findOrder(order:String):Tools.CommandTy{
        return _mapOrder.getOrElse(order, {Tools.CommandTy.INVALID} )
    }

    /* --- Private Methods --- */
    private fun createMapEnglish():HashMap<String, Tools.CommandTy>{
        var map = HashMap<String, Tools.CommandTy>()
        map["open"] = Tools.CommandTy.OPEN_APP
        map["search"] = Tools.CommandTy.SEARCH_INTERNET
        map["get news feed"] = Tools.CommandTy.NEWS_FEED
        map["get message"] = Tools.CommandTy.MESSAGE
        map["new message"] = Tools.CommandTy.NEW_MESSAGE

        return map
    }

    private fun createMapSpanish():HashMap<String, Tools.CommandTy>{
        var map = HashMap<String, Tools.CommandTy>()
        map["abrir"] = Tools.CommandTy.OPEN_APP
        map["busca"] = Tools.CommandTy.SEARCH_INTERNET
        map["noticias"] = Tools.CommandTy.NEWS_FEED
        map["mensajes"] = Tools.CommandTy.MESSAGE
        map["nuevo mensaje"] = Tools.CommandTy.NEW_MESSAGE

        return map
    }

    private fun createMapJapanese():HashMap<String, Tools.CommandTy>{
        var map = HashMap<String, Tools.CommandTy>()

        return map
    }
}