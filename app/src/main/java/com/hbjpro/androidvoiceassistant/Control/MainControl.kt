package com.hbjpro.androidvoiceassistant.Control

import com.hbjpro.androidvoiceassistant.MainActivity
import com.hbjpro.androidvoiceassistant.Model.ModelSpeech
import com.hbjpro.androidvoiceassistant.Tools.Tools

class MainControl(mainActivity: MainActivity) {
    private var _modelSpeech: ModelSpeech

    init {
        _modelSpeech = ModelSpeech(mainActivity)
    }

    /* --- MODEL SPEECH --- */
    fun doSpeechRecognition(languageCode: Tools.LanguageCode){
        _modelSpeech.startSpeech(languageCode)
    }
}