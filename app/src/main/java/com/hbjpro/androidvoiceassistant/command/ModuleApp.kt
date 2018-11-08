package com.hbjpro.androidvoiceassistant.command

import android.content.Intent
import android.content.pm.ApplicationInfo
import android.content.pm.PackageManager
import com.hbjpro.androidvoiceassistant.common.app.PackageManagerClient

class ModuleApp {

    /* --- Public Methods --- */
    fun getAppLaunchIntent(appName: String): Intent?{
        var packageName = getPackageName(appName)

        return if(!packageName.isEmpty()) PackageManagerClient.getPackageManager().getLaunchIntentForPackage(packageName) else null
    }

    /* --- Private Methods --- */
    private fun getPackageName(appName: String): String{
        var app:ApplicationInfo? = PackageManagerClient.getPackageManager()
                .getInstalledApplications(PackageManager.GET_META_DATA)
                .find { PackageManagerClient.getPackageManager().getApplicationLabel(it).toString().toLowerCase().equals(appName) }

        return if(app != null) app.packageName else ""
    }
}