package com.hbjpro.androidvoiceassistant.data

data class MessageData(
        var id: String,
        var content: String
) {
    constructor() : this("", "")
}