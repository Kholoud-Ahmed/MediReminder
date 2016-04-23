package com.iti.www.medireminder.dto;

/**
 * Created by Shall on 18/4/2016.
 */
public class NavItem {
    public String mTitle;
    public String mSubtitle;
    public int mIcon;

    public NavItem(String title, String subtitle, int icon) {
        mTitle = title;
        mSubtitle = subtitle;
        mIcon = icon;
    }
}