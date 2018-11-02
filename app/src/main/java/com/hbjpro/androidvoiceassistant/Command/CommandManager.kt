package com.hbjpro.androidvoiceassistant.Command

import com.hbjpro.androidvoiceassistant.model.ModelSpeech
import com.hbjpro.androidvoiceassistant.Speech.SpeechResult
import com.hbjpro.androidvoiceassistant.SubApplication
import com.hbjpro.androidvoiceassistant.Tools.Tools

class CommandManager() {

    /* --- Public Methods --- */
    fun executeCommand(speechResult: SpeechResult){
        doExecuteCommand(speechResult.commandTy, speechResult.commandArgument)
    }

    /* --- Private Methods --- */
    private fun doExecuteCommand(commandTy: Tools.CommandTy, commandArgument: String){
        when(commandTy){
            Tools.CommandTy.OPEN_APP -> {
                var moduleApp = ModuleApp()
                var intent = moduleApp.getAppLaunchIntent(commandArgument)
                if(intent != null){
                    SubApplication.instance.startActivity(intent)
                }
                // callback? error msg
            }
            Tools.CommandTy.SEARCH_INTERNET -> {
                var moduleInternet = ModuleInternet()
            }
            Tools.CommandTy.NEWS_FEED -> {

            }
            Tools.CommandTy.INVALID -> return
        }
    }

}