package com.hbjpro.androidvoiceassistant.Command

import com.hbjpro.androidvoiceassistant.Tools.Tools

class OrderHelper(var languageCode: Tools.LanguageCode) : CommandBuilder() {

    init {
        super.createMapOrder(languageCode)
    }

    /* --- Public Methods --- */
    fun getCommandTy(order: String): Tools.CommandTy{
        return super.findOrder(order)
    }

    /* --- Private Methods --- */
}