package com.soundboard.minions.soundboardminions;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.widget.GridView;
import android.widget.ListView;

import com.soundboard.minions.soundboardminions.adapter.SoundsAdapter;

public class SoundboardActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_soundboard);
        setUIReferences();
    }

    private void setUIReferences() {
        GridView soundListView = (GridView) findViewById(R.id.soundsListLayout);
        SoundsAdapter adapter = new SoundsAdapter(this, Constants.SOUNDS_LIST);
        soundListView.setAdapter(adapter);
    }

}
