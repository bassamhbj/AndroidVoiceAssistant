package com.hbjpro.androidvoiceassistant.common.app

import android.content.pm.ApplicationInfo
import android.content.pm.PackageManager
import com.hbjpro.androidvoiceassistant.SubApplication

object PackageManagerClient {
    private var packageManager: PackageManager

    init {
        packageManager = SubApplication.context.packageManager
    }

    fun getPackageManager(): PackageManager = packageManager
}