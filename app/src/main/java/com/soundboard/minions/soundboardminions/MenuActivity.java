package com.soundboard.minions.soundboardminions;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;


public class MenuActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
    }

    public void goToSoundboard(View v){
        Intent intent = new Intent(this, SoundboardActivity.class);
        startActivity(intent);
    }

    public void goToMemory(View v){
        Intent intent = new Intent(this, MemoryActivity.class);
        startActivity(intent);
    }

}
