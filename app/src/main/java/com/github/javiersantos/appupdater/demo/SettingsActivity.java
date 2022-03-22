package com.github.javiersantos.appupdater.demo;

import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceActivity;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;

import com.github.javiersantos.appupdater.AppUpdater;
import com.github.javiersantos.appupdater.enums.Display;
import com.github.javiersantos.appupdater.enums.UpdateFrom;

public class SettingsActivity extends PreferenceActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.settings);

        Preference prefCheckForUpdates = findPreference("prefCheckForUpdates");

        prefCheckForUpdates.setOnPreferenceClickListener(preference -> {
            new AppUpdater(SettingsActivity.this)
                    //.setUpdateFrom(UpdateFrom.GITHUB)
                    //.setGitHubUserAndRepo("javiersantos", "AppUpdater")
                    .setUpdateFrom(UpdateFrom.XML)
                    .setUpdateXML("https://raw.githubusercontent.com/javiersantos/AppUpdater/master/app/update-changelog.xml")
                    .setDisplay(Display.DIALOG)
                    .showAppUpdated(true)
                    .start();
            return true;
        });

    }

    @Override
    public void setContentView(int layoutResID) {
        ViewGroup contentView = (ViewGroup) LayoutInflater.from(this).inflate(R.layout.activity_settings, new LinearLayout(this), false);
        Toolbar toolbar = contentView.findViewById(R.id.toolbar);
        toolbar.setTitle(R.string.action_settings);
        toolbar.setTitleTextColor(ContextCompat.getColor(this, android.R.color.white));
        toolbar.setNavigationOnClickListener(view -> onBackPressed());

        ViewGroup contentWrapper = contentView.findViewById(R.id.content_wrapper);
        LayoutInflater.from(this).inflate(layoutResID, contentWrapper, true);
        getWindow().setContentView(contentView);

    }

}
