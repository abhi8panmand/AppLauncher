package com.gravity.applauncher;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.SearchView;

import com.gravity.applauncher.Adapter.AppsAdapter;
import com.gravity.applauncher.databinding.ActivityMainBinding;
import com.gravity.installapp.AppsInfo;
import com.gravity.installapp.Model.MApps;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements AppsAdapter.CallBack {

    private ActivityMainBinding binding;
    private Activity activity;
    private List<MApps> appsList = new ArrayList<>();
    private AppsAdapter adapter;
    private LinearLayoutManager linearLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activity = this;
        binding = DataBindingUtil.setContentView(activity, R.layout.activity_main);

        AppsInfo appsInfo = new AppsInfo(activity);
        appsList.addAll(appsInfo.getAppData());
        appsInfo.DetectInstallUninstallApps();
        
        bindRecyclerVIewAdapter();
        setOnSearchListener();
    }

    private void bindRecyclerVIewAdapter() {

        linearLayoutManager = new LinearLayoutManager(activity, RecyclerView.VERTICAL, false);
        binding.recyclerView.setLayoutManager(linearLayoutManager);
        binding.recyclerView.setHasFixedSize(true);
        binding.recyclerView.setItemAnimator(new DefaultItemAnimator());
        adapter = new AppsAdapter(activity, appsList, this);
        binding.recyclerView.setAdapter(adapter);
    }

    private void setOnSearchListener() {

        binding.searchApp.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {

                adapter.getFilter().filter(s);
                return false;
            }
        });

    }

    @Override
    public void onAppClick(MApps apps, int position) {

        Intent launchApp = getPackageManager().getLaunchIntentForPackage(apps.getLabel().toString());
        startActivity(launchApp);
    }
}