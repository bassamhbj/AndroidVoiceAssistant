package com.hbjpro.androidvoiceassistant.speech

import com.hbjpro.androidvoiceassistant.command.CommandBuilder
import com.hbjpro.androidvoiceassistant.common.tools.Tools

class ProcessSpeech {

    companion object {
        val TAG = ProcessSpeech::class.simpleName
    }

    /* --- Public Methods --- */
    fun processText(text:String, languageCode: Tools.LanguageCode): SpeechResult{
        var speechResult = SpeechResult(text, false, "", Tools.CommandTy.INVALID, "", languageCode)

        var commandBuilder = CommandBuilder().apply {
            createMapOrder(languageCode)
        }

        var regexStr = getRegexString(commandBuilder.getKeyWord(languageCode), commandBuilder._mapOrder)
        var regex = Regex(regexStr, RegexOption.IGNORE_CASE)

        if(regex.containsMatchIn(text)){
            speechResult.isKeyWord = true
            var result: kotlin.text.MatchResult? = regex.find(text)

            if(result != null){
                speechResult.command = result?.groups[1]!!.value.toLowerCase()
                speechResult.commandTy = commandBuilder.findOrder(speechResult.command)
                speechResult.commandArgument = text.substring((result?.value)!!.length).trim().toLowerCase()
            }
        }

        return speechResult
    }

    /* --- Private Methods --- */
    private fun getRegexString(keyWord: String, commandMap: HashMap<String, Tools.CommandTy>): String{
        var commandWord = ""
        commandMap.forEach { it -> commandWord += it.key + "|"  }

        var regexStr = "^" + keyWord + """\s(""" + commandWord.substring(0, commandWord.length - 1) + ")"

        return regexStr
    }
}