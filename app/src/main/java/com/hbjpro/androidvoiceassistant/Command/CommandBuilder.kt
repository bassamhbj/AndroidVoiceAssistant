package com.hbjpro.androidvoiceassistant.Command

import com.hbjpro.androidvoiceassistant.Tools.Tools

// More than one word for one order, next version

open class CommandBuilder {
    protected var _mapOrder:HashMap<String, Tools.OrderTy>

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
    protected fun findOrder(order:String):Tools.OrderTy{
        return _mapOrder.getOrElse(order, {Tools.OrderTy.INVALID} )
    }

    /* --- Private Methods --- */
    private fun createMapEnglish():HashMap<String, Tools.OrderTy>{
        var map = HashMap<String, Tools.OrderTy>()
        map.put("open", Tools.OrderTy.OPEN_APP)
        map.put("search", Tools.OrderTy.SEARCH_INTERNET)

        return map
    }

    private fun createMapSpanish():HashMap<String, Tools.OrderTy>{
        var map = HashMap<String, Tools.OrderTy>()
        map.put("abrir", Tools.OrderTy.OPEN_APP)
        map.put("busca", Tools.OrderTy.SEARCH_INTERNET)

        return map
    }

    private fun createMapJapanese():HashMap<String, Tools.OrderTy>{
        var map = HashMap<String, Tools.OrderTy>()

        return map
    }
}