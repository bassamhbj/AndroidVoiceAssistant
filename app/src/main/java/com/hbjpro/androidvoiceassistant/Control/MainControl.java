package com.hbjpro.androidvoiceassistant.Control;

import com.hbjpro.androidvoiceassistant.MainActivity;
import com.hbjpro.androidvoiceassistant.Model.ModelApp;
import com.hbjpro.androidvoiceassistant.Model.ModelInternet;
import com.hbjpro.androidvoiceassistant.Model.ModelSpeech;
import com.hbjpro.androidvoiceassistant.Tools.Tools;

/**
 * Created by bassamhbj on 26/11/2017.
 */

public class MainControl {
    private MainActivity mainContext;
    private ModelSpeech modelSpeech;
    private ModelApp modelApp;
    private ModelInternet modelInternet;

    public MainControl(MainActivity main){
        this.mainContext = main;
        this.modelSpeech = new ModelSpeech(main);
        //this.modelApp = new ModelApp(main);
        //this.modelInternet = new ModelInternet(main);
    }

    /* --- MODEL SPEECH --- */

    public void doSpeechRecognition(Tools.LanguageCode languageCode){
        modelSpeech.startSpeech(languageCode);
    }

    /* --- MODEL APP --- */

    /* --- MODEL INTERNET --- */
}
