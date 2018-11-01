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
import android.view.View
import android.widget.Button
import android.widget.ListView
import android.widget.TextView
import android.widget.Toast
import com.google.gson.GsonBuilder
import com.hbjpro.androidvoiceassistant.Interface.INewsApi
import com.hbjpro.androidvoiceassistant.Tools.NewsDataAdapter
import com.hbjpro.androidvoiceassistant.Tools.Tools
import com.hbjpro.androidvoiceassistant.presenter.MainViewPresent
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.internal.schedulers.IoScheduler
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Callback
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity(), MainViewPresent.MainViewListener  {

    private val _presenter = MainViewPresent(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initElem()

        val button1:Button = findViewById(R.id.button1)

        var languageCode = getLanguageCodeFromSettings()
        setText(languageCode.value)

        //button1.setOnClickListener { _presenter.doSpeechRecognition(languageCode) }

        //testAdapter()

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
        if(ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.RECORD_AUDIO)){
            Toast.makeText(applicationContext, "Please allow permission", Toast.LENGTH_SHORT).show()
        }//else{
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.RECORD_AUDIO), 1)
        //}
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
        setText(speechText)
    }

    override fun onSpeechResultError(errorMsg: String) {
        setText(errorMsg)
    }

    private fun setText(text: String){
        val textView:TextView = findViewById(R.id.textView1)
        textView.text = text
    }

    private fun testJSON(){
        recyclerViewNews.layoutManager = LinearLayoutManager(this)

        val retrofit = Retrofit.Builder().addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl("https://jsonplaceholder.typicode.com/").build()

        //.baseUrl("https://newsapi.org/").build()

        val newsApi = retrofit.create(INewsApi::class.java)

        //var response = newsApi.getTopHeadlines("jp", "b13d74d28e0a4c30b9945524dfec7faf")
        var response = newsApi.getAllPosts()


        response.observeOn(AndroidSchedulers.mainThread()).subscribeOn(IoScheduler()).subscribe {
            var a = it
           // recyclerViewNews.adapter = PostItemAdapter(it, this)
        }

    }
}
