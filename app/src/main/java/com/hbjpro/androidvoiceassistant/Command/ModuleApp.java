package com.hbjpro.androidvoiceassistant.Command;

import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;

import com.hbjpro.androidvoiceassistant.Tools.GlobalState;

import java.util.Iterator;
import java.util.List;

public class ModuleApp {
    private PackageManager _packageManager;
    private List<ApplicationInfo> _listApp;

    public ModuleApp(){
        _packageManager = GlobalState.getInstance().getContext().getPackageManager();
        _listApp = _packageManager.getInstalledApplications(PackageManager.GET_META_DATA);
    }

    /* --- Public Methods --- */
    public Intent getAppLaunchIntent(String appName){
        Intent launchIntent = null;

        String packageName = getPackageName(appName);

        if(!packageName.isEmpty()){
            launchIntent = _packageManager.getLaunchIntentForPackage(packageName);
        }

        return  launchIntent;
    }

    /* --- Private Methods --- */
    private String getPackageName(String appName){
        Iterator it = _listApp.iterator();

        String packageName = "";

        while (it.hasNext()){
            ApplicationInfo app = (ApplicationInfo)it.next();
            String appLabel = _packageManager.getApplicationLabel(app).toString();

            if(appLabel.toLowerCase().equals(appName.toLowerCase())){
                packageName = app.packageName;
            }
        }

        return packageName;
    }
}
