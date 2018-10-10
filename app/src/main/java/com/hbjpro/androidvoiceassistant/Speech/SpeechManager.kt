package com.hbjpro.androidvoiceassistant.Speech

import android.content.Context
import com.hbjpro.androidvoiceassistant.Interface.OnSpeechResult
import com.hbjpro.androidvoiceassistant.Model.ModelSpeech
import com.hbjpro.androidvoiceassistant.Tools.Tools

/**
 * Created by bassamhbj on 13/08/2018
 */

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

    override fun onSpeechError(errorMsg: String?) {

    }
}