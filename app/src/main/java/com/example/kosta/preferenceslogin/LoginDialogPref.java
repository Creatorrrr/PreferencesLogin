package com.example.kosta.preferenceslogin;

import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.preference.DialogPreference;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;

/**
 * Created by kosta on 2017-04-24.
 */

public class LoginDialogPref extends DialogPreference {

    private SharedPreferences prefs;
    private EditText id;
    private EditText pw;

    public LoginDialogPref(Context context, AttributeSet attrs) {
        super(context, attrs);
        prefs = context.getSharedPreferences(getKey(), Context.MODE_PRIVATE);
    }

    @Override
    protected View onCreateDialogView() {
        LayoutInflater inflater = (LayoutInflater)getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        LinearLayout layout = (LinearLayout)inflater.inflate(R.layout.login_dialog, null);

        id = (EditText)layout.findViewById(R.id.id);
        pw = (EditText)layout.findViewById(R.id.pw);

        id.setText(prefs.getString("id", ""));
        pw.setText(prefs.getString("pw", ""));

        return layout;
    }

    @Override
    public void onClick(DialogInterface dialog, int which) {
        if(which == DialogInterface.BUTTON_POSITIVE) {
            prefs = getContext().getSharedPreferences(getKey(), Context.MODE_PRIVATE);

            SharedPreferences.Editor edit = prefs.edit();
            edit.putString("id", id.getText().toString());
            edit.putString("pw", pw.getText().toString());
            edit.apply();
        }
    }
}
