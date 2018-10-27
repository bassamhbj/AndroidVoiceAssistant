package com.hbjpro.androidvoiceassistant.model

import com.hbjpro.androidvoiceassistant.Speech.SpeechResult

class ModelCommand {

    fun executeCommand(callback: ModelCommandCallback, speechResult: SpeechResult){

    }

    interface ModelCommandCallback{
        fun onSucess()
        fun onError()
    }
}