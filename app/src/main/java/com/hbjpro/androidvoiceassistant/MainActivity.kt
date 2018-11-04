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
import com.google.gson.GsonBuilder
import com.hbjpro.androidvoiceassistant.Interface.INewsApi
import com.hbjpro.androidvoiceassistant.Tools.Tools
import com.hbjpro.androidvoiceassistant.presenter.MainViewPresent
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.internal.schedulers.IoScheduler
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity(), MainViewPresent.MainViewListener  {

    private val _presenter = MainViewPresent(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initElem()

        var languageCode = getLanguageCodeFromSettings()
        setText(languageCode.value)

        button1.setOnClickListener { _presenter.doSpeechRecognition(languageCode) }

        testJSON()

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
        var languageCode = Tools.LanguageCode.ENGLISH_AMERICA
        when(sharedPreferences.getString("list_languages", "-1")){
            Tools.LanguageCode.ENGLISH_AMERICA.value -> {
                languageCode = Tools.LanguageCode.ENGLISH_AMERICA
            }
            Tools.LanguageCode.SPANISH.value -> {
                languageCode = Tools.LanguageCode.SPANISH
            }
            else -> {
                languageCode = Tools.LanguageCode.ENGLISH_AMERICA
            }
        }
        return languageCode
    }

    /* --- Override Methods --- */
    override fun onSpeechResultSuccess(speechText: String) {
        runOnUiThread {
            setText(speechText)
        }
    }

    override fun onSpeechResultError(errorMsg: String) {
        runOnUiThread {
            setText(errorMsg)
        }
    }

    private fun setText(text: String){
        textView1.text = text
    }

    private fun testJSON(){
        recyclerViewNews.layoutManager = LinearLayoutManager(this)

        val retrofit = Retrofit.Builder().addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(Tools.NEWS_API_BASE_URL).build()

        val newsApi = retrofit.create(INewsApi::class.java)

        var response = newsApi.getTopHeadlines("jp", "")

        response.observeOn(AndroidSchedulers.mainThread()).subscribeOn(IoScheduler()).subscribe{
            recyclerViewNews.adapter = NewsDataAdapter(it.articles, this)
        }

    }
}
