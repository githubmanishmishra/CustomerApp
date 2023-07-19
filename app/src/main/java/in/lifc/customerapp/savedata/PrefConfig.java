package in.lifc.customerapp.savedata;

import android.content.Context;
import android.content.SharedPreferences;

import in.lifc.customerapp.R;


public class PrefConfig {

    private SharedPreferences sharedPreferences;
    private Context context;

    public PrefConfig(Context context) {
        this.context = context;
        sharedPreferences = context.getSharedPreferences(context.getString(R.string.pref_file), Context.MODE_PRIVATE);
    }

    public void writeLoginStatus(boolean status)
    {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(context.getString(R.string.pref_login_status), status);
        editor.apply();
    }

    public boolean readLoginStatus() {

        return sharedPreferences.getBoolean(context.getString(R.string.pref_login_status), false);
    }

    public void writeName(String token) {

        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(context.getString(R.string.pref_token), token);
        editor.apply();
    }

    public String readToken() {

        return sharedPreferences.getString(context.getString(R.string.pref_token), "");
    }

}
