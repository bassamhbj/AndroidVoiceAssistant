package com.hbjpro.androidvoiceassistant

import android.Manifest
import android.content.Intent
import android.content.SharedPreferences
import android.support.v4.app.ActivityCompat
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.preference.PreferenceManager
import android.support.design.widget.Snackbar
import android.view.Menu
import android.view.MenuItem
import com.hbjpro.androidvoiceassistant.common.inTransaction
import com.hbjpro.androidvoiceassistant.common.tools.Tools
import com.hbjpro.androidvoiceassistant.presenter.MainViewPresent
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), MainViewPresent.MainViewListener  {

    private val _presenter = MainViewPresent(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initElem()

        var languageCode = getLanguageCodeFromSettings()
        setSnackBar("Current language: ${languageCode.value}")

        button1.setOnClickListener { _presenter.doSpeechRecognition(languageCode) }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?) = when (item?.itemId){
        R.id.action_settings ->{
            var intent = Intent(this, SettingsActivity::class.java)
            startActivity(intent)
            true
        }
        else -> {
            super.onOptionsItemSelected(item)
        }
    }

    /* --- Private Methods --- */
    private fun initElem(){
        requestPermission()
    }

    private fun requestPermission(){
        if(ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.RECORD_AUDIO)
            || ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.INTERNET)){
            setSnackBar("Please allow permission")
        }

        ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.RECORD_AUDIO), 1)
        ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.INTERNET), 1)
    }

    private fun getLanguageCodeFromSettings(): Tools.LanguageCode{
        var sharedPreferences: SharedPreferences = PreferenceManager.getDefaultSharedPreferences(this)
        when(sharedPreferences.getString("list_languages", "-1")){
            Tools.LanguageCode.ENGLISH_AMERICA.value -> {
                return Tools.LanguageCode.ENGLISH_AMERICA
            }
            Tools.LanguageCode.SPANISH.value -> {
                return  Tools.LanguageCode.SPANISH
            }
            else -> {
                return  Tools.LanguageCode.ENGLISH_AMERICA
            }
        }
    }

    private fun setSnackBar(text: String){
        Snackbar.make(main_layout, text, Snackbar.LENGTH_SHORT).show()
    }

    /* --- Override Methods --- */
    override fun onSpeechResultSuccess(speechText: String) {
        runOnUiThread {
            setSnackBar(speechText)
        }
    }

    override fun onFragmentInit(fragment: Any) {
        supportFragmentManager.inTransaction {
            add(R.id.containerFragment, fragment as android.support.v4.app.Fragment)
        }
    }

    override fun onError(errorMsg: String) {
        runOnUiThread {
            setSnackBar(errorMsg)
        }
    }
}
