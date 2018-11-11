package com.hbjpro.androidvoiceassistant.commandfactory

import com.hbjpro.androidvoiceassistant.common.tools.Tools

class CommandFactorySpanish : ICommandFactory {
    val KEY_WORD = "voz"

    override fun createMap(): HashMap<String, Tools.CommandTy>{
        var map = HashMap<String, Tools.CommandTy>()
        map["abrir"] = Tools.CommandTy.OPEN_APP
        map["busca"] = Tools.CommandTy.SEARCH_INTERNET
        map["noticias"] = Tools.CommandTy.NEWS_FEED
        map["mensajes"] = Tools.CommandTy.MESSAGE
        map["nuevo mensaje"] = Tools.CommandTy.NEW_MESSAGE

        return map
    }

    override fun getKeyWord(): String {
        return KEY_WORD
    }
}