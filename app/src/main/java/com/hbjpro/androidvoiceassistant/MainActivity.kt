package com.hbjpro.androidvoiceassistant

import android.Manifest
import android.content.Intent
import android.support.v4.app.ActivityCompat
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.hbjpro.androidvoiceassistant.Command.ModuleApp

import com.hbjpro.androidvoiceassistant.Control.MainControl
import com.hbjpro.androidvoiceassistant.Interface.ViewListener
import com.hbjpro.androidvoiceassistant.Tools.Tools

class MainActivity : AppCompatActivity(), ViewListener  {

    private var _mainControl = MainControl(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initElem()

        val button1:Button = findViewById(R.id.button1)

        button1.setOnClickListener(View.OnClickListener {
            _mainControl.doSpeechRecognition(Tools.LanguageCode.ENGLISH_AMERICA)
        })
    }

    /* --- Private Methods --- */
    fun initElem(){
        requestPermission()
    }

    fun requestPermission(){
        if(ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.RECORD_AUDIO)){
            Toast.makeText(applicationContext, "Please allow permission", Toast.LENGTH_SHORT).show()
        }else{
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.RECORD_AUDIO), 1)
        }
    }

    /* --- Override Methods --- */
    override fun setResultText(text: String?) {
        val _textView:TextView = findViewById(R.id.textView1)
        _textView.text = text
    }

    override fun onOpenAppSuccess(appLaunchIntent: Intent?) {
        if(appLaunchIntent != null)
            startActivity(appLaunchIntent)
    }
}
