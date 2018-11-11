package com.hbjpro.androidvoiceassistant.common.data

data class MessageData(
        var id: String,
        var content: String
) {
    constructor() : this("", "")
}