package com.hbjpro.androidvoiceassistant.model

import com.hbjpro.androidvoiceassistant.Speech.ModuleSpeech
import com.hbjpro.androidvoiceassistant.Speech.SpeechResult
import com.hbjpro.androidvoiceassistant.Tools.Tools

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