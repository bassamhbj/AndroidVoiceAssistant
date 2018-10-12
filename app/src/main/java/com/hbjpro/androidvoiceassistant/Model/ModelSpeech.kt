package com.hbjpro.androidvoiceassistant.Model

import android.content.Intent
import com.hbjpro.androidvoiceassistant.Command.CommandManager
import com.hbjpro.androidvoiceassistant.Interface.OnOrderResult
import com.hbjpro.androidvoiceassistant.Interface.OnSpeechManager
import com.hbjpro.androidvoiceassistant.MainActivity
import com.hbjpro.androidvoiceassistant.Speech.SpeechManager
import com.hbjpro.androidvoiceassistant.Speech.SpeechResult
import com.hbjpro.androidvoiceassistant.Tools.Tools

class ModelSpeech(val main: MainActivity) : OnSpeechManager, OnOrderResult {

    /* --- Speech Manager --- */
    fun startSpeech(languageCode: Tools.LanguageCode){
        var speechManager = SpeechManager(languageCode, this)
        speechManager.speech(main)
    }

    /* --- OnSpeechManager --- */
    override fun onSpeechManagerFinish(speechResult: SpeechResult?) {
        var commandManager = CommandManager(this)
        commandManager.executeCommand(speechResult)
    }

    /* --- OnOrderResult --- */
    override fun onGetLaunchIntentSuccess(launchIntent: Intent?) {
        main.onOpenAppSuccess(launchIntent)
    }

    override fun onGetLaunchIntentError() {

    }
}