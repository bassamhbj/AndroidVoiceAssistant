package com.hbjpro.androidvoiceassistant.commandfactory

import com.hbjpro.androidvoiceassistant.common.tools.Tools

class CommandFactoryEnglish : ICommandFactory {
    val KEY_WORD = "voice"

    override fun createMap(): HashMap<String, Tools.CommandTy>{
        var map = HashMap<String, Tools.CommandTy>()
        map["open"] = Tools.CommandTy.OPEN_APP
        map["search"] = Tools.CommandTy.SEARCH_INTERNET
        map["get news feed"] = Tools.CommandTy.NEWS_FEED
        map["get message"] = Tools.CommandTy.MESSAGE
        map["new message"] = Tools.CommandTy.NEW_MESSAGE

        return map
    }

    override fun getKeyWord(): String {
        return KEY_WORD
    }
}