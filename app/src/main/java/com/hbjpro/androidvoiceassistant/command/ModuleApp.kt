package com.hbjpro.androidvoiceassistant.Command

import android.content.Intent
import android.content.pm.ApplicationInfo
import android.content.pm.PackageManager
import com.hbjpro.androidvoiceassistant.SubApplication

class ModuleApp {
    private var _packageManager: PackageManager
    private var _listApp: List<ApplicationInfo>

    init {
        _packageManager = SubApplication.instance.packageManager
        _listApp = _packageManager.getInstalledApplications(PackageManager.GET_META_DATA)
    }

    /* --- Public Methods --- */
    fun getAppLaunchIntent(appName: String): Intent?{
        var packageName = getPackageName(appName)

        return if(!packageName.isEmpty()) _packageManager.getLaunchIntentForPackage(packageName) else null
    }

    /* --- Private Methods --- */
    private fun getPackageName(appName: String): String{
        var app:ApplicationInfo? = _listApp.find { _packageManager.getApplicationLabel(it).toString().toLowerCase().equals(appName) }

        return if(app != null) app.packageName else ""
    }
}