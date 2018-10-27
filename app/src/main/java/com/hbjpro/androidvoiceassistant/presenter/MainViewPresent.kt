package com.hbjpro.androidvoiceassistant.presenter

import com.hbjpro.androidvoiceassistant.model.ModelSpeech
import com.hbjpro.androidvoiceassistant.Speech.SpeechResult
import com.hbjpro.androidvoiceassistant.Tools.Tools
import com.hbjpro.androidvoiceassistant.model.ModelCommand

class MainViewPresent(val view: MainViewListener){

    var _modelSpeech = ModelSpeech()

    fun doSpeechRecognition(languageCode: Tools.LanguageCode){
        _modelSpeech.startSpeech(object: ModelSpeech.ModelSpeechCallback{
            override fun onSuccess(speechResult: SpeechResult) {
                view.onSpeechResultSuccess(speechResult.message)
                ModelCommand().apply {
                    executeCommand(speechResult)
                }
            }

            override fun onError(errorMsg: String) {
                view.onSpeechResultError(errorMsg)
            }
        }, languageCode)
    }

    interface MainViewListener{
        fun onSpeechResultSuccess(speechText: String)
        fun onSpeechResultError(errorMsg: String)
    }
}