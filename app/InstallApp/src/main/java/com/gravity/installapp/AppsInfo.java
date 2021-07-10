package com.gravity.installapp;

import android.app.Activity;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.util.Log;

import com.gravity.installapp.Model.MApps;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class AppsInfo {

    private PackageInfo packageInfo;
    private List<MApps> appsList = new ArrayList<>();
    private List<ApplicationInfo> applicationInfoList = new ArrayList<>();
    private List<PackageInfo> packageList = new ArrayList<>();
    private List<ResolveInfo> pkgAppsList = new ArrayList<>();
    private Activity activity;
//    private AppsAdapter adapter;
    private PackageManager pm;
    private NewPackageReceiver receiver;


    public AppsInfo(Activity activity) {
        this.activity = activity;
    }

    public List<MApps> getAppData(){

        pm = activity.getPackageManager();

        Intent mainIntent = new Intent(Intent.ACTION_MAIN, null);
        mainIntent.addCategory(Intent.CATEGORY_LAUNCHER);
        pkgAppsList = pm.queryIntentActivities( mainIntent, 0);


        //get a list of installed apps.
//        applicationInfoList = pm.getInstalledApplications(PackageManager.GET_META_DATA);

        packageList = pm.getInstalledPackages(PackageManager.GET_PERMISSIONS);


        if (packageList.size() > 0) {
            Collections.sort(packageList, new Comparator<PackageInfo>() {
                @Override
                public int compare(final PackageInfo object1, final PackageInfo object2) {
                    return object1.applicationInfo.loadLabel(pm).toString().compareTo(object2.applicationInfo.loadLabel(pm).toString());
                }
            });
        }

        return getPackageInfo();
    }


    private List<MApps> getPackageInfo() {

        for (PackageInfo pkgInfo: packageList)
        {
            if (isSystemPackage(pkgInfo))
            {

                MApps apps = new MApps();
                apps.setLabel(pkgInfo.packageName);
                apps.setIcon(pkgInfo.applicationInfo.loadIcon(pm));
                apps.setName(pkgInfo.applicationInfo.loadLabel(pm));
                apps.setVer_name(pkgInfo.versionName);
                apps.setVer_code(pkgInfo.versionCode);
                appsList.add(apps);

            }

        }

//        searchAppList.addAll(appsList);
        Log.d("Abhi", appsList.toString());

        return appsList;
    }


    public void DetectInstallUninstallApps(){

        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(Intent.ACTION_PACKAGE_ADDED);
        intentFilter.addAction(Intent.ACTION_PACKAGE_REMOVED);
        intentFilter.addAction(Intent.ACTION_UNINSTALL_PACKAGE);
        intentFilter.addAction(Intent.ACTION_PACKAGE_INSTALL);
        intentFilter.addDataScheme("Package");

        receiver = new NewPackageReceiver();
        activity.registerReceiver(receiver, intentFilter);

//        return intentFilter;
    }


    private boolean isSystemPackage(PackageInfo pkgInfo) {
        return ((pkgInfo.applicationInfo.flags & ApplicationInfo.FLAG_SYSTEM) == 0);
    }

}
