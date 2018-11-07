package com.hbjpro.androidvoiceassistant.common.app

import android.content.pm.ApplicationInfo
import android.content.pm.PackageManager
import com.hbjpro.androidvoiceassistant.SubApplication

object PackageManagerClient {
    private var packageManager: PackageManager

    init {
        packageManager = SubApplication.instance.packageManager
    }

    fun getPackageManager(): PackageManager = packageManager

    fun getInstalledApps(): List<ApplicationInfo>
            = packageManager.getInstalledApplications(PackageManager.GET_META_DATA)
}