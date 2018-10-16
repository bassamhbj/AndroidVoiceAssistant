package com.hbjpro.androidvoiceassistant.Command

import com.hbjpro.androidvoiceassistant.Tools.Tools

class OrderHelper(var languageCode: Tools.LanguageCode) : CommandBuilder() {

    init {
        super.createMapOrder(languageCode)
    }

    /* --- Public Methods --- */
    fun getOrder(order: String): Tools.OrderTy{
        return super.findOrder(order)
    }

    /* --- Private Methods --- */
}