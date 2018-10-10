package com.hbjpro.androidvoiceassistant.Interface;

import com.hbjpro.androidvoiceassistant.Speech.SpeechResult;

public interface OnSpeechResult {
    void onSpeechSuccess(SpeechResult speechResult);
    void onSpeechError(String errorMsg);
    //void onProcessCommandSuccess(CommandResult commandResult);
    //void onProcessCommandError();
}
