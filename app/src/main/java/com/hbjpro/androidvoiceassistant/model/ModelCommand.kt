package com.hbjpro.androidvoiceassistant.model

import com.hbjpro.androidvoiceassistant.Command.CommandManager
import com.hbjpro.androidvoiceassistant.Speech.SpeechResult

class ModelCommand {

    fun executeCommand(callback: ModelCommandCallback, speechResult: SpeechResult){

    }

    fun executeCommand(speechResult: SpeechResult){
        var commandManager = CommandManager()
        commandManager.executeCommand(speechResult)
    }

    interface ModelCommandCallback{
        fun onSucess()
        fun onError()
    }
}