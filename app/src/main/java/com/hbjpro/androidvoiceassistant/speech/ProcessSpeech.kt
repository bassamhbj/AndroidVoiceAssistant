package com.hbjpro.androidvoiceassistant.speech

import com.hbjpro.androidvoiceassistant.commandfactory.CommandFactory
import com.hbjpro.androidvoiceassistant.common.tools.Tools

class ProcessSpeech {

    companion object {
        val TAG = ProcessSpeech::class.simpleName
    }

    /* --- Public Methods --- */
    fun processText(text:String, languageCode: Tools.LanguageCode): SpeechResult{
        var speechResult = SpeechResult(text, false, "", Tools.CommandTy.INVALID, "", languageCode)

        var commandFactory = CommandFactory.createCommandMap(languageCode)
        var mapOrder = commandFactory.createMap()

        var regexStr = getRegexString(commandFactory.getKeyWord(), mapOrder)
        var regex = Regex(regexStr, RegexOption.IGNORE_CASE)

        if(regex.containsMatchIn(text)){
            speechResult.isKeyWord = true
            var result: kotlin.text.MatchResult? = regex.find(text)

            if(result != null){
                speechResult.command = result?.groups[1]!!.value.toLowerCase()
                speechResult.commandTy = findOrder(speechResult.command, mapOrder)
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

    private fun findOrder(order:String, mapOrder: HashMap<String, Tools.CommandTy>): Tools.CommandTy{
        return mapOrder.getOrElse(order) { Tools.CommandTy.INVALID}
    }
}