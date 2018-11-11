package com.hbjpro.androidvoiceassistant.commandfactory

import com.hbjpro.androidvoiceassistant.common.tools.Tools

class CommandFactory {
    companion object {
        fun createCommandMap(languageCode: Tools.LanguageCode): ICommandFactory
            = when(languageCode){
                Tools.LanguageCode.ENGLISH_AMERICA -> CommandFactoryEnglish()
                Tools.LanguageCode.SPANISH -> CommandFactorySpanish()
                //Tools.LanguageCode.JAPANESE ->
                else -> CommandFactoryEnglish()
            }
    }
}

interface ICommandFactory{
    fun createMap(): HashMap<String, Tools.CommandTy>
    fun getKeyWord(): String
}