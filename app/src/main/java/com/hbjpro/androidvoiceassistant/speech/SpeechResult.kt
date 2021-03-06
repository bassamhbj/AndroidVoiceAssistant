package com.hbjpro.androidvoiceassistant.speech

import com.hbjpro.androidvoiceassistant.common.tools.Tools

data class SpeechResult(
        var message: String, // Speech input full text
        var isKeyWord: Boolean, // If Speech input is an order
        var command: String, // Order (1 word) (More than one word in future versions)
        var commandTy: Tools.CommandTy, // CommandTy from command
        var commandArgument: String, // Name of app to open, information to seach in internet
        var languageCode: Tools.LanguageCode // Language Code
)