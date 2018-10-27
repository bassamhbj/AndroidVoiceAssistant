package com.hbjpro.androidvoiceassistant.Command

import com.hbjpro.androidvoiceassistant.Tools.Tools

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

    /* --- Protected Methods --- */
    fun findOrder(order:String):Tools.CommandTy{
        return _mapOrder.getOrElse(order, {Tools.CommandTy.INVALID} )
    }

    /* --- Private Methods --- */
    private fun createMapEnglish():HashMap<String, Tools.CommandTy>{
        var map = HashMap<String, Tools.CommandTy>()
        map.put("open", Tools.CommandTy.OPEN_APP)
        map.put("search", Tools.CommandTy.SEARCH_INTERNET)

        return map
    }

    private fun createMapSpanish():HashMap<String, Tools.CommandTy>{
        var map = HashMap<String, Tools.CommandTy>()
        map.put("abrir", Tools.CommandTy.OPEN_APP)
        map.put("busca", Tools.CommandTy.SEARCH_INTERNET)

        return map
    }

    private fun createMapJapanese():HashMap<String, Tools.CommandTy>{
        var map = HashMap<String, Tools.CommandTy>()

        return map
    }
}