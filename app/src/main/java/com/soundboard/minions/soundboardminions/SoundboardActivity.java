package com.soundboard.minions.soundboardminions;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.TextView;

import com.soundboard.minions.soundboardminions.adapter.SoundsAdapter;

public class SoundboardActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_soundboard);
        setUIReferences();
    }

    private void setUIReferences() {
        TextView tutorial = (TextView) findViewById(R.id.tutorial);
        Typeface font = Typeface.createFromAsset(getAssets(), "fonts/Young.ttf");
        tutorial.setTypeface(font);

        GridView soundListView = (GridView) findViewById(R.id.soundsListLayout);
        SoundsAdapter adapter = new SoundsAdapter(this, Constants.SOUNDS_LIST);
        soundListView.setAdapter(adapter);
    }

}
