package com.soundboard.minions.soundboardminions;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.soundboard.minions.soundboardminions.utilities.Utilities;


public class MenuActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        setUIReferences();
        Utilities.trackNavigation(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        //Card views color reinit
        CardView goToSoundboard = (CardView) findViewById(R.id.go_to_soundboard);
        goToSoundboard.setCardBackgroundColor(getResources().getColor(R.color.white));
        CardView goToMemory = (CardView) findViewById(R.id.go_to_memory);
        goToMemory.setCardBackgroundColor(getResources().getColor(R.color.white));

    }

    private void setUIReferences() {
        Typeface font = Typeface.createFromAsset(getAssets(), "fonts/Young.ttf");
        TextView soundboardBtnTv = (TextView) findViewById(R.id.soundboardBtnTv);
        soundboardBtnTv.setTypeface(font);
        TextView memoryBtnTv = (TextView) findViewById(R.id.memoryBtnTv);
        memoryBtnTv.setTypeface(font);
    }

    public void goToSoundboard(View v){
        CardView goToSoundboard = (CardView) findViewById(R.id.go_to_soundboard);
        goToSoundboard.setCardBackgroundColor(getResources().getColor(R.color.lightgrey));
        Intent intent = new Intent(this, SoundboardActivity.class);
        startActivity(intent);
    }

    public void goToMemory(View v){
        CardView goToMemory = (CardView) findViewById(R.id.go_to_memory);
        goToMemory.setCardBackgroundColor(getResources().getColor(R.color.lightgrey));
        Intent intent = new Intent(this, MemoryActivity.class);
        startActivity(intent);
    }

}
