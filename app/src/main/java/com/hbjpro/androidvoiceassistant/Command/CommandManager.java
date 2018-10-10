package com.hbjpro.androidvoiceassistant.Command;

import android.content.Intent;

import com.hbjpro.androidvoiceassistant.Model.ModelSpeech;
import com.hbjpro.androidvoiceassistant.Speech.SpeechResult;
import com.hbjpro.androidvoiceassistant.Tools.Tools;

public class CommandManager {
    private ModelSpeech _modelSpeech;

    public CommandManager(ModelSpeech modelSpeech){
        this._modelSpeech = modelSpeech;
    }

    /* --- Public Methods --- */
    public void executeCommand(SpeechResult speechResult){
        Tools.OrderTy orderTy = getOrderTy(speechResult.getCommand(), speechResult.getLanguageCode());
        doExecuteCommand(orderTy, speechResult.getCommandArgument());
    }

    /* --- Private Methods --- */
    private void doExecuteCommand(Tools.OrderTy orderTy, String commandArgument){
        switch (orderTy){
            case OPEN_APP:
                ModuleApp moduleApp = new ModuleApp();
                Intent intent = moduleApp.getAppLaunchIntent(commandArgument);
                if(intent != null){
                    _modelSpeech.onGetLaunchIntentSuccess(intent);
                }else{
                    _modelSpeech.onGetLaunchIntentError();
                }
                break;
            case SEARCH_INTERNET:
                ModuleInternet moduleInternet = new ModuleInternet();


                break;
            case INVALID: break;
            default: break;
        }
    }
    private Tools.OrderTy getOrderTy(String command, Tools.LanguageCode languageCode){
        OrderHelper helper = new OrderHelper(languageCode);
        return helper.getOrder(command);
    }
}
