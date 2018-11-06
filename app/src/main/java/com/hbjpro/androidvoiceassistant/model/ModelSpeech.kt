package com.hbjpro.androidvoiceassistant.model

import com.hbjpro.androidvoiceassistant.speech.ModuleSpeech
import com.hbjpro.androidvoiceassistant.speech.SpeechResult
import com.hbjpro.androidvoiceassistant.tools.Tools

class ModelSpeech {

    fun startSpeech(callback: ModelSpeechCallback, languageCode: Tools.LanguageCode){
        ModuleSpeech().apply {
            setSpeechRecognizer(languageCode)
            startSpeechRecognizer(object: ModuleSpeech.SpeechCallback{
                override fun onSpeechSuccess(speechResult: SpeechResult) {
                    callback.onSuccess(speechResult)
                }

                override fun onSpeechError(errorMsg: String) {
                    callback.onError(errorMsg)
                }
            })
        }
    }

    interface ModelSpeechCallback{
        fun onSuccess(speechResult: SpeechResult)
        fun onError(errorMsg: String)
    }
}