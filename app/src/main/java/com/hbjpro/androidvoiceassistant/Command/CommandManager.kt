package com.hbjpro.androidvoiceassistant.Command

import com.hbjpro.androidvoiceassistant.Model.ModelSpeech
import com.hbjpro.androidvoiceassistant.Speech.SpeechResult
import com.hbjpro.androidvoiceassistant.Tools.Tools

class CommandManager(val modelSpeech: ModelSpeech) {

    /* --- Public Methods --- */
    fun executeCommand(speechResult: SpeechResult){
        var orderTy = getOrderTy(speechResult.command, speechResult.languageCode)
        doExecuteCommand(orderTy, speechResult.commandArgument)
    }

    /* --- Private Methods --- */
    private fun doExecuteCommand(orderTy: Tools.OrderTy, commandArgument: String){
        when(orderTy){
            Tools.OrderTy.OPEN_APP -> {
                var moduleApp = ModuleApp()
                var intent = moduleApp.getAppLaunchIntent(commandArgument)
                if(intent != null){
                    modelSpeech.onGetLaunchIntentSuccess(intent)
                }else{
                    modelSpeech.onGetLaunchIntentError()
                }
            }
            Tools.OrderTy.SEARCH_INTERNET -> {
                var moduleInternet = ModuleInternet()
            }
            Tools.OrderTy.INVALID -> return
        }
    }

    private fun getOrderTy(command: String, languageCode: Tools.LanguageCode): Tools.OrderTy{
        var helper = OrderHelper(languageCode)
        return helper.getOrder(command)
    }
}