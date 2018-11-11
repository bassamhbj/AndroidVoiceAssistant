package com.hbjpro.androidvoiceassistant.command

import android.content.Intent
import android.content.pm.ApplicationInfo
import android.content.pm.PackageManager
import com.hbjpro.androidvoiceassistant.common.app.PackageManagerClient

class ModuleApp {

    /* --- Public Methods --- */
    fun getAppLaunchIntent(appName: String, callback: AppCallback<Intent>){
        var packageName = getPackageName(appName)

        if(!packageName.isEmpty()){
            callback.onSuccess(PackageManagerClient.getPackageManager().getLaunchIntentForPackage(packageName))
        }else{
            callback.onError("App could not be found")
        }
    }

    /* --- Private Methods --- */
    private fun getPackageName(appName: String): String{
        var app:ApplicationInfo? = PackageManagerClient.getPackageManager()
                .getInstalledApplications(PackageManager.GET_META_DATA)
                .find { PackageManagerClient.getPackageManager().getApplicationLabel(it).toString().toLowerCase() == appName }

        return if(app != null) app.packageName else ""
    }

    interface AppCallback<T>{
        fun onSuccess(result: T)
        fun onError(errorMsg: String)
    }
}