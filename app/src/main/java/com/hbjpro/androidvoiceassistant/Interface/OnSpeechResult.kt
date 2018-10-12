package com.hbjpro.androidvoiceassistant.Interface

import com.hbjpro.androidvoiceassistant.Speech.SpeechResult

interface OnSpeechResult {
    fun onSpeechSuccess(speechResult: SpeechResult)
    fun onSpeechError(errorMsg: String)
    //void onProcessCommandSuccess(CommandResult commandResult);
    //void onProcessCommandError();
}
