package com.example.notandi.chargemap;

import android.os.Bundle;
import android.preference.PreferenceActivity;


public class PreferenceAkt extends PreferenceActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.preferenceslayout);

        //This was disabled after vs 4
    }
}
