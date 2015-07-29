package com.soundboard.minions.soundboardminions;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.widget.GridView;
import android.widget.TextView;

import com.soundboard.minions.soundboardminions.adapter.SoundsAdapter;
import com.soundboard.minions.soundboardminions.listener.SoundboardEvents;
import com.soundboard.minions.soundboardminions.model.Sound;
import com.soundboard.minions.soundboardminions.utilities.Utilities;

public class SoundboardActivity extends ActionBarActivity implements SoundboardEvents {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_soundboard);
        Utilities.trackNavigation(this);
        setUIReferences();
    }

    private void setUIReferences() {
        TextView tutorial = (TextView) findViewById(R.id.tutorial);
        Typeface font = Typeface.createFromAsset(getAssets(), "fonts/Young.ttf");
        tutorial.setTypeface(font);

        GridView soundListView = (GridView) findViewById(R.id.soundsListLayout);
        SoundsAdapter adapter = new SoundsAdapter(this, Constants.SOUNDS_LIST, this);
        soundListView.setAdapter(adapter);
    }

    public void setRingtone(final Sound sound){
        new AlertDialog.Builder(this)
                .setTitle(getResources().getString(R.string.ringtone))
                .setMessage(getResources().getString(R.string.set_as_ringtone_question, sound.getTitle()))
                .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        Utilities.setAsRingtone(sound, getApplicationContext());
                    }
                })
                .setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                    }
                })
                .show();
    }

}
