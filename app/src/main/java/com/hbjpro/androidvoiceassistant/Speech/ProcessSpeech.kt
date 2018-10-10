package com.hbjpro.androidvoiceassistant.Speech

import android.util.Log
import com.hbjpro.androidvoiceassistant.Tools.Tools

class ProcessSpeech {

    companion object {
        val TAG = ProcessSpeech::class.simpleName
    }

    /* --- Public Methods --- */
    fun processText(text:String, languageCode: Tools.LanguageCode) : SpeechResult =
        SpeechResult("", false, "", "", languageCode).apply{
            val commandWords = getWords(text)
            isKeyWord = isKeyWordCheck(commandWords[0], languageCode)
            if(isKeyWord){
                command = commandWords[1]
                commandArgument = getCommandArgument(commandWords)
            }
        }

    /* --- Private Methods --- */
    private fun getWords(text:String): List<String> = text.split(" ")

    private fun isKeyWordCheck(firstWord:String, languageCode: Tools.LanguageCode): Boolean
        = firstWord.toLowerCase() == Tools().getKeyWord(languageCode)

    private fun getCommandArgument(commandWords:List<String>) : String{
        var commandArgument = ""
        commandArgument += commandWords.filterIndexed { index, _ -> index > 1 }
        Log.d(TAG, commandArgument)
        return commandArgument
    }
}