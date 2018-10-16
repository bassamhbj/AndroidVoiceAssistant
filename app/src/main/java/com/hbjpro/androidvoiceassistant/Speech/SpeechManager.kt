package com.hbjpro.androidvoiceassistant.Speech

import android.content.Context
import com.hbjpro.androidvoiceassistant.Interface.OnSpeechResult
import com.hbjpro.androidvoiceassistant.Tools.Tools
import com.hbjpro.androidvoiceassistant.Model.ModelSpeech

class SpeechManager(val languageCode: Tools.LanguageCode, val modelSpeech: ModelSpeech) : OnSpeechResult {

    /* --- Public Methods --- */
    fun speech(context: Context){
        getSpeech(context)
    }

    /* --- Private Methods --- */
    private fun getSpeech(context: Context){
        ModuleSpeech(context, this).apply {
            setSpeechRecognizer(languageCode)
            startSpeechRecognizer()
        }
    }

    /* --- Override Methods --- */
    override fun onSpeechSuccess(speechResult: SpeechResult) {
        modelSpeech.onSpeechManagerFinish(speechResult)
    }

    override fun onSpeechError(errorMsg: String) {

    }
}