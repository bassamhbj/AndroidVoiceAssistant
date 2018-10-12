package com.hbjpro.androidvoiceassistant.Interface

import com.hbjpro.androidvoiceassistant.Speech.SpeechResult

interface OnSpeechManager {
    fun onSpeechManagerFinish(speechResult: SpeechResult)
}
