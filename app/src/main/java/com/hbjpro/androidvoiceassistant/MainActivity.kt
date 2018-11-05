package com.hbjpro.androidvoiceassistant

import android.Manifest
import android.content.Intent
import android.content.SharedPreferences
import android.support.v4.app.ActivityCompat
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.preference.PreferenceManager
import android.support.v7.widget.LinearLayoutManager
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import com.hbjpro.androidvoiceassistant.Data.NewsData
import com.hbjpro.androidvoiceassistant.Tools.Tools
import com.hbjpro.androidvoiceassistant.presenter.MainViewPresent
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), MainViewPresent.MainViewListener  {

    private val _presenter = MainViewPresent(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initElem()

        var languageCode = getLanguageCodeFromSettings()
        setText(languageCode.value)

        button1.setOnClickListener { _presenter.doSpeechRecognition(languageCode) }

        //PreferenceManager.setDefaultValues(this, R.xml.preferences, false)
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
            Toast.makeText(applicationContext, "Please allow permission", Toast.LENGTH_SHORT).show()
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

    /* --- Override Methods --- */
    override fun onSpeechResultSuccess(speechText: String) {
        runOnUiThread {
            setText(speechText)
        }
    }

    override fun onOpenAppSuccess(intent: Intent) {
        runOnUiThread {
            startActivity(intent)
        }
    }

    override fun onGetNewsFeedSuccess(newsData: NewsData) {
        runOnUiThread {
            recyclerViewNews.layoutManager = LinearLayoutManager(this)
            recyclerViewNews.adapter = NewsDataAdapter(newsData.articles, this)
        }
    }

    override fun onError(errorMsg: String) {
        runOnUiThread {
            setText(errorMsg)
        }
    }

    private fun setText(text: String){
        textView1.text = text
    }
}
