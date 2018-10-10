package com.hbjpro.androidvoiceassistant.Speech

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.speech.RecognitionListener
import android.speech.RecognizerIntent
import android.speech.SpeechRecognizer
import android.util.Log
import com.hbjpro.androidvoiceassistant.Tools.Tools

/**
 * Created by bassamhbj
 */

class ModuleSpeech(val context: Context, val speechManager: SpeechManager) {

    companion object {
        val TAG = ModuleSpeech::class.simpleName
    }

    private lateinit var _languageCode: Tools.LanguageCode
    private var _speechRecognizer:SpeechRecognizer = SpeechRecognizer.createSpeechRecognizer(context)

    /* --- Public Methods --- */
    fun setSpeechRecognizer(languageCode: Tools.LanguageCode){
        _languageCode = languageCode
        val locationCode = Tools().getLocationStr(_languageCode)

        val intent = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH)
        with(intent){
            putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM)
            putExtra(RecognizerIntent.EXTRA_LANGUAGE, locationCode)
        }

        _speechRecognizer.startListening(intent)
    }

    fun startSpeechRecognizer(){
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
                speechManager.onSpeechError(getErrorMsg(error))
            }

            override fun onResults(results: Bundle?) {
                Log.d(TAG, "Getting Results")
                val speechResult = results!!.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION)[0]
                Log.d(TAG, "Results: " + speechResult)

                val obj = ProcessSpeech()
                obj.processText(speechResult, _languageCode)

                speechManager.onSpeechSuccess(
                        with(ProcessSpeech()){
                            processText(speechResult, _languageCode)
                        }
                )
            }

        })
    }

    /* --- Private Methods --- */
    // error string file
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
}