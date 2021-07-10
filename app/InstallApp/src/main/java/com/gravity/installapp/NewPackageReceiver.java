package com.gravity.installapp;

import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

public class NewPackageReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context, "app get install/uninstall", Toast.LENGTH_LONG).show();

        AlertDialog dialog = new AlertDialog.Builder(context).create();

        dialog.setTitle("Alert Dialog");

        // Setting Dialog Message
        dialog.setMessage("app get install/uninstall");

        // Showing Alert Message
        dialog.show();
    }
}
