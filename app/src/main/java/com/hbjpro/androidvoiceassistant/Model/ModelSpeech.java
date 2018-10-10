package com.hbjpro.androidvoiceassistant.Model;

import android.content.Intent;

import com.hbjpro.androidvoiceassistant.Command.CommandManager;
import com.hbjpro.androidvoiceassistant.Interface.OnOrderResult;
import com.hbjpro.androidvoiceassistant.Interface.OnSpeechManager;
import com.hbjpro.androidvoiceassistant.MainActivity;
import com.hbjpro.androidvoiceassistant.Speech.SpeechManager;
import com.hbjpro.androidvoiceassistant.Speech.SpeechResult;
import com.hbjpro.androidvoiceassistant.Tools.Tools;

public class ModelSpeech implements OnSpeechManager, OnOrderResult{
    private MainActivity main;

    public ModelSpeech(MainActivity main){
        this.main = main;
    }

    /* --- Speech Manager --- */
    public void startSpeech(Tools.LanguageCode languageCode){
        SpeechManager speechManager = new SpeechManager(languageCode, this);
        speechManager.speech(main);
    }

    /* --- Order Manager --- */

    /* --- OnSpeechManager --- */
    @Override
    public void onSpeechManagerFinish(SpeechResult speechResult) {
        CommandManager commandManager = new CommandManager(this);
        commandManager.executeCommand(speechResult);
    }

    /* --- OnOrderResult --- */
    @Override
    public void onGetLaunchIntentSuccess(Intent launchIntent) {
        main.onOpenAppSuccess(launchIntent);
    }

    @Override
    public void onGetLaunchIntentError() {

    }
}
