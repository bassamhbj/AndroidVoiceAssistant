package com.hbjpro.androidvoiceassistant.speech

import android.content.Intent
import android.os.Bundle
import android.speech.RecognitionListener
import android.speech.RecognizerIntent
import android.speech.SpeechRecognizer
import android.util.Log
import com.hbjpro.androidvoiceassistant.SubApplication
import com.hbjpro.androidvoiceassistant.tools.Tools

class ModuleSpeech {

    companion object {
        val TAG = ModuleSpeech::class.simpleName
    }

    private lateinit var _languageCode: Tools.LanguageCode
    private var _speechRecognizer:SpeechRecognizer = SpeechRecognizer.createSpeechRecognizer(SubApplication.context)

    /* --- Public Methods --- */
    fun setSpeechRecognizer(languageCode: Tools.LanguageCode){
        _languageCode = languageCode

        val intent = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH)
        with(intent){
            putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM)
            putExtra(RecognizerIntent.EXTRA_LANGUAGE, _languageCode.value)
        }

        _speechRecognizer.startListening(intent)
    }

    fun startSpeechRecognizer(speechCallback: SpeechCallback){
        _speechRecognizer.setRecognitionListener(object : RecognitionListener{
            override fun onReadyForSpeech(params: Bundle?) {
                Log.d(TAG, "Ready for Speech")
            }

            override fun onRmsChanged(rmsdB: Float) {

            }

            override fun onBufferReceived(buffer: ByteArray?) {

            }

            override fun onPartialResults(partialResults: Bundle?) {

            }

            override fun onEvent(eventType: Int, params: Bundle?) {

            }

            override fun onBeginningOfSpeech() {
                Log.d(TAG, "Beginning of Speech")
            }

            override fun onEndOfSpeech() {
                Log.d(TAG, "End of Speech")
            }

            override fun onError(error: Int) {
                Log.d(TAG, "Error")
                var err = getErrorMsg(error)
                Log.d(TAG, "Error: " + err)
                speechCallback.onSpeechError(err)
            }

            override fun onResults(results: Bundle?) {
                //Log.d(TAG, "Getting Results")
                val speechResult = results!!.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION)[0]
                //Log.d(TAG, "Results: " + speechResult)

                speechCallback.onSpeechSuccess(
                        with(ProcessSpeech()){
                            processText(speechResult, _languageCode)
                        }
                )
            }

        })
    }

    /* --- Private Methods --- */
    private fun getErrorMsg(error:Int) =
            when(error){
                SpeechRecognizer.ERROR_AUDIO -> "Audio recording error."
                SpeechRecognizer.ERROR_CLIENT -> "Other client side errors."
                SpeechRecognizer.ERROR_INSUFFICIENT_PERMISSIONS -> "Insufficient permissions."
                SpeechRecognizer.ERROR_NETWORK -> "Other network related errors."
                SpeechRecognizer.ERROR_NETWORK_TIMEOUT -> "Network operation timed out."
                SpeechRecognizer.ERROR_NO_MATCH -> "No recognition result matched."
                SpeechRecognizer.ERROR_RECOGNIZER_BUSY -> "RecognitionService busy."
                SpeechRecognizer.ERROR_SERVER -> "Server sends error status."
                SpeechRecognizer.ERROR_SPEECH_TIMEOUT -> "No speech input."
                else -> ""
            }

    interface SpeechCallback{
        fun onSpeechSuccess(speechResult: SpeechResult)
        fun onSpeechError(errorMsg: String)
    }
}