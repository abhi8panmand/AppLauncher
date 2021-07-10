package com.gravity.installapp.Model;

import android.graphics.drawable.Drawable;

public class MApps {

    private CharSequence label;
    private CharSequence name;
    private Drawable icon;
    private String pkg_name;
    private int ver_code;
    private String ver_name;
    private String main_activity;

    @Override
    public String toString() {
        return "MApps{" +
                "label=" + label +
                ", name=" + name +
                ", icon=" + icon +
                ", pkg_name='" + pkg_name + '\'' +
                ", ver_code=" + ver_code +
                ", ver_name='" + ver_name + '\'' +
                ", main_activity='" + main_activity + '\'' +
                '}';
    }

    public CharSequence getLabel() {
        return label;
    }

    public void setLabel(CharSequence label) {
        this.label = label;
    }

    public CharSequence getName() {
        return name;
    }

    public void setName(CharSequence name) {
        this.name = name;
    }

    public Drawable getIcon() {
        return icon;
    }

    public void setIcon(Drawable icon) {
        this.icon = icon;
    }

    public String getPkg_name() {
        return pkg_name;
    }

    public void setPkg_name(String pkg_name) {
        this.pkg_name = pkg_name;
    }

    public int getVer_code() {
        return ver_code;
    }

    public void setVer_code(int ver_code) {
        this.ver_code = ver_code;
    }

    public String getVer_name() {
        return ver_name;
    }

    public void setVer_name(String ver_name) {
        this.ver_name = ver_name;
    }

    public String getMain_activity() {
        return main_activity;
    }

    public void setMain_activity(String main_activity) {
        this.main_activity = main_activity;
    }
}
