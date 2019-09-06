package id.ac.umn.made_basiliusbias_submission4;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.preference.PreferenceManager;

public class LangApp extends AppCompatActivity {

    @Override
    protected void attachBaseContext(Context newBase) {

        // Get Language Shared Preference
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(newBase);
        String languageCode = sharedPreferences.getString(
            newBase.getResources().getString(R.string.pref_language_list_key),
            Utility.getSystemLocale()
        );

        // Set Language
        assert languageCode != null;
        super.attachBaseContext(Utility.changeLanguage(newBase, languageCode.split("-")[0]));
    }
}